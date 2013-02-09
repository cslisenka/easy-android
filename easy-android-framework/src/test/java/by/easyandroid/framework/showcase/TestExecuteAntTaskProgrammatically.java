package by.easyandroid.framework.showcase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import junit.framework.Assert;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.junit.Test;

public class TestExecuteAntTaskProgrammatically {

	private static final String BUILDFILE = "src/test/resources/showcase/ant/SimpleAntBuildfile.xml";

	@Test
	public void testExecuteAndScript() {
		File buildFile = new File(BUILDFILE);
		
		// Caught print stream for testing
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(baos);
		
		Project p = new Project();
		p.setUserProperty("ant.file", buildFile.getAbsolutePath());		
		DefaultLogger consoleLogger = createAntLogger(System.err, out);
		p.addBuildListener(consoleLogger);

		try {
			p.fireBuildStarted();
			p.init();
			ProjectHelper helper = ProjectHelper.getProjectHelper();
			p.addReference("ant.projectHelper", helper);
			helper.parse(p, buildFile);
			p.executeTarget(p.getDefaultTarget());
			p.fireBuildFinished(null);
		} catch (BuildException e) {
			p.fireBuildFinished(e);
			throw e;
		}
		
		System.out.println(baos.toString());
		Assert.assertTrue(baos.toString().contains("Hello World"));
		Assert.assertTrue(baos.toString().contains("BUILD SUCCESSFUL"));
	}

	private DefaultLogger createAntLogger(PrintStream err, PrintStream out) {
		DefaultLogger consoleLogger = new DefaultLogger();
		consoleLogger.setErrorPrintStream(err);
		consoleLogger.setOutputPrintStream(out);
		consoleLogger.setMessageOutputLevel(Project.MSG_INFO);
		return consoleLogger;
	}
}