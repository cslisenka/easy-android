package by.easyandroid.template.conference.service;

import org.w3c.dom.Node;

import android.content.Context;
import by.easyandroid.template.conference.model.Section;
import by.easyandroid.template.conference.util.XmlUtil;

public class SectionService extends AbstractEntityService<Section> {

	public SectionService(Context context) {
		super(context, SECTION);
	}

	@Override
	protected Section parseItem(Node sectionItem) {
		Section section = new Section();
		section.setId(XmlUtil.getElementAttr(sectionItem, ID));
		section.setName(XmlUtil.getChildElementText(sectionItem, NAME));
		
		return section;
	}
}