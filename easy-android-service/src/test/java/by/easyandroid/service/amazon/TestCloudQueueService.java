package by.easyandroid.service.amazon;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/service-context.xml")
public class TestCloudQueueService {
	
	private static final String TEST_MES_BODY = "test body";
	
	@Autowired
	private CloudQueueService queyeService;
	
	@Test
	public void testSendReceiveDeleteMessage() {
		queyeService.sendMessage(TEST_MES_BODY);
		Assert.assertEquals(TEST_MES_BODY, queyeService.receiveDeleteMessage());
	}
	
	@Test
	public void testPooling() throws InterruptedException {
		new Thread() {
			@Override
			public void run() {
				Assert.assertEquals(TEST_MES_BODY, queyeService.receiveDeleteMessage());
			}
		}.start();
		
		// Sleep 5 seconds to test queue long pooling
		Thread.sleep(5000);
		queyeService.sendMessage(TEST_MES_BODY);
	}
	
	// TODO test exception handling
}