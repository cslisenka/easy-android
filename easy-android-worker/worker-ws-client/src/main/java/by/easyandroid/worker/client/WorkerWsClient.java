package by.easyandroid.worker.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

/**
 * Utility to call worker app webservices
 * 
 * @author kslisenko
 * 
 */
public class WorkerWsClient {

	public void callAndroidAppBuildWS(String applicationObjId) {
		try {
			URL url = new URL("http://localhost:8081/webservice/buildpp?wsdl");
			QName qname = new QName("http://service.webapp.worker.easyandroid.by/", "AndroidAppBuildWSImplService");
			AndroidAppBuildWSImplService service = new AndroidAppBuildWSImplService(url, qname);
			AndroidAppBuildWS client = service.getAndroidAppBuildWSImplPort();
			client.buildAndroidApplication(applicationObjId);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			// TODO throw ApplicationServiceException
		} catch (ApplicationServiceException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}