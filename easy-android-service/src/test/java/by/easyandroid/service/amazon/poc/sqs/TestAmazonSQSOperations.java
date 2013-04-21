package by.easyandroid.service.amazon.poc.sqs;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;

public class TestAmazonSQSOperations {

	private static final String TEST_QUEUE_URL = "https://queue.amazonaws.com/104646438091/by-easyandroid-testing";
	
	private AmazonSQS sqs;
	
	@Before
	public void setUp() throws IOException {
		InputStream in = TestAmazonSQSOperations.class.getResourceAsStream("/aws-credentials.properties");
		sqs = new AmazonSQSClient(new PropertiesCredentials(in));
	}
	
	@Test
	public void testViewQueues() {
		System.out.println("Listing all queues in your account.\n");
        for (String queueUrl : sqs.listQueues().getQueueUrls()) {
            System.out.println("QueueUrl: " + queueUrl);
        }
	}
	
	@Test
	public void testSendReceiveDeleteMessage() {
		// Send message
		SendMessageRequest sendMessageReq = new SendMessageRequest(TEST_QUEUE_URL, "test message 111");
		sqs.sendMessage(sendMessageReq);
		
		// Receive message
		ReceiveMessageRequest receiveRequest = new ReceiveMessageRequest(TEST_QUEUE_URL);
		receiveRequest.setMaxNumberOfMessages(10);
		ReceiveMessageResult result = sqs.receiveMessage(receiveRequest);
		List<Message> messages = result.getMessages();
		System.out.println("Received " + messages.size() + " messages");
		Assert.assertEquals(1, messages.size());
		Assert.assertEquals("test message 111", messages.get(0).getBody());
		
		// Delete message
		for (int i = 0; i < messages.size(); i++) {
			String handle = messages.get(i).getReceiptHandle();
			System.out.println("Handle: " + handle);
			System.out.println("Body: " + messages.get(i).getBody());
			DeleteMessageRequest deleteRequest = new DeleteMessageRequest(TEST_QUEUE_URL, handle);
			sqs.deleteMessage(deleteRequest);
		}
	}
	
	@Test
	public void testPollingRequests() throws InterruptedException {
		new Thread() {
			@Override
			public void run() {
				ReceiveMessageRequest receiveRequest = new ReceiveMessageRequest(TEST_QUEUE_URL);
				receiveRequest.setMaxNumberOfMessages(1);
				ReceiveMessageResult result = sqs.receiveMessage(receiveRequest);
				List<Message> messages = result.getMessages();
				Assert.assertEquals(1, messages.size());
				Assert.assertEquals("message with long pooling", messages.get(0).getBody());
				System.out.println("Received message " + messages.get(0).getBody());
				DeleteMessageRequest deleteRequest = new DeleteMessageRequest(TEST_QUEUE_URL, messages.get(0).getBody());
				sqs.deleteMessage(deleteRequest);
			}
		}.start();
		
		Thread.sleep(3000);
		System.out.println("Send message");
		SendMessageRequest sendMessageReq = new SendMessageRequest(TEST_QUEUE_URL, "message with long pooling");
		sqs.sendMessage(sendMessageReq);
	}
}