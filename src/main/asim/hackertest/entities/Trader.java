package asim.hackertest.entities;

import asim.hackertest.functionalInterfaces.CreateTrader;

public class Trader implements CreateTrader
{
	private String name;
	private String city;

	/*
	public Trader()
	{	 
	}
	*/

	public Trader(String name, String city)
	{
		this.name = name.trim();
		this.city = city.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Override
	public String toString() {
		return "Trader [name=" + name + ", city=" + city + "]";
	}

	@Override
	public void trader(String name, String location) {
		// TODO Auto-generated method stub
		System.out.println("Trader [name=" + name + ", city=" + city + "]");
	}

}
