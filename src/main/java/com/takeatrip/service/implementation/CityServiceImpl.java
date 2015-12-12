package com.takeatrip.service.implementation;

<<<<<<< HEAD
import java.util.ArrayList;
=======
>>>>>>> 28af60f07548aed6bb2eef047ee6f73cb94f978b
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

<<<<<<< HEAD
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
=======
	@Override
	public City findById(String id) {
		return cityRepository.findById(id);
	}

	@Override
	public List<City> searchFirstLetters(String beginning) {
		return cityRepository.searchFirstLetteres(beginning);
	}
	@Override
	public void add(City city){
		cityRepository.save(city);
	}
>>>>>>> 28af60f07548aed6bb2eef047ee6f73cb94f978b
}
