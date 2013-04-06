package by.easyandroid.worker.webapp;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import by.easyandroid.worker.client.HelloWorldServiceWS;
import by.easyandroid.worker.client.HelloWorldServiceWSImplService;

public class HelloServiceTest {

	// TODO start jetty programmatically to test web service or use mocks
	@Ignore
	@Test
	public void testHelloService() throws MalformedURLException {
		// Generation may being done with local wsdl
		URL url = new URL("http://localhost:8081/webservice/hello?wsdl");
        QName qname = new QName("http://webapp.worker.easyandroid.by/", "HelloWorldServiceWSImplService");
        
		HelloWorldServiceWSImplService s = new HelloWorldServiceWSImplService(url, qname);
		HelloWorldServiceWS client = s.getHelloWorldServiceWSImplPort();
		Assert.assertEquals("JAX-WS + Spring!", client.getHelloWorld());
	}
}