package by.easyandroid.template.conference.service;

import org.w3c.dom.Node;

import android.content.Context;
import by.easyandroid.template.conference.model.Category;
import by.easyandroid.template.conference.util.XmlUtil;

public class CategoryService extends AbstractEntityService<Category> {

	public CategoryService(Context context) {
		super(context, CATEGORY);
	}

	@Override
	protected Category parseItem(Node sectionItem) {
		Category category = new Category();
		category.setId(XmlUtil.getElementAttr(sectionItem, ID));
		category.setName(XmlUtil.getChildElementText(sectionItem, NAME));
		return category;
	}
}