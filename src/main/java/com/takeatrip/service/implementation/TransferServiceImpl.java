package com.takeatrip.service.implementation;

import java.util.List;

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
	
	@Override
	public Transfer findById(String id) {
		return transferRepository.findById(id);
	}

	@Override
	public void add(Transfer transfer) {
		transferRepository.save(transfer);
	}

	@Override
	public List<Transfer> findByCityPair(City city1, City city2) {
		City cityA,cityB;
		if(city1.getName().compareTo(city2.getName())!=-1)
		{
			cityA=city1;
			cityB=city2;
		}
		else{
			cityA=city2;
			cityB=city1;
		}
		return transferRepository.findByCityPair(cityA, cityB);
	}

	@Override
	public List<Transfer> getAllWithCity(City city) {
		return transferRepository.getAllWithCity(city);
	}
}
