package by.easyandroid.template.adapter.conference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import by.easyandroid.model.conference.ConferenceApplicationModel;
import by.easyandroid.template.adapter.FileEntry;
import by.easyandroid.template.adapter.ITemplateAdapter;
import by.easyandroid.template.adapter.exception.TemplateAdapterException;

/**
 * Adapter for conference application
 * @author kslisenko
 */
public class ConferenceAdapter implements ITemplateAdapter {

	private String sourcesPath;
	private ConferenceApplicationModel model;
	
	public ConferenceAdapter(ConferenceApplicationModel model) {
		this.model = model;
	}

	public List<FileEntry> convert() throws TemplateAdapterException {
		List<FileEntry> result = new ArrayList<FileEntry>();

		result.add(createAdoutConferenceWebPage());
		
		// TODO conference_data.xml
		// TODO about.html
		// TODO strings.xml
		
		return result;
	}

	private FileEntry createAdoutConferenceWebPage() throws TemplateAdapterException {
		// Get webpage from asets
		File webpage = new File(sourcesPath + File.separator + "assets" + File.separator + "about.html");
		if (!webpage.exists()) {
			throw new TemplateAdapterException("Webpage 'about.html' is not exists in project sources");
		}
		
		try {
			String webPageContent = FileUtils.readFileToString(webpage);
			Document html = Jsoup.parse(webPageContent);
			String bodyText = html.body().text();
			FileEntry file = new FileEntry("assets/about.html", webPageContent.replace(bodyText, model.getInformation().getAbout()));
			return file;
		} catch (IOException e) {
			throw new TemplateAdapterException("Erorr when reading 'about.html' file in project sources", e);
		}
	}

	public void setSourcesPath(String sourcesPath) {
		this.sourcesPath = sourcesPath;
	}
}