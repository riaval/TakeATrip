package com.takeatrip.service.implementation;

import java.util.List;

import com.takeatrip.domain.TransferType;
import com.takeatrip.repository.CityRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.takeatrip.domain.City;
import com.takeatrip.domain.Transfer;
import com.takeatrip.repository.TransferRepository;
import com.takeatrip.service.TransferService;

@Service
public class TransferServiceImpl implements TransferService{
	@Autowired
    private TransferRepository transferRepository;

	@Autowired
	private CityRepository cityRepository;
	
	@Override
	public Transfer findById(String id) {
		return transferRepository.findById(id);
	}

	@Override
	public void add(String city1Id, String city2Id, Integer price, Integer duration, TransferType type) {
		City city1 = cityRepository.findOne(city1Id);
		City city2 = cityRepository.findOne(city2Id);
		transferRepository.save(new Transfer(city1, city2, price, duration, type, null));
	}

	@Override
	public List<Transfer> findByCityPair(City city1, City city2) {
		City cityA,cityB;
		if(city1.getName().compareTo(city2.getName())!=-1) {
			cityA=city1;
			cityB=city2;
		} else {
			cityA=city2;
			cityB=city1;
		}
		ObjectId cityAId = new ObjectId(cityA.getId());
		ObjectId cityBId = new ObjectId(cityB.getId());
		return transferRepository.findByCityPair(cityAId, cityBId);
	}

	@Override
	public List<Transfer> getAllWithCity(ObjectId cityId) {
		return transferRepository.getAllWithCity(cityId);
	}
}
