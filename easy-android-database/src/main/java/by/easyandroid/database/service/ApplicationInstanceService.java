package by.easyandroid.database.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import by.easyandroid.database.service.conference.CategoryService;
import by.easyandroid.database.service.conference.ReportService;
import by.easyandroid.database.service.conference.ReporterService;
import by.easyandroid.database.service.conference.SectionService;
import by.easyandroid.database.service.exception.DatabaseServiceException;
import by.easyandroid.database.util.RelationSavingCopier;
import by.easyandroid.model.ApplicationInstance;
import by.easyandroid.model.User;
import by.easyandroid.model.conference.Category;
import by.easyandroid.model.conference.ConferenceApplicationModel;
import by.easyandroid.model.conference.Report;
import by.easyandroid.model.conference.Reporter;
import by.easyandroid.model.conference.Section;

public class ApplicationInstanceService extends AbstractGenericService<ApplicationInstance> {

	private static final String APPLICATION_INSTANCE = "applicationInstance";

	private SectionService sectionService;
	private CategoryService categoryService;
	private ReporterService reporterService;
	private ReportService reportService;
	private UserService userService;
	
	public ApplicationInstanceService(MongoOperations mongo) {
		super(mongo, ApplicationInstance.class, APPLICATION_INSTANCE);
	}
	
	public ApplicationInstance fullCopy(String id) throws DatabaseServiceException {
		// Copy instance
		ApplicationInstance copied = copy(id);

		// Copy sections
		ConferenceApplicationModel model = copied.getModel();
		RelationSavingCopier<Section> sectionCopier = new RelationSavingCopier<Section>(sectionService);
		RelationSavingCopier<Category> categoryCopier = new RelationSavingCopier<Category>(categoryService);
		RelationSavingCopier<Reporter> reporterCopier = new RelationSavingCopier<Reporter>(reporterService);
		
		List<Report> newReports = new ArrayList<Report>();
		for (Report report : model.getReports()) {
			Report newReport = reportService.copy(report.getId());
			newReports.add(newReport);
			newReport.setSection(sectionCopier.copyOrRelate(newReport.getSection()));
			newReport.setCategory(categoryCopier.copyOrRelate(newReport.getCategory()));
			newReport.setReporter(reporterCopier.copyOrRelate(newReport.getReporter()));
			reportService.save(newReport);
		}
		
		model.setReports(newReports);
		model.setSections(sectionCopier.getCopiedObjects());
		model.setCategories(categoryCopier.getCopiedObjects());
		model.setReporters(reporterCopier.getCopiedObjects());
		
		save(copied);
		
		return copied;
	}

	public ApplicationInstance copyFromTemplate(String templateId, String newAppTitle, User currentUser) throws DatabaseServiceException {
		if (currentUser == null) {
			throw new DatabaseServiceException("Can not copy application model because no current user in the system");			
		}
		
		ApplicationInstance initialInstance = findTemplateInstance(templateId);
		
		// Copy instance, remove "template" flag to false because new instance is user customizable now
		ApplicationInstance copiedInstance = fullCopy(initialInstance.getId());
		copiedInstance.setName(newAppTitle);
		copiedInstance.setTemplateInstance(false);
		save(copiedInstance);
		
		currentUser.getApplications().add(copiedInstance);
		userService.save(currentUser);
		
		return copiedInstance;
	}
	
	public ApplicationInstance findTemplateInstance(String templateId) {
		Query q = new Query();
		q.addCriteria(Criteria.where("template.$id").is(new ObjectId(templateId)));
		return findOne(q);
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

	public ReportService getReportService() {
		return reportService;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}