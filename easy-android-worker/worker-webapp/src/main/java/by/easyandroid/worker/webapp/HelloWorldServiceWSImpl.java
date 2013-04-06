package by.easyandroid.worker.webapp;

import javax.jws.WebService;

@WebService(endpointInterface = "by.easyandroid.worker.webapp.HelloWorldServiceWS")
public class HelloWorldServiceWSImpl implements HelloWorldServiceWS {

	private HelloWorldBo helloWorldBo;

//	@WebMethod(exclude = true)
	public void setHelloWorldBo(HelloWorldBo helloWorldBo) {
		this.helloWorldBo = helloWorldBo;
	}

	@Override
//	@WebMethod(operationName = "getHelloWorld")
	public String getHelloWorld() {
		return helloWorldBo.getHelloWorld();
	}
}
