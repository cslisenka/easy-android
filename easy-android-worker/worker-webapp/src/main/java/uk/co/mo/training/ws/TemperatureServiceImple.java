package uk.co.mo.training.ws;

import javax.jws.WebService;


import org.springframework.stereotype.Service;
@WebService(endpointInterface = "uk.co.mo.training.ws.TemperatureService",serviceName="temperatureService")
@Service
public class TemperatureServiceImple implements TemperatureService {


	public double toCelsius(double fahrenheit) {
		double celsius = (5.0 / 9.0) * (fahrenheit - 32.0);
		return celsius;
	}


	public double toFahrenheit(double celsius) {
		double fahrenheit = (celsius *1.8)+32.0;
		return fahrenheit;
	}

}
