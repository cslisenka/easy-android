package by.easyandroid.webapp.form.appCustomization.report;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import by.easyandroid.database.service.conference.CategoryService;
import by.easyandroid.database.service.conference.ReportService;
import by.easyandroid.database.service.conference.ReporterService;
import by.easyandroid.database.service.conference.SectionService;
import by.easyandroid.database.service.exception.DatabaseServiceException;
import by.easyandroid.model.conference.Category;
import by.easyandroid.model.conference.Report;
import by.easyandroid.model.conference.Reporter;
import by.easyandroid.model.conference.Section;
import by.easyandroid.model.util.ModelUtil;
import by.easyandroid.webapp.form.ICrudForm;
import by.easyandroid.webapp.form.appCustomization.AbstractConferenceBaseForm;
import by.easyandroid.webapp.util.Bean;
import by.easyandroid.webapp.util.FacesUtil;

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
	
	@ManagedProperty(value = Bean.SRV_SECTION)
	private SectionService sectionService;
	
	@ManagedProperty(value = Bean.SRV_CAGETORY)
	private CategoryService categoryService;
	
	@ManagedProperty(value = Bean.SRV_REPORTER)
	private ReporterService reporterService;		
	
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
	
	// TODO to interface or base class
	public List<SelectItem> getSelectItems() {
		return FacesUtil.toSelectItems(reports);
	}	

	@Override
	public void delete(ActionEvent event) {
		reportService.delete(deleteDialog.getObject().getId(), template);
		deleteDialog.close();
	}

	@Override
	public void create(ActionEvent event) {
		try {
			if (template != null) {
				Section section = sectionService.get(createDialog.getSectionId());
				Reporter reporter = reporterService.get(createDialog.getReporterId());
				Category category = categoryService.get(createDialog.getCategoryId());
				reportService.add(createDialog.getObject(), section, category, reporter, template);
				createDialog.close();
			}
		} catch (DatabaseServiceException e) {
			// TODO display error message
			e.printStackTrace();
		}
	}

	@Override
	public void edit(ActionEvent event) {
		Section section = sectionService.get(createDialog.getSectionId());
		Reporter reporter = reporterService.get(createDialog.getReporterId());
		Category category = categoryService.get(createDialog.getCategoryId());
		Report report = editDialog.getObject();
		report.setCategory(category);
		report.setSection(section);
		report.setReporter(reporter);
		reportService.save(report);
		
		// Update UI
		ModelUtil.replaceById(getAll(), report);
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

	public SectionService getSectionService() {
		return sectionService;
	}

	public void setSectionService(SectionService sectionService) {
		this.sectionService = sectionService;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public ReporterService getReporterService() {
		return reporterService;
	}

	public void setReporterService(ReporterService reporterService) {
		this.reporterService = reporterService;
	}
}