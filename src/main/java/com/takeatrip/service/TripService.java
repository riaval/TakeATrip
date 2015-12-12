package com.takeatrip.service;

import com.takeatrip.domain.Trip;

public interface TripService {
	Trip findById(String id);
	public void add(Trip trip);
}
