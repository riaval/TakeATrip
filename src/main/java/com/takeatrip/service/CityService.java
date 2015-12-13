package com.takeatrip.service;

import java.util.List;

import com.takeatrip.domain.City;
import dto.CityReport;

public interface CityService {
    City findById(String id);

    List<City> searchFirstLetters(String beginning);

    public void add(City city);
    
    public void add(List<City> cities);

    List<City> getAvailableCities(String city);

    CityReport getPrices(String cityStart, String cityFinish);

    List<City> getAll();
}
