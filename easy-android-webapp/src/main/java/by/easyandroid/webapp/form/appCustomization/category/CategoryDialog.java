package by.easyandroid.webapp.form.appCustomization.category;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import by.easyandroid.model.conference.Category;
import by.easyandroid.webapp.form.AbstractEntityDialog;

@ManagedBean
@SessionScoped
public class CategoryDialog extends AbstractEntityDialog<Category> {

	public CategoryDialog() throws InstantiationException, IllegalAccessException {
		super(Category.class);
	}
}