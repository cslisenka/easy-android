package by.easyandroid.template.conference.service;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.content.Context;
import by.easyandroid.template.conference.model.Identity;
import by.easyandroid.template.conference.util.AndroidUtil;

public abstract class AbstractEntityService<E extends Identity> {

	protected static final String ID = "id";
	protected static final String REPORTER = "reporter";
	protected static final String NAME = "name";
	protected static final String SECTION = "section";
	protected static final String CATEGORY = "category";	
	
	protected static final String DATA_XML = "conference_data.xml";
	
	protected Context context;
	private String itemNodeName;

	public AbstractEntityService(Context context, String itemNodeName) {
		this.context = context;
		this.itemNodeName = itemNodeName;
	}
	
	public List<E> getAll() {
		Document xml = AndroidUtil.readXmlAsset(context, DATA_XML);
		List<E> result = new ArrayList<E>();
		
		NodeList reports = xml.getElementsByTagName(itemNodeName);
		for (int i = 0; i < reports.getLength(); i++) {
			result.add(parseItem(reports.item(i)));
		}
		
		return result;
	}
	
	public final E getById(long id) {
		for (E oneReport : getAll()) {
			if (Long.parseLong(oneReport.getId()) == id) {
				return oneReport;
			}
		}
		
		return null;
	}
	
	protected abstract E parseItem(Node xmlNode);
}