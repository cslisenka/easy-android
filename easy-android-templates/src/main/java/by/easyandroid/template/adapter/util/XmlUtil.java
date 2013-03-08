package by.easyandroid.template.adapter.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlUtil {

	public static Document parse(String source) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		return builder.parse(new ByteArrayInputStream(source.getBytes()));
	}
	
	public static Node findFirstNodeByAttribute(NodeList list, String attrName, String attrValue) {
		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
			if (attrValue.equals(getElementAttr(node, attrName))) {
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
		for (int i = 0; i < element.getAttributes().getLength(); i++) {
			Node child = element.getAttributes().item(i);
			if (child.getNodeName().equals(attrName)) {
				return child.getNodeValue();
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