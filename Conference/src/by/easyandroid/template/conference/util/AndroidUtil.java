package by.easyandroid.template.conference.util;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class AndroidUtil {

	public static void setText(View baseView, int viewId, String text) {
		TextView textView = (TextView) baseView.findViewById(viewId);
		textView.setText(text);
	}
	
	public static Document readXmlAsset(Context context, String xmlFileName) {
		try {
			InputStream in = context.getAssets().open(xmlFileName);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			return builder.parse(in);			
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		} catch (SAXException e) {
			throw new RuntimeException(e);
		}
	}
}