package by.easyandroid.worker.client;

import org.junit.Before;
import org.junit.Test;

public class TestWorkerWsClient {

	private static final String wsdl = "http://localhost:8081/webservice/appbuild?wsdl";
	private static final String ns = "http://service.webapp.worker.easyandroid.by/";
	private static final String nsLocalPart = "AndroidAppBuildWSImplService";	
	private WorkerWsClient client;
	
	@Before
	public void setUp() {
		client = new WorkerWsClient(wsdl, ns, nsLocalPart);
	}
	
	@Test
	public void testClientWsCall() {
		// TODO populate database before test
		client.callAndroidAppBuildWS("5145946884ae06049278c93b");
	}
	
//	@Test(expected = ApplicationServiceException.class)
//	public void testNullId() throws ApplicationServiceException {
//		client.callAndroidAppBuildWS(null);
//	}
//	
//	@Test(expected = ApplicationServiceException.class)
//	public void testWrongId() throws ApplicationServiceException {
//		client.callAndroidAppBuildWS("wrongId");
//	}		
}