package by.easyandroid.webapp.form.appCustomization.category;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import by.easyandroid.model.conference.Category;
import by.easyandroid.webapp.form.AbstractEntityDialog;

@ManagedBean
@SessionScoped
public class CreateCategoryDialog extends AbstractEntityDialog<Category> {

	public CreateCategoryDialog() throws InstantiationException, IllegalAccessException {
		super(Category.class);
	}
}