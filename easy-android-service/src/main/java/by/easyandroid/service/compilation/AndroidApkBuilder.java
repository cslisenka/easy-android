package by.easyandroid.service.compilation;

import java.io.File;
import java.io.PrintStream;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

public class AndroidApkBuilder {
	
	public void build(String workingDirectoryPath) {
		// TODO now build file is in the working directory, but in future we should store it in this project resources
		File buildFile = new File(workingDirectoryPath + "/build.xml");
		
		// Caught print stream for testing
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		PrintStream out = new PrintStream(baos);
		
		Project p = new Project();
		p.setUserProperty("ant.file", buildFile.getAbsolutePath());		
		DefaultLogger consoleLogger = createAntLogger(System.err, System.out);
		p.addBuildListener(consoleLogger);

		try {
			p.fireBuildStarted();
			p.init();
			ProjectHelper helper = ProjectHelper.getProjectHelper();
			p.setBaseDir(new File(workingDirectoryPath));
			p.addReference("ant.projectHelper", helper);
			helper.parse(p, buildFile);
			p.executeTarget("debug");
			p.fireBuildFinished(null);
		} catch (BuildException e) {
			p.fireBuildFinished(e);
			throw e;
		}
		
//		System.out.println(baos.toString());
//		Assert.assertTrue(baos.toString().contains("Hello World"));
//		Assert.assertTrue(baos.toString().contains("BUILD SUCCESSFUL"));
	}

	private DefaultLogger createAntLogger(PrintStream err, PrintStream out) {
		DefaultLogger consoleLogger = new DefaultLogger();
		consoleLogger.setErrorPrintStream(err);
		consoleLogger.setOutputPrintStream(out);
		consoleLogger.setMessageOutputLevel(Project.MSG_INFO);
		return consoleLogger;
	}
}
