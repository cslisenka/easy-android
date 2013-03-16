package by.easyandroid.webapp;

import javax.annotation.PostConstruct;

/**
 * Abstract class for webpages backing beans
 * 
 * @author kslisenko
 */
public abstract class AbstractBaseForm {

	/**
	 * Method calls after bean initialization and all dependencies are injected
	 * @return
	 */
	public abstract void init() throws Exception;
	
	@PostConstruct
	public final void postConstruct() {
		try {
			init();
		} catch (Exception e) {
			// TODO redirect to error page which shows stacktrace
			e.printStackTrace();
		}
	}
}