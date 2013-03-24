package by.easyandroid.webapp.util;


/**
 * Commonly used ManagedProperty names in web application
 * @author kslisenko
 */
public interface Bean {

	// Services	
	public static final String SRV_CATEGORY = "#{categoryService}";
	public static final String SRV_REPORTER = "#{reporterService}";
	public static final String SRV_REPORT = "#{reportService}";
	public static final String SRV_SECTION = "#{sectionService}";
	public static final String SRV_CAGETORY = "#{categoryService}";
	public static final String SRV_APPLICATION = "#{applicationInstanceService}";
	public static final String SRV_TEMPLATE = "#{templateService}";
	
	public static final String SRV_COMPILATION = "#{compilationService}";
	
	// Beans with business logic
	public static final String BN_USER = "#{userBean}";

	// Forms
	public static final String FRM_CONFERENCE = "#{conferenceForm}";
}