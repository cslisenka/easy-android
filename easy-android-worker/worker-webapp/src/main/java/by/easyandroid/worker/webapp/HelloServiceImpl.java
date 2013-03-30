package by.easyandroid.worker.webapp;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHello(String name) {
		return "Say hello from jax-ws " + name;
	}
}