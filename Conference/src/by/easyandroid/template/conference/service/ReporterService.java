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

	public ReporterService(Context context) {
		super(context, REPORTER);
	}

	@Override
	protected Reporter parseItem(Node reporterNode) {
		Reporter reporter = new Reporter();
		reporter.setId(XmlUtil.getElementAttr(reporterNode, ID));
		reporter.setName(XmlUtil.getChildElementText(reporterNode, NAME));
		reporter.setDescription(XmlUtil.getChildElementText(reporterNode, DESCRIPTION));
		reporter.setCompany(XmlUtil.getChildElementText(reporterNode, COMPANY));
		reporter.setEmail(XmlUtil.getChildElementText(reporterNode, EMAIL));
		reporter.setPosition(XmlUtil.getChildElementText(reporterNode, POSITION));
		
		return reporter;
	}	
}