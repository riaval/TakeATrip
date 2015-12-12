package com.takeatrip.service;

import java.util.ArrayList;

import com.takeatrip.domain.City;

public interface CityService {
	City findById(String id);
	ArrayList<City> searchFirstLetters(String beginning);
}
