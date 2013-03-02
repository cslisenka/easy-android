package by.easyandroid.template.conference.util;

import org.w3c.dom.Node;

public class XmlUtil {
	
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
