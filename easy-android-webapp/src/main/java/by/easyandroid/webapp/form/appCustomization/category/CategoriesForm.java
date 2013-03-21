package by.easyandroid.webapp.form.appCustomization.category;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import by.easyandroid.database.service.conference.CategoryService;
import by.easyandroid.database.service.exception.DatabaseServiceException;
import by.easyandroid.model.conference.Category;
import by.easyandroid.webapp.form.appCustomization.AbstractConferenceBaseForm;

@ManagedBean
@RequestScoped
public class CategoriesForm extends AbstractConferenceBaseForm {

	@ManagedProperty(value = "#{createCategoryDialog}")
	private CreateCategoryDialog createCategoryDialog;
	
	@ManagedProperty(value = "#{categoryService}")
	private CategoryService categoryService;
	
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

	public void create(ActionEvent event) {
		Category category = new Category();
		category.setName(createCategoryDialog.getName());
		
		try {
			if (template != null) {
				categoryService.add(category, template);
				createCategoryDialog.close();
			}
		} catch (DatabaseServiceException e) {
			// TODO display error message
			e.printStackTrace();
		}
	}	
	
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public CreateCategoryDialog getCreateCategoryDialog() {
		return createCategoryDialog;
	}

	public void setCreateCategoryDialog(CreateCategoryDialog createCategoryDialog) {
		this.createCategoryDialog = createCategoryDialog;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
}