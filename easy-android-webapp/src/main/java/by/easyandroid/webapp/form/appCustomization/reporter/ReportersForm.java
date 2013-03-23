package by.easyandroid.webapp.form.appCustomization.reporter;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import by.easyandroid.database.service.conference.ReporterService;
import by.easyandroid.database.service.exception.DatabaseServiceException;
import by.easyandroid.model.conference.Reporter;
import by.easyandroid.model.util.ModelUtil;
import by.easyandroid.webapp.form.ICrudForm;
import by.easyandroid.webapp.form.appCustomization.AbstractConferenceBaseForm;
import by.easyandroid.webapp.util.Bean;
import by.easyandroid.webapp.util.FacesUtil;

@ManagedBean
@RequestScoped
public class ReportersForm extends AbstractConferenceBaseForm implements ICrudForm<Reporter> {

	@ManagedProperty(value = "#{createReporterDialog}")
	private ReporterDialog createDialog;
	
	@ManagedProperty(value = "#{editReporterDialog}")
	private ReporterDialog editDialog;
	
	@ManagedProperty(value = "#{deleteReporterDialog}")
	private ReporterDialog deleteDialog;
	
	@ManagedProperty(value = Bean.SRV_REPORTER)
	private ReporterService reporterService;
	
	private List<Reporter> reporters = new ArrayList<Reporter>(); 
	
	@Override
	public void init() throws Exception {
		super.init();
		// TODO load values from database, get request parameter conference id
		// TODO it's better to do that when enabling/disabling opening ConferenceForm
		// TODO check if selected conference belongs to user
		if (template != null) {
			reporters = template.getModel().getReporters();
		}
	}

	@Override
	public void create(ActionEvent event) {
		try {
			if (template != null) {
				reporterService.add(createDialog.getObject(), template);
				createDialog.close();
			}
		} catch (DatabaseServiceException e) {
			// TODO display error message
			e.printStackTrace();
		}
	}	
	
	@Override
	public void delete(ActionEvent event) {
		reporterService.delete(deleteDialog.getObject().getId(), template);
		deleteDialog.close();
	}	
	

	@Override
	public void edit(ActionEvent event) {
		reporterService.save(editDialog.getObject());
		
		// Update UI
		ModelUtil.replaceById(reporters, editDialog.getObject());
		editDialog.close();
	}	
	
	@Override
	public List<Reporter> getAll() {
		return reporters;
	}
	
	// TODO to interface or base class
	public List<SelectItem> getSelectItems() {
		return FacesUtil.toSelectItems(reporters);
	}	

	public ReporterService getReporterService() {
		return reporterService;
	}

	public void setReporterService(ReporterService reporterService) {
		this.reporterService = reporterService;
	}

	public ReporterDialog getEditDialog() {
		return editDialog;
	}

	public void setEditDialog(ReporterDialog editDialog) {
		this.editDialog = editDialog;
	}

	public ReporterDialog getCreateDialog() {
		return createDialog;
	}

	public void setCreateDialog(ReporterDialog createDialog) {
		this.createDialog = createDialog;
	}

	public ReporterDialog getDeleteDialog() {
		return deleteDialog;
	}

	public void setDeleteDialog(ReporterDialog deleteDialog) {
		this.deleteDialog = deleteDialog;
	}
}