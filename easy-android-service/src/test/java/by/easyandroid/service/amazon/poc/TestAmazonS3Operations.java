package by.easyandroid.service.amazon.poc;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.model.StorageClass;

public class TestAmazonS3Operations {

	private static Logger log = Logger.getLogger(TestAmazonS3Operations.class.getName());	
	
	private static final String TEST_BUCKET_NAME = "by.easyandroid.applications";
	
	private AmazonS3 s3;
	
	@Before
	public void setUp() throws IOException {
		InputStream in = TestAmazonS3Operations.class.getResourceAsStream("/aws-credentials.properties");
		s3 = new AmazonS3Client(new PropertiesCredentials(in));
	}
	
	@Ignore
	@Test
	public void testUploadFile() throws IOException {
		String key = "test/screenshot.jpg";

		File f = new File(new File("").getAbsoluteFile(), "/src/test/resources/screenshot.jpg");

		log.info("test logger");
		
		try {
			System.out.println("Uploading a new object to S3 from a file\n");
			// Upload object and make it public
			s3.putObject(new PutObjectRequest(TEST_BUCKET_NAME, key, f)
				.withCannedAcl(CannedAccessControlList.PublicRead)
				.withStorageClass(StorageClass.ReducedRedundancy));

			System.out.println("Listing objects");
			ObjectListing objectListing = s3.listObjects(new ListObjectsRequest().withBucketName(TEST_BUCKET_NAME));
			for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
				System.out.println(" - " + objectSummary.getKey() + "  " + "(size = " + objectSummary.getSize() + ")");
			}
		} catch (AmazonServiceException ase) {
			System.out.println("Caught an AmazonServiceException, which means your request made it " 
				+ "to Amazon S3, but was rejected with an error response for some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out.println("Caught an AmazonClientException, which means the client encountered "
							+ "a serious internal problem while trying to communicate with S3, "
							+ "such as not being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}
	}
}