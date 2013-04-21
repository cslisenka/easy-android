package by.easyandroid.worker.pooler;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.easyandroid.service.amazon.CloudQueueService;
import by.easyandroid.worker.client.WorkerWsClient;

public class Main {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/pooler-context.xml");
		WorkerWsClient wsClient = (WorkerWsClient) context.getBean(WorkerWsClient.class);
		CloudQueueService queueService = context.getBean(CloudQueueService.class);

		// Pool amazon sqs in a infinity loop
		while (true) {
			try {
				// TODO handle exceptions
				String applicationId = queueService.receiveDeleteMessage();
				System.out.println("Received message " + applicationId);
				if (applicationId != null) {
					// TODO use logging
					System.out.println("Call ws client");
					wsClient.callAndroidAppBuildWS(applicationId);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}