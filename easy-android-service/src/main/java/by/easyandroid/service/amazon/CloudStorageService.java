package by.easyandroid.service.amazon;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import by.easyandroid.service.exception.ApplicationServiceException;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.model.StorageClass;

/**
 * Operates with Amazon s3 cloud storage.
 * Has methods for application business operations with cloud storage.
 * 
 * @author kslisenko
 */
public class CloudStorageService {

	private String bucketName;
	private AmazonS3 s3;
	
	public CloudStorageService(String bucketName, AmazonS3 s3) {
		this.bucketName = bucketName;
		this.s3 = s3;
	}

	/**
	 * Uploads file to amazon s3.
	 * Returns public html link to file.
	 * File stored at cloud storage with reduced redundancy storage class.
	 * 
	 * @param fileToUpload
	 * @param folderInBucket path in cloud storage (within bucket) where uploaded file should be
	 * @return
	 */
	public String uploadPublicFile(File fileToUpload, String folderInBucket) throws ApplicationServiceException {
		if (!fileToUpload.exists() || fileToUpload.isDirectory()) {
			throw new ApplicationServiceException("File to upload " + fileToUpload.getAbsolutePath() + " not exists or is a directory");
		}
		
		try {
			s3.putObject(new PutObjectRequest(bucketName, folderInBucket + "/" + fileToUpload.getName(), fileToUpload)
				.withCannedAcl(CannedAccessControlList.PublicRead)
				.withStorageClass(StorageClass.ReducedRedundancy));
		} catch (AmazonServiceException e) {
			throw new ApplicationServiceException("Error when uploading file " 
					+ fileToUpload.getAbsolutePath() + " to amazon s3, bucket name " + folderInBucket, e);
		}
		
		return getS3ObjectUrl(folderInBucket + "/" + fileToUpload.getName());
	}
	
	/**
	 * Removes all files in the folder at cloud storage
	 * 
	 * @param folderInBucket
	 * @throws ApplicationServiceException
	 */
	public void clearFolder(String folderInBucket) throws ApplicationServiceException {
		try {
			List<String> filesInFolder = listFiles(folderInBucket);
			for (String filePath : filesInFolder) {
				s3.deleteObject(bucketName, filePath);
			}
		} catch (AmazonServiceException e) {
			throw new ApplicationServiceException("Error when delete folder " + folderInBucket 
					+ "from cloud storage", e);
		}
	}
	
	/**
	 * Downloads all files from folder at cloud storage to local folder.
	 * Application uses it for downloading templates content (android project sources) from cloud storage.
	 * 
	 * @param folderInBucket
	 * @param localFolder
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	public void downloadFiles(String folderInBucket, File localFolder) throws ApplicationServiceException {
		try {
			ObjectListing objectListing = s3.listObjects(new ListObjectsRequest()
				.withBucketName(bucketName)
				.withPrefix(folderInBucket + "/"));
			
			for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
				if (objectSummary.getSize() > 0) {
					URL url = new URL(getS3ObjectUrl(objectSummary.getKey()));
					String localName = toLocalName(objectSummary.getKey(), folderInBucket);
					File resultFile = new File(localFolder, localName);
					FileUtils.copyURLToFile(url, resultFile);
				} else {
					// Create folder
					new File(localFolder, objectSummary.getKey()).mkdirs();
				}
			}
		} catch (AmazonServiceException e) {
			throw new ApplicationServiceException("Error when downloading directory from amazon", e);
		} catch (MalformedURLException e) {
			throw new ApplicationServiceException(e);
		} catch (IOException e) {
			throw new ApplicationServiceException(e);
		}
	}

	public List<String> listFiles(String folderInBucket) {
		ObjectListing objectListing = s3.listObjects(new ListObjectsRequest()
			.withBucketName(bucketName)
			.withPrefix(folderInBucket + "/"));
		
		List<String> result = new ArrayList<String>();
		for (S3ObjectSummary summary : objectListing.getObjectSummaries()) {
			result.add(summary.getKey());
		}
		
		return result;
	}
	
	protected String toLocalName(String nameInBucket, String baseFolderInBucket) {
		return nameInBucket.replaceFirst(baseFolderInBucket + "/", "");
	}
	
	protected String getS3ObjectUrl(String pathInBucket) {
		return String.format("http://s3.amazonaws.com/%s/%s", bucketName, pathInBucket);
	}
	
	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public AmazonS3 getS3() {
		return s3;
	}

	public void setS3(AmazonS3 s3) {
		this.s3 = s3;
	}
}