package by.easyandroid.worker.webapp;

import javax.jws.WebService;

@WebService(endpointInterface = "by.easyandroid.worker.webapp.HelloWorldServiceWS")
public class HelloWorldServiceWSImpl implements HelloWorldServiceWS {

	private HelloWorldBo helloWorldBo;

	public void setHelloWorldBo(HelloWorldBo helloWorldBo) {
		this.helloWorldBo = helloWorldBo;
	}

	@Override
	public String getHelloWorld() {
		return helloWorldBo.getHelloWorld();
	}
}