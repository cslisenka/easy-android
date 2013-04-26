package by.easyandroid.template.adapter.conference;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.easyandroid.model.Identity;
import by.easyandroid.model.conference.Category;
import by.easyandroid.model.conference.ConferenceApplicationModel;
import by.easyandroid.model.conference.Report;
import by.easyandroid.model.conference.Reporter;
import by.easyandroid.model.conference.Section;
import by.easyandroid.template.adapter.FileEntry;
import by.easyandroid.template.adapter.exception.TemplateAdapterException;
import by.easyandroid.template.adapter.util.EntityConverter;
import by.easyandroid.template.adapter.util.XmlUtil;

public class TestConferenceAdapter {

	private static final String CONFERENCE_DATA = "assets/conference_data.xml";
	private static final String STRINGS = "res/values/strings.xml";
	private static final String ABOUT = "assets/about.html";
	
	private ConferenceAdapter adapter;
	private ConferenceApplicationModel model;
	
	private String pathToSources;
	
	@Before
	public void setUp() {
		model = new ConferenceApplicationModel();
		model.getInformation().setAbout("Some text about conference");
		model.getInformation().setWebsiteUrl("http://test.conference.com");
		model.getInformation().setTitle("My test conference");
		
		// Create fake sections
		Section s1 = addSection("section1id", "section 1");
		Section s2 = addSection("section2id", "section 2");
		
		// Create fake categories
		Category c1 = addCategory("category1id", "category 1");
		Category c2 = addCategory("category2id", "category 2");
		
		Reporter r1 = addReporter("reporter1id", "reporter 1");
		Reporter r2 = addReporter("reporter2id", "reporter 2");
		
		addReport("report1id", "report 1", s1, c1, r1);
		addReport("report2id", "report 2", s2, c2, r2);
		
		adapter = new ConferenceAdapter(model);
		
		pathToSources = new File("").getAbsolutePath() + "/../../../app-conference";
		// TODO this is temporary solution, we need to put project sources into target folder during build
		adapter.setSourcesPath(pathToSources);
	}
	
	@Test
	public void testConvertSimpleModel() throws TemplateAdapterException {
		List<FileEntry> results = adapter.convert();
		
		Assert.assertEquals(3, results.size());
		assertInMemoryFileExists(results, CONFERENCE_DATA);
		assertInMemoryFileExists(results, ABOUT);
		assertInMemoryFileExists(results, STRINGS);
	}
	
	@Test
	public void testConvertDataXmlFile() throws ParserConfigurationException, SAXException, IOException, TemplateAdapterException {
		String conferenceDataXml = getFileContents(adapter.convert(), CONFERENCE_DATA);
		Document doc = XmlUtil.parse(conferenceDataXml);
		
		checkReports(model, doc);
		checkReporters(model, doc);
		checkSections(model, doc);
		checkCategories(model, doc);
	}
	
	@Test
	public void testConvertAboutHtmlFile() throws TemplateAdapterException {
		String aboutHtml = getFileContents(adapter.convert(), ABOUT);
		Assert.assertTrue(aboutHtml.contains(model.getInformation().getAbout()));
	}	
	
	@Test
	public void testConvertStringsFile() throws ParserConfigurationException, SAXException, IOException, TemplateAdapterException {
		String stringsXml = getFileContents(adapter.convert(), STRINGS);
		
		Document doc = XmlUtil.parse(stringsXml);
		NodeList strings = doc.getElementsByTagName("string");
		
		Assert.assertTrue(strings.getLength() > 0);
		
		Assert.assertEquals(model.getInformation().getTitle(), XmlUtil.findFirstNodeByAttribute(strings, "name", "main_conf_name").getTextContent());
		Assert.assertEquals(model.getInformation().getWebsiteUrl(), XmlUtil.findFirstNodeByAttribute(strings, "name", "main_website_url").getTextContent());
	}
	
	private void addReport(String id, String name, Section s1, Category c1, Reporter r1) {
		Report r = new Report();
		r.setId(id);
		r.setTitle(name);
		r.setReporter(r1);
		r.setSection(s1);
		r.setCategory(c1);
		r.setDescription("description " + name);
		r.setTime(new Date(2013, 10, 10, 5, 30));
		model.getReports().add(r);
	}

	private Reporter addReporter(String id, String name) {
		Reporter r = new Reporter(); 
		r.setId(id);
		r.setName(name);
		r.setDescription("description " + name);
		r.setEmail("email " + name);
		r.setPosition("position " + name);
		r.setCompany("company " + name);
		model.getReporters().add(r);
		return r;
	}

