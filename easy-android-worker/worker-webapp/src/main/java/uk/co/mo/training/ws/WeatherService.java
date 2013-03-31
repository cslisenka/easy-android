package uk.co.mo.training.ws;

import javax.jws.WebParam;
import javax.jws.WebService;

import uk.co.mo.training.dto.Weather;

@WebService
public interface WeatherService {
	
	public Weather currentWeather(@WebParam(name="city")String city); 

}
