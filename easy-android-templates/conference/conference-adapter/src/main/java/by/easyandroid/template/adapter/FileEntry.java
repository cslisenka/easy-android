package by.easyandroid.template.adapter;

/**
 * Represents in-memory text file, produced by adapter 
 * @author kslisenko
 */
public class FileEntry {

	private String pathInProject;
	
	private String fileContent;

	public FileEntry(String pathInProject, String fileContent) {
		this.pathInProject = pathInProject;
		this.fileContent = fileContent;
	}

	public String getPathInProject() {
		return pathInProject;
	}

	public void setPathInProject(String pathInProject) {
		this.pathInProject = pathInProject;
	}

	public String getFileContent() {
		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}
}