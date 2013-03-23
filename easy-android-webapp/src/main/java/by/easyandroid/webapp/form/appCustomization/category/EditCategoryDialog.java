package by.easyandroid.webapp.form.appCustomization.category;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import by.easyandroid.model.conference.Category;
import by.easyandroid.webapp.form.AbstractEntityDialog;

@ManagedBean
@SessionScoped
public class EditCategoryDialog extends AbstractEntityDialog<Category> {

	public EditCategoryDialog() throws InstantiationException, IllegalAccessException {
		super(Category.class);
	}
}