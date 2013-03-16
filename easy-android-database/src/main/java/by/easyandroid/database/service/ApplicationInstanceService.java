package by.easyandroid.database.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.MongoOperations;

import by.easyandroid.database.service.conference.CategoryService;
import by.easyandroid.database.service.conference.ReportService;
import by.easyandroid.database.service.conference.ReporterService;
import by.easyandroid.database.service.conference.SectionService;
import by.easyandroid.database.service.exception.DatabaseServiceException;
import by.easyandroid.model.ApplicationInstance;
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
	
	public ApplicationInstanceService(MongoOperations mongo) {
		super(mongo, ApplicationInstance.class, APPLICATION_INSTANCE);
	}
	
	public ApplicationInstance fullCopy(String id) throws DatabaseServiceException {
		// Copy instance
		ApplicationInstance copied = copy(id);

		// Copy sections
		ConferenceApplicationModel model = copied.getModel();
		Map<String, Section> oldNewSections = new HashMap<String, Section>();
		Map<String, Category> oldNewCategories = new HashMap<String, Category>();
		Map<String, Reporter> oldNewReporters = new HashMap<String, Reporter>();
		List<Report> newReports = new ArrayList<Report>();
		for (Report oneReport : model.getReports()) {
			Report newReport = reportService.copy(oneReport.getId());
			newReports.add(newReport);
			
			// Copy section
			Section section = newReport.getSection();
			if (!oldNewSections.containsKey(section.getId())) {
				Section newSection = sectionService.copy(section.getId());
				oldNewSections.put(section.getId(), newSection);
				section = newSection;
			} else {
				section = oldNewSections.get(section.getId());
			}
			
			newReport.setSection(section);
			
			// Copy category
			Category category = newReport.getCategory();
			if (!oldNewCategories.containsKey(category.getId())) {
				Category newCategory = categoryService.copy(category.getId());
				oldNewCategories.put(category.getId(), newCategory);
				category = newCategory;
			} else {
				category = oldNewCategories.get(category.getId());
			}
			
			Reporter reporter = newReport.getReporter();
			if (!oldNewReporters.containsKey(reporter.getId())) {
				Reporter newReporter = reporterService.copy(reporter.getId());
				oldNewReporters.put(reporter.getId(), newReporter);
				reporter = newReporter;
			} else {
				reporter = oldNewReporters.get(reporter.getId());
			}
			
			newReport.setReporter(reporter);
			
			reportService.save(newReport);
		}
		
		model.setReports(newReports);
		model.setSections(new ArrayList<Section>(oldNewSections.values()));
		model.setCategories(new ArrayList<Category>(oldNewCategories.values()));
		model.setReporters(new ArrayList<Reporter>(oldNewReporters.values()));
		
		save(copied);
		
		return copied;
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
}