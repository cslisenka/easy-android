package by.easyandroid.framework.showcase;

import java.io.File;
import java.io.PrintStream;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.junit.Test;

public class TestBuildAndroidApp {

	private static final String BUILDFILE = "src/test/resources/showcase/ant/android/MyAndroidAppProject/build.xml";

	@Test
	public void testExecuteAndScript() {
		File buildFile = new File(BUILDFILE);
		
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
			p.setBaseDir(new File("src/test/resources/showcase/ant/android/MyAndroidAppProject"));
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