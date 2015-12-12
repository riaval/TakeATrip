package com.takeatrip.domain;

import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cities")
public class City{

	@Id
    private String id;
	private String name;
	private String region;
	private String country;
	private int countryRate;
	private int priceLive;
	private int priceFood;
	private Date date;
	public City() {	}

	public City(String name, String country, String region, int countryRate, int priceLive, int priceFood, Date date) {
		this.name = name;
		this.region = region;
		this.country = country;
		this.countryRate = countryRate;
		this.priceLive = priceLive;
		this.priceFood = priceFood;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getCountryRate() {
		return countryRate;
	}

	public void setCountryRate(int countryRate) {
		this.countryRate = countryRate;
	}

	public int getPriceLive() {
		return priceLive;
	}

	public void setPriceLive(int priceLive) {
		this.priceLive = priceLive;
	}

	public int getPriceFood() {
		return priceFood;
	}

	public void setPriceFood(int priceFood) {
		this.priceFood = priceFood;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((region == null) ? 0 : region.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		return true;
	}
}
