package com.takeatrip.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.takeatrip.domain.City;
import com.takeatrip.repository.CityRepository;
import com.takeatrip.service.CityService;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public City findById(String id) {
        return cityRepository.findById(id);
    }

    @Override
    public List<City> searchFirstLetters(String beginning) {
        return cityRepository.searchFirstLetteres(beginning);
    }

    @Override
    public void add(City city) {
        cityRepository.save(city);
    }
}
