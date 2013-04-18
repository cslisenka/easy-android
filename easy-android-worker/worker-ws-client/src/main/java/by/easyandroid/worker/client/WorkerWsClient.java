package by.easyandroid.worker.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

/**
 * Class for executing appbuild web service.
 * 
 * @author kslisenko
 * 
 */
public class WorkerWsClient {

	private String wsdlUrl;
	private String namespace;
	private String nsLocalPart;

	public WorkerWsClient(String wsdlUrl, String namespace, String nsLocalPart) {
		this.wsdlUrl = wsdlUrl;
		this.namespace = namespace;
		this.nsLocalPart = nsLocalPart;
	}

	public void callAndroidAppBuildWS(String applicationObjId) {//throws by.easyandroid.service.exception.ApplicationServiceException {
		try {
			URL url = new URL(wsdlUrl);
			QName qname = new QName(namespace, nsLocalPart);
			AndroidAppBuildWSImplService service = new AndroidAppBuildWSImplService(url, qname);
			AndroidAppBuildWS client = service.getAndroidAppBuildWSImplPort();
			client.buildAndroidApplication(applicationObjId);
		} catch (MalformedURLException e) {
//			throw new by.easyandroid.service.exception.ApplicationServiceException(e);
		} catch (ApplicationServiceException_Exception e) {
//			throw new by.easyandroid.service.exception.ApplicationServiceException(e);
		}
	}
}