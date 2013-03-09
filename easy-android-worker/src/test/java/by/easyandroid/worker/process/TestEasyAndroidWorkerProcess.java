package by.easyandroid.worker.process;

import org.junit.Before;
import org.junit.Test;

public class TestEasyAndroidWorkerProcess {

	private EasyAndroidWorkerProcess process;
	
	@Before
	public void setUp() {
		process = new EasyAndroidWorkerProcess();
	}
	
	@Test
	public void testprocessExecution() {
		process.execute();
	}
}