package by.easyandroid.template.adapter.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlUtil {

	public static Document createDocument() throws ParserConfigurationException {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		return builder.newDocument();
	}

	public static Element createChildElement(Element parent, String tagName, Document doc) {
		Element child = doc.createElement(tagName);
		parent.appendChild(child);
		return child;
	}
	
	public static Element appendTextNode(String tagName, String textContent, Document doc) {
		Element textNode = doc.createElement(tagName);
		textNode.setTextContent(textContent);
		return textNode;
	}
	
	public static String getXml(Document doc) throws TransformerException {
		TransformerFactory transfac = TransformerFactory.newInstance();
		Transformer trans = transfac.newTransformer();
		trans.setOutputProperty(OutputKeys.METHOD, "xml");
		trans.setOutputProperty(OutputKeys.INDENT, "yes");
		trans.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", Integer.toString(2));

		StringWriter sw = new StringWriter();
		StreamResult result = new StreamResult(sw);
		DOMSource source = new DOMSource(doc.getDocumentElement());

		trans.transform(source, result);
		return sw.toString();
	}
	
	public static Document parse(String source) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		return builder.parse(new ByteArrayInputStream(source.getBytes()));
	}
	
	public static Document parse(File source) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		return builder.parse(source);
	}	
	
	public static Node findFirstNodeByAttribute(NodeList list, String attrName, String attrValue) {
		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
			String nodeAttrValue = getElementAttr(node, attrName); 
			if (nodeAttrValue != null && attrValue.equals(nodeAttrValue)) {
				return node;
			}
		}
		
		return null;
	}
	
	public static Node getChildElement(Node element, String childNodeName) {
		for (int i = 0; i < element.getChildNodes().getLength(); i++) {
			Node child = element.getChildNodes().item(i);
			if (child.getNodeName().equals(childNodeName)) {
				return child;
			}
		}
		
		return null;
	}	
	
	public static String getChildElementText(Node element, String childNodeName) {
		Node child = getChildElement(element, childNodeName);
		
		if (child != null) {
			return child.getTextContent();
		}
		
		return null;
	}

	public static String getElementAttr(Node element, String attrName) {
		if (element.getAttributes() != null) {
			for (int i = 0; i < element.getAttributes().getLength(); i++) {
				Node child = element.getAttributes().item(i);
				if (child.getNodeName().equals(attrName)) {
					return child.getNodeValue();
				}
			}
		}
		
		return null;
	}
	
	public static int getElementIntAttr(Node element, String attrName) {
		String attrValue = getElementAttr(element, attrName);
		
		if (attrValue != null) {
			return Integer.parseInt(attrValue);
		}
		
		return 0;
	}
}