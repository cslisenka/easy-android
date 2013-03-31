package uk.co.mo.training.dto;

import javax.xml.bind.annotation.XmlTransient;

public class Weather {
	
	private String city;
	private double celsius;
	private double fahrenheit;
	
	@SuppressWarnings("restriction")
	@XmlTransient
	private String location;
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public double getCelsius() {
		return celsius;
	}
	public void setCelsius(double celsius) {
		this.celsius = celsius;
	}
	public double getFahrenheit() {
		return fahrenheit;
	}
	public void setFahrenheit(double fahrenheit) {
		this.fahrenheit = fahrenheit;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	

}
