package by.easyandroid.template.adapter.conference;

import java.util.ArrayList;
import java.util.List;

import by.easyandroid.model.conference.ConferenceApplicationModel;
import by.easyandroid.template.adapter.FileEntry;
import by.easyandroid.template.adapter.ITemplateAdapter;

public class ConferenceAdapter implements ITemplateAdapter {

	private ConferenceApplicationModel model;
	
	public ConferenceAdapter(ConferenceApplicationModel model) {
		this.model = model;
	}

	public List<FileEntry> convert() {
		List<FileEntry> result = new ArrayList<FileEntry>();
		
		// TODO get data from model and produce files
		
		// TODO conference_data.xml
		// TODO about.html
		// TODO strings.xml
		
		return result;
	}
}