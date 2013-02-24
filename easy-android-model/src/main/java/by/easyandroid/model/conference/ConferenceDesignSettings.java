package by.easyandroid.model.conference;

/**
 * Attributes of conference android application design and UI
 * 
 * @author kslisenko
 */
public class ConferenceDesignSettings {

	private String backgroundColor;
	
	private String textColor;
	
	private String conferenceLogoUrl;

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	public String getConferenceLogoUrl() {
		return conferenceLogoUrl;
	}

	public void setConferenceLogoUrl(String conferenceLogoUrl) {
		this.conferenceLogoUrl = conferenceLogoUrl;
	}
}