package com.takeatrip.service;

import java.util.List;

import com.takeatrip.domain.City;
import com.takeatrip.domain.Transfer;

public interface TransferService {
	Transfer findById(String id);
	public void add(Transfer transfer);
	public void add(List<Transfer> transfers);
	List<Transfer> findByCityPair(City city1, City city2);
	List<Transfer> getAllWithCity(City city);
}
