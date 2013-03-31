package uk.co.mo.training.ws;


import org.springframework.stereotype.Component;


@Component
public class WeatherWebservice {
	
	private String city;
	private String country;
	private double temp;
	
	public WeatherWebservice()
	{
		System.out.println("Weather Webservice");
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public double getTemp() {
		return temp;
	}
	public void setTemp(double temp) {
		this.temp = temp;
	} 
	
	

}