	private Category addCategory(String id, String name) {
		Category c = new Category();
		c.setName(name);
		c.setId(id);
		model.getCategories().add(c);
		return c;
	}	
	
	private Section addSection(String id, String name) {
		Section s = new Section();
		s.setName(name);
		s.setId(id);
		model.getSections().add(s);
		return s;
	}		
	
	private void checkCategories(ConferenceApplicationModel model, Document doc) {
		NodeList categories = doc.getElementsByTagName("category");
		Assert.assertEquals(model.getCategories().size(), categories.getLength());
		for (Category oneCategory : model.getCategories()) {
			Node reportNode = XmlUtil.findFirstNodeByAttribute(categories, "id", getId(oneCategory));
			Assert.assertNotNull(reportNode);
//			Assert.assertEquals(oneCategory.getId(), XmlUtil.getElementAttr(reportNode, "id"));
			Assert.assertEquals(oneCategory.getName(), XmlUtil.getChildElementText(reportNode, "name"));
			assertNumber(XmlUtil.getElementAttr(reportNode, "id"));
			// TODO test relations in model 
			// 1. all ids are unique
			// 2. relations are correct
		}
	}

	private void checkSections(ConferenceApplicationModel model, Document doc) {
		NodeList sections = doc.getElementsByTagName("section");
		Assert.assertEquals(model.getSections().size(), sections.getLength());
		for (Section oneSection : model.getSections()) {
			Node sectionNode = XmlUtil.findFirstNodeByAttribute(sections, "id", getId(oneSection));
			Assert.assertNotNull(sectionNode);
//			Assert.assertEquals(oneSection.getId(), XmlUtil.getElementAttr(sectionNode, "id"));
			Assert.assertEquals(oneSection.getName(), XmlUtil.getChildElementText(sectionNode, "name"));
			assertNumber(XmlUtil.getElementAttr(sectionNode, "id"));
		}
	}

	private void checkReporters(ConferenceApplicationModel model2, Document doc) {
		NodeList reporters = doc.getElementsByTagName("reporter");
		Assert.assertEquals(model.getReporters().size(), reporters.getLength());
		for (Reporter oneReporter : model.getReporters()) {
			Node reportNode = XmlUtil.findFirstNodeByAttribute(reporters, "id", getId(oneReporter));
			Assert.assertNotNull(reportNode);
//			Assert.assertEquals(oneReporter.getId(), XmlUtil.getElementAttr(reportNode, "id"));
			Assert.assertEquals(oneReporter.getDescription(), XmlUtil.getChildElementText(reportNode, "description"));
			Assert.assertEquals(oneReporter.getEmail(), XmlUtil.getChildElementText(reportNode, "email"));
			Assert.assertEquals(oneReporter.getCompany(), XmlUtil.getChildElementText(reportNode, "company"));
			Assert.assertEquals(oneReporter.getPosition(), XmlUtil.getChildElementText(reportNode, "position"));
			assertNumber(XmlUtil.getElementAttr(reportNode, "id"));
		}
	}

	private void checkReports(ConferenceApplicationModel model, Document doc) {
		NodeList reports = doc.getElementsByTagName("report");
		Assert.assertEquals(model.getReports().size(), reports.getLength());
		for (Report oneReport : model.getReports()) {
			Node reportNode = XmlUtil.findFirstNodeByAttribute(reports, "id", getId(oneReport));
			Assert.assertNotNull(reportNode);
			Assert.assertEquals(oneReport.getTitle(), XmlUtil.getChildElementText(reportNode, "title"));
			Assert.assertEquals(oneReport.getDescription(), XmlUtil.getChildElementText(reportNode, "description"));
//			Assert.assertEquals(oneReport.getCategory().getId(), XmlUtil.getElementAttr(reportNode, "category"));
//			Assert.assertEquals(oneReport.getSection().getId(), XmlUtil.getElementAttr(reportNode, "section"));
//			Assert.assertEquals(oneReport.getReporter().getId(), XmlUtil.getElementAttr(reportNode, "reporter"));
			Assert.assertEquals(oneReport.getTime(), EntityConverter.toDate(reportNode));
			assertNumber(XmlUtil.getElementAttr(reportNode, "id"));
			assertNumber(XmlUtil.getElementAttr(reportNode, "category"));
			assertNumber(XmlUtil.getElementAttr(reportNode, "section"));
			assertNumber(XmlUtil.getElementAttr(reportNode, "reporter"));
		}
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
	
	public static void assertNumber(String value) {
		try {
			Integer.parseInt(value);
		} catch (Exception e) {
			Assert.fail("value " + value + "is not number");
		}
	}
	
	public String getId(Identity object) {
		return adapter.getConverter().getMapper().getId(object);
	}
}