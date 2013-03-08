package by.easyandroid.template.adapter.conference;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.easyandroid.model.conference.ConferenceApplicationModel;
import by.easyandroid.template.adapter.FileEntry;
import by.easyandroid.template.adapter.util.XmlUtil;

public class TestConferenceAdapter {

	private static final String CONFERENCE_DATA = "assets/conference_data.xml";
	private static final String STRINGS = "res/values/strings.xml";
	private static final String ABOUT = "assets/about.xml";
	
	private ConferenceAdapter adapter;
	private ConferenceApplicationModel model;
	
	@Before
	public void setUp() {
		model = new ConferenceApplicationModel();
		model.getInformation().setAbout("Some text about conference");
		model.getInformation().setWebsiteUrl("http://test.conference.com");
		model.getInformation().setTitle("My test conference");
		
		
		adapter = new ConferenceAdapter(model);
	}
	
	@Test
	public void testConvertSimpleModel() {
		List<FileEntry> results = adapter.convert();
		
		Assert.assertEquals(3, results.size());
		assertInMemoryFileExists(results, CONFERENCE_DATA);
		assertInMemoryFileExists(results, ABOUT);
		assertInMemoryFileExists(results, STRINGS);
	}
	
	@Test
	public void testConvertDataXmlFile() {
		String conferenceDataXml = getFileContents(adapter.convert(), CONFERENCE_DATA);
		
		// TODO deeply check conference_data.xml
	}
	
	@Test
	public void testConvertAboutHtmlFile() {
		String aboutHtml = getFileContents(adapter.convert(), ABOUT);
		Assert.assertTrue(aboutHtml.contains(model.getInformation().getAbout()));
	}	
	
	@Test
	public void testConvertStringsFile() throws ParserConfigurationException, SAXException, IOException {
		String stringsXml = getFileContents(adapter.convert(), STRINGS);
		
		// TODO to xml utils
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = builder.parse(new ByteArrayInputStream(stringsXml.getBytes()));
		NodeList strings = doc.getElementsByTagName("string");
		
		Assert.assertEquals(model.getInformation().getTitle(), XmlUtil.findFirstNodeByAttribute(strings, "name", "main_conf_name"));
		Assert.assertEquals(model.getInformation().getWebsiteUrl(), XmlUtil.findFirstNodeByAttribute(strings, "name", "main_website_url"));
	}
	
	private void assertInMemoryFileExists(List<FileEntry> list, String filePath) {
		if (findByFileName(list, filePath) == null) {
			Assert.fail("File with path '" + filePath + "' not exists in list");	
		}
	}	
	
	private String getFileContents(List<FileEntry> list, String filepath) {
		FileEntry entry = findByFileName(list, filepath);
		if (entry == null) {
			Assert.fail("Can not find file '" + filepath + "' to get content");
		}
		
		return entry.getFileContent();
	}
	
	private FileEntry findByFileName(List<FileEntry> list, String filepath) {
		for (FileEntry entry : list) {
			if (entry.getPathInProject().equals(filepath)) {
				return entry;
			}
		}
		
		return null;		
	}
}