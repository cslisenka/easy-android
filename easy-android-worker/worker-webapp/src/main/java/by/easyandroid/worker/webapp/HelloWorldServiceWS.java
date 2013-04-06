package by.easyandroid.worker.webapp;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface HelloWorldServiceWS {

	@WebMethod
	String getHelloWorld();
}
