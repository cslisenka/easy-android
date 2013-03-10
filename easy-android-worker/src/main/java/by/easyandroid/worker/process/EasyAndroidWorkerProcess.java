package by.easyandroid.worker.process;

import java.io.File;
import java.util.Date;
import java.util.List;

import by.easyandroid.framework.Copier;
import by.easyandroid.framework.exception.TaskExecutionException;
import by.easyandroid.model.conference.Category;
import by.easyandroid.model.conference.ConferenceApplicationModel;
import by.easyandroid.model.conference.Report;
import by.easyandroid.model.conference.Reporter;
import by.easyandroid.model.conference.Section;
import by.easyandroid.template.adapter.FileEntry;
import by.easyandroid.template.adapter.conference.ConferenceAdapter;
import by.easyandroid.template.adapter.exception.TemplateAdapterException;
import by.easyandroid.worker.builder.AndroidApkBuilder;

public class EasyAndroidWorkerProcess {

	private Copier copier;
	
	private ConferenceAdapter adapter;
	private ConferenceApplicationModel model;
	
	private String pathToSources;	
	
	public void execute() {
		// TODO 1. Get template data and model from database
		createFakeModel();
		
		// TODO 2. Download sources to working dir
		// TODO 3. Find adapter for this template and call it
		createFakeAdapter();
		
		// TODO 4. Copy converted files to working directory and flush
		try {
			copyConvertedFilesToWorkingDirectory();
		} catch (TemplateAdapterException e) {
			// TODO throw process exception
			e.printStackTrace();
		} catch (TaskExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO 5. Run apk building
		buildApplication();
		// TODO 6. Publish results
	}

	private void buildApplication() {
		AndroidApkBuilder builder = new AndroidApkBuilder();
		builder.build(copier.getOutputDir());
	}

	private void copyConvertedFilesToWorkingDirectory() throws TemplateAdapterException, TaskExecutionException {
		List<FileEntry> convertedFiles = adapter.convert();
		copier = new Copier();
		// TODO now we copy to target, in future we need to copy to special directory on server
		copier.setOutputDir(new File("").getAbsolutePath() + "/target/buildResults");
		copier.add(new File("").getAbsolutePath() + "/../Conference");
		for (FileEntry entry : convertedFiles) {
			copier.createFile(entry.getPathInProject(), entry.getFileContent());
		}
		
		copier.flush();
	}

	private void createFakeAdapter() {
		adapter = new ConferenceAdapter(model);
		
		pathToSources = new File("").getAbsolutePath() + "/../Conference";
		// TODO this is temporary solution, we need to put project sources into target folder during build
		adapter.setSourcesPath(pathToSources);		
	}

	private void createFakeModel() {
		model = new ConferenceApplicationModel();
		model.getInformation().setAbout("Some text about conference");
		model.getInformation().setWebsiteUrl("http://test.conference.com");
		model.getInformation().setTitle("My test conference");
		
		// Create fake sections
		Section s1 = addSection("1", "section 1");
		Section s2 = addSection("2", "section 2");
		
		// Create fake categories
		Category c1 = addCategory("1", "category 1");
		Category c2 = addCategory("2", "category 2");
		
		Reporter r1 = addReporter("1", "reporter 1");
		Reporter r2 = addReporter("2", "reporter 2");
		
		addReport("1", "report 1", s1, c1, r1);
		addReport("2", "report 2", s2, c2, r2);
	}
	
	private void addReport(String id, String name, Section s1, Category c1, Reporter r1) {
		Report r = new Report();
		r.setId(id);
		r.setTitle(name);
		r.setReporter(r1);
		r.setSection(s1);
		r.setCategory(c1);
		r.setDescription("description " + name);
		r.setTime(new Date(2013, 10, 10, 5, 30));
		model.getReports().add(r);
	}

	private Reporter addReporter(String id, String name) {
		Reporter r = new Reporter(); 
		r.setId(id);
		r.setName(name);
		r.setDescription("description " + name);
		r.setEmail("email " + name);
		r.setPosition("position " + name);
		r.setCompany("company " + name);
		model.getReporters().add(r);
		return r;
	}

	private Category addCategory(String id, String name) {
		Category c = new Category();
		c.setName(name);
		c.setId(id);
		model.getCategories().add(c);
		return c;
	}	
	
	private Section addSection(String id, String name) {
		Section s = new Section();
		s.setName(name);
		s.setId(id);
		model.getSections().add(s);
		return s;
	}		
}