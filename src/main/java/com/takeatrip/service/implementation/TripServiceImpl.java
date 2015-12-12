package com.takeatrip.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.takeatrip.domain.Trip;
import com.takeatrip.repository.TripRepository;
import com.takeatrip.service.TripService;

@Service
public class TripServiceImpl implements TripService{
	@Autowired
	TripRepository tripRepository;
	
	@Override
	public Trip findById(String id) {
		return tripRepository.findById(id);
	}
}
