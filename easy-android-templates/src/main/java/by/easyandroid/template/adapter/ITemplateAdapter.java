package by.easyandroid.template.adapter;

import java.util.List;

import by.easyandroid.template.adapter.exception.TemplateAdapterException;

/**
 * Adapter takes application data model as input and produces 
 * source files. Then we can put this files to android template
 * project and build apk.
 * 
 * @author kslisenko
 */
public interface ITemplateAdapter {

	void setSourcesPath(String path);
	
	List<FileEntry> convert() throws TemplateAdapterException;
}