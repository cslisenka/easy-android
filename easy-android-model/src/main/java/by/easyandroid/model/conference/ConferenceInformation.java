package by.easyandroid.model.conference;

public class ConferenceInformation {

	/**
	 * If there is no logo image, title would be displayed in application header
	 */
	private String title;
	
	/**
	 * Html content of screen "About conference"
	 */
	private String about;
	
	/**
	 * For conference web-site menu button
	 */
	private String websiteUrl;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}
}