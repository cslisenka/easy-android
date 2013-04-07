package by.easyandroid.service.amazon;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.easyandroid.service.exception.ApplicationServiceException;
import by.easyandroid.service.util.UnitTestUtil;

// TODO use test contexts for unit-tests in whole project
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/service-context.xml")
// TODO solve problem with running amazon rests on jenkins
@Ignore("Works only on local mashine now because of credientials")
public class TestCloudStorageService {

	private static final String BUCKET_TEST_DIR = "test";

	@Autowired
	private CloudStorageService storageService;
	
	private File testFile;
	private File testFile2;
	
	@Before
	public void setUp() {
		testFile = new File(new File("").getAbsoluteFile(), "/src/test/resources/screenshot.jpg");
		testFile2 = new File(new File("").getAbsoluteFile(), "/src/test/resources/test-xml-file.xml");
	}
	
	@Test
	public void testUploadPublicFile() throws ApplicationServiceException, MalformedURLException, IOException {
		String link = storageService.uploadPublicFile(testFile, BUCKET_TEST_DIR);
		Assert.assertNotNull(link);
		Assert.assertEquals(1, storageService.listFiles(BUCKET_TEST_DIR).size());
		
		File resultFile = new File(new File("").getAbsoluteFile(), "target/screenshot.jpg");
		FileUtils.copyURLToFile(new URL(link), resultFile);
		Assert.assertTrue(resultFile.exists());
		Assert.assertTrue(resultFile.isFile());
		Assert.assertEquals(testFile.length(), resultFile.length());
	}
	
	@Test(expected = ApplicationServiceException.class)
	public void testUploadNotExistFile() throws ApplicationServiceException {
		storageService.uploadPublicFile(new File("fake"), BUCKET_TEST_DIR);
	}
	
	@Test
	public void testDownloadFiles() throws ApplicationServiceException, IOException {
		// Upload two files to test directory
		storageService.uploadPublicFile(testFile, BUCKET_TEST_DIR);
		storageService.uploadPublicFile(testFile2, BUCKET_TEST_DIR + "/subdir");
		
		File folderToDownload = UnitTestUtil.getFolderInTarget("testDownloadS3Files");
		
		storageService.downloadFiles(BUCKET_TEST_DIR, folderToDownload);
		Assert.assertTrue(new File(folderToDownload, testFile.getName()).exists());
		Assert.assertTrue(new File(folderToDownload, "subdir" + File.separator + testFile2.getName()).exists());
		
		FileUtils.deleteDirectory(folderToDownload);
	}
	
	@Test
	public void testToLocalName() {
		Assert.assertEquals("subfolder/file.ext", storageService.toLocalName("testfolder/subfolder/file.ext", "testfolder"));
		Assert.assertEquals("file.ext", storageService.toLocalName("testfolder/subfolder/file.ext", "testfolder/subfolder"));
		Assert.assertEquals("testfolder/subfolder/file.ext", storageService.toLocalName("testfolder/subfolder/file.ext", "fake"));
	}
	
	@Test
	public void testClearFolder() throws ApplicationServiceException {
		storageService.uploadPublicFile(testFile, BUCKET_TEST_DIR);
		Assert.assertEquals(1, storageService.listFiles(BUCKET_TEST_DIR).size());
		storageService.clearFolder(BUCKET_TEST_DIR);
		Assert.assertEquals(0, storageService.listFiles(BUCKET_TEST_DIR).size());
	}
	
	@After
	public void tearDown() throws ApplicationServiceException {
		storageService.clearFolder(BUCKET_TEST_DIR);
	}
}