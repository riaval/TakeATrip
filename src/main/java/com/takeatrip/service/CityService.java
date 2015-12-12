package com.takeatrip.service;

import java.util.List;

import com.takeatrip.domain.City;

public interface CityService {
	City findById(String id);
	List<City> searchFirstLetters(String beginning);
	public void add(City city);
}
