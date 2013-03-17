package by.easyandroid.webapp.form.appCustomization;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import by.easyandroid.model.conference.Category;

@ManagedBean
@RequestScoped
public class CategoriesForm extends AbstractConferenceBaseForm {

	private List<Category> categories = new ArrayList<Category>(); 
	
	@Override
	public void init() throws Exception {
		super.init();
		// TODO load values from database, get request parameter conference id
		// TODO it's better to do that when enabling/disabling opening ConferenceForm
		// TODO check if selected conference belongs to user
		if (template != null) {
			categories = template.getModel().getCategories();
		}
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
}