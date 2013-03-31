package uk.co.mo.training.ws;

import java.util.Random;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

import uk.co.mo.training.dto.Weather;

@WebService(endpointInterface = "uk.co.mo.training.ws.WeatherService",serviceName="weatherService")
@Service
public class WeatherServiceImple implements WeatherService {

	public Weather currentWeather(String city) {
		Weather weather = new Weather();
		weather.setCity(city);
		weather.setCelsius(currentTemperature(city));
		weather.setFahrenheit(new TemperatureServiceImple().toFahrenheit(weather.getCelsius()));
		return weather;
	}
	
	private double currentTemperature(String city)
	{
		Random randomGenerator = new Random();	   
	    int randomInt = randomGenerator.nextInt(100);
		return randomInt;
	}

}
