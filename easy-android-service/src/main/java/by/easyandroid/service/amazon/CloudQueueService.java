package by.easyandroid.service.amazon;

import java.util.List;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;

public class CloudQueueService {

	private AmazonSQS sqs;
	private String queueUrl;
	
	public CloudQueueService(AmazonSQS sqs, String queueUrl) {
		this.sqs = sqs;
		this.queueUrl = queueUrl;
	}
	
	public void sendMessage(String messageBody) {
		// TODO to think about exception handling		
		SendMessageRequest sendRequest = new SendMessageRequest(queueUrl, messageBody);
		sqs.sendMessage(sendRequest);
	}

	/**
	 * Receives message and then deletes it from queue.
	 * @return
	 */
	public String receiveDeleteMessage() {
		// TODO to think about exception handling		
		Message message = receiveMessage();
		if (message != null) {
			deleteMessage(message.getReceiptHandle());
			return message.getBody();
		}
		
		return null;
	}
	
	public AmazonSQS getSqs() {
		return sqs;
	}

	public void setSqs(AmazonSQS sqs) {
		this.sqs = sqs;
	}

	public String getQueueUrl() {
		return queueUrl;
	}

	public void setQueueUrl(String queueUrl) {
		this.queueUrl = queueUrl;
	}
	
	/**
	 * Receives message and deletes it from queue. 
	 * TO THINK: To make more perfomance, we can receive batches of messages (up to 10) and process them in a loop.
	 * And return list of messages.
	 * @return received message object or null if no messages are in the queue
	 */
	protected Message receiveMessage() {
		// TODO to think about exception handling		
		ReceiveMessageRequest request = new ReceiveMessageRequest(queueUrl);
		request.setMaxNumberOfMessages(1);
		List<Message> messages = sqs.receiveMessage(request).getMessages();
		if (messages.size() > 0) {
			return messages.get(0);
		}
		
		return null; 
	}
	
	/**
	 * Deletes message from queue
	 * @param messageHandler value, returned from message.getReceiptHandle()
	 */
	protected void deleteMessage(String messageHandler) {
		// TODO to think about exception handling
		DeleteMessageRequest deleteRequest = new DeleteMessageRequest(queueUrl, messageHandler);
		sqs.deleteMessage(deleteRequest);
	}	
}