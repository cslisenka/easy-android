package by.easyandroid.template.adapter.util;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.io.FilenameUtils;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import by.easyandroid.template.adapter.FileEntry;
import by.easyandroid.template.adapter.exception.TemplateAdapterException;

/**
 * Utility for easyer creating adapters for applications
 * @author kslisenko
 */
public class AdapterUtil {

	public static FileEntry create(String pathInProject, Document doc) throws TemplateAdapterException {
		try {
			return new FileEntry(pathInProject, XmlUtil.getXml(doc));
		} catch (TransformerException e) {
			throw new TemplateAdapterException("Erorr when converting '" + pathInProject + "' file from xml to text", e);
		}
	}
	
	public static void updateStringsXml(Document document, Map<String, String> attributes) throws TemplateAdapterException {
		Node resources = XmlUtil.getChildElement(document, "resources");
		
		for (String stringName : attributes.keySet()) {
			Node confNameNode = XmlUtil.findFirstNodeByAttribute(resources.getChildNodes(), "name", stringName);
			confNameNode.setTextContent(attributes.get(stringName));
		}
	}
	
	public static Document getProjectXml(String sourcesPath, String pathInProject) throws TemplateAdapterException {
		try {
			File xmlFile = getProjectFile(sourcesPath, pathInProject);
			return XmlUtil.parse(xmlFile);
		} catch (ParserConfigurationException e) {
			throw new TemplateAdapterException("Erorr when parsing xml file '" + pathInProject + "' file in project sources", e);
		} catch (SAXException e) {
			throw new TemplateAdapterException("Erorr when parsing xml file '" + pathInProject + "' file in project sources", e);
		} catch (IOException e) {
			throw new TemplateAdapterException("Erorr when parsing xml file '" + pathInProject + "' file in project sources", e);
		}
	}	
	
	public static org.jsoup.nodes.Document getProjectWebPage(String sourcesPath, String pathInProject) throws TemplateAdapterException {
		try {
			File webPageFile = getProjectFile(sourcesPath, pathInProject);
			return Jsoup.parse(webPageFile, "UTF-8");
		} catch (IOException e) {
			throw new TemplateAdapterException("Erorr when parsing webpage '" + pathInProject + "' file in project sources", e);
		}
	}
	
	public static File getProjectFile(String sourcesPath, String pathInProject) throws TemplateAdapterException {
		String path = FilenameUtils.separatorsToSystem(sourcesPath + File.separator +  pathInProject);
		File fileInProject = new File(path);
		if (!fileInProject.exists()) {
			throw new TemplateAdapterException("File '" + pathInProject + "' is not exists in project sources");
		}
		
		return fileInProject;
	}
}