package by.easyandroid.template.conference.model;

public class Section extends Identity {

	/**
	 * The name of section
	 */
	private String name;
	
	/**
	 * Image with map of conference building and marks where section is
	 */
	private String mapImageUrl;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMapImageUrl() {
		return mapImageUrl;
	}

	public void setMapImageUrl(String mapImageUrl) {
		this.mapImageUrl = mapImageUrl;
	}

	@Override
	public String toString() {
		return name;
	}
}