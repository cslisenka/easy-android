package by.easyandroid.worker.webapp;

import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService
public interface HelloService {

	@WebMethod
	String sayHello(String name);
}