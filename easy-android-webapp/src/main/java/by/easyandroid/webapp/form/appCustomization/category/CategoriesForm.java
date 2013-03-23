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
import by.easyandroid.model.util.ModelUtil;
import by.easyandroid.webapp.form.ICrudForm;
import by.easyandroid.webapp.form.appCustomization.AbstractConferenceBaseForm;
import by.easyandroid.webapp.util.Bean;

@ManagedBean
@RequestScoped
public class CategoriesForm extends AbstractConferenceBaseForm implements ICrudForm<Category> {

	@ManagedProperty(value = "#{createCategoryDialog}")
	private CreateCategoryDialog createDialog;
	
	@ManagedProperty(value = "#{editCategoryDialog}")
	private EditCategoryDialog editDialog;	
	
	@ManagedProperty(value = "#{deleteCategoryDialog}")
	private DeleteCategoryDialog deleteDialog;
	
	@ManagedProperty(value = Bean.SRV_CATEGORY)
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

	@Override
	public void create(ActionEvent event) {
		try {
			if (template != null) {
				categoryService.add(createDialog.getObject(), template);
				createDialog.close();
			}
		} catch (DatabaseServiceException e) {
			// TODO display error message
			e.printStackTrace();
		}
	}	
	
	@Override
	public void delete(ActionEvent event) {
		categoryService.delete(deleteDialog.getObject().getId(), template);
		deleteDialog.close();
	}
	
	@Override
	public void edit(ActionEvent event) {
		categoryService.save(editDialog.getObject());
		
		// Update UI
		ModelUtil.replaceById(getAll(), editDialog.getObject());
		editDialog.close();
	}

	@Override
	public List<Category> getAll() {
		return categories;
	}

	public CreateCategoryDialog getCreateDialog() {
		return createDialog;
	}

	public void setCreateDialog(CreateCategoryDialog createDialog) {
		this.createDialog = createDialog;
	}

	public EditCategoryDialog getEditDialog() {
		return editDialog;
	}

	public void setEditDialog(EditCategoryDialog editDialog) {
		this.editDialog = editDialog;
	}

	public DeleteCategoryDialog getDeleteDialog() {
		return deleteDialog;
	}

	public void setDeleteDialog(DeleteCategoryDialog deleteDialog) {
		this.deleteDialog = deleteDialog;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
}