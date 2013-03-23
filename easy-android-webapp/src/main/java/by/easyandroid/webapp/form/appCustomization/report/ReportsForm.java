package by.easyandroid.webapp.form.appCustomization.report;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import by.easyandroid.database.service.conference.ReportService;
import by.easyandroid.model.conference.Report;
import by.easyandroid.webapp.form.ICrudForm;
import by.easyandroid.webapp.form.appCustomization.AbstractConferenceBaseForm;
import by.easyandroid.webapp.util.Bean;

@ManagedBean
@RequestScoped
public class ReportsForm extends AbstractConferenceBaseForm implements ICrudForm<Report> {

	@ManagedProperty(value = "#{createReportDialog}")
	private ReportDialog createDialog;
	
	@ManagedProperty(value = "#{editReportDialog}")
	private ReportDialog editDialog;	
	
	@ManagedProperty(value = "#{deleteReportDialog}")
	private ReportDialog deleteDialog;
	
	@ManagedProperty(value = Bean.SRV_REPORT)
	private ReportService reportService;
	
	private List<Report> reports = new ArrayList<Report>(); 
	
	@Override
	public void init() throws Exception {
		super.init();
		// TODO load values from database, get request parameter conference id
		// TODO check if selected conference belongs to user
		if (template != null) {
			reports = template.getModel().getReports();
		}
	}

	@Override
	public List<Report> getAll() {
		return reports;
	}

	@Override
	public void delete(ActionEvent event) {
		// TODO Auto-generated method stub
		deleteDialog.close();
	}

	@Override
	public void create(ActionEvent event) {
		// TODO Auto-generated method stub
		createDialog.close();
	}

	@Override
	public void edit(ActionEvent event) {
		// TODO Auto-generated method stub
		editDialog.close();
	}

	public ReportDialog getCreateDialog() {
		return createDialog;
	}

	public void setCreateDialog(ReportDialog createDialog) {
		this.createDialog = createDialog;
	}

	public ReportDialog getEditDialog() {
		return editDialog;
	}

	public void setEditDialog(ReportDialog editDialog) {
		this.editDialog = editDialog;
	}

	public ReportDialog getDeleteDialog() {
		return deleteDialog;
	}

	public void setDeleteDialog(ReportDialog deleteDialog) {
		this.deleteDialog = deleteDialog;
	}

	public ReportService getReportService() {
		return reportService;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}
}