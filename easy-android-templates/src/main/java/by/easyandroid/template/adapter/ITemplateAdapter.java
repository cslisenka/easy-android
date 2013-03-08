package by.easyandroid.template.adapter;

import java.util.List;

/**
 * Adapter takes application data model as input and produces 
 * source files. Then we can put this files to android template
 * project and build apk.
 * 
 * @author kslisenko
 */
public interface ITemplateAdapter {

	List<FileEntry> convert();
}