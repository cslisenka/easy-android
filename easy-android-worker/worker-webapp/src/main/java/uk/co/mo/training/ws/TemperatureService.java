package uk.co.mo.training.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface TemperatureService {
	
	@WebMethod
	public double toCelsius(@WebParam(name="fahrenheit") double fahrenheit);
	@WebMethod
	public double toFahrenheit(@WebParam(name="celsius") double celsius);

}
