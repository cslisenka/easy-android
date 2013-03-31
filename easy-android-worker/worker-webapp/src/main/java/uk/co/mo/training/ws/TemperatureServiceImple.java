package uk.co.mo.training.ws;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

@SuppressWarnings("restriction")
@WebService(endpointInterface = "uk.co.mo.training.ws.TemperatureService",serviceName="temperatureService")
@Service
public class TemperatureServiceImple implements TemperatureService {


	public double toCelsius(double fahrenheit) {
		double celsius = (5.0 / 9.0) * (fahrenheit - 32.0);
		//C° = (5 / 9)x (F° - 32)  
		return celsius;
	}


	public double toFahrenheit(double celsius) {
		double fahrenheit = (celsius *1.8)+32.0;
		//F° = (C° x 1.8) + 32
		return fahrenheit;
	}

}
