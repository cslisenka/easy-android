package by.easyandroid.template.conference.service;

import org.w3c.dom.Node;

import android.content.Context;
import by.easyandroid.template.conference.model.Reporter;
import by.easyandroid.template.conference.util.XmlUtil;

public class ReporterService extends AbstractEntityService<Reporter> {

	private static final String POSITION = "position";
	private static final String EMAIL = "email";
	private static final String COMPANY = "company";
	private static final String DESCRIPTION = "description";
	private static final String NAME = "name";

	public ReporterService(Context context) {
		super(context, REPORTER);
	}

	@Override
	protected Reporter parseItem(Node xmlNode) {
		Reporter reporter = new Reporter();
		reporter.setId(XmlUtil.getElementAttr(xmlNode, ID));
		reporter.setName(XmlUtil.getChildElementText(xmlNode, NAME));
		reporter.setDescription(XmlUtil.getChildElementText(xmlNode, DESCRIPTION));
		reporter.setCompany(XmlUtil.getChildElementText(xmlNode, COMPANY));
		reporter.setEmail(XmlUtil.getChildElementText(xmlNode, EMAIL));
		reporter.setPosition(XmlUtil.getChildElementText(xmlNode, POSITION));
		
		return reporter;
	}	
}