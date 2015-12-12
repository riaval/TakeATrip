package com.takeatrip.service;

import java.util.List;

import com.takeatrip.domain.City;
import com.takeatrip.domain.Transfer;
import com.takeatrip.domain.TransferType;
import org.bson.types.ObjectId;

public interface TransferService {
	Transfer findById(String id);
	public void add(String city1, String city2, Integer price, Integer duration, TransferType type);
	public void add(Transfer transfer);
	public void add(List<Transfer> transfers);
	List<Transfer> findByCityPair(City city1, City city2);
	List<Transfer> getAllWithCity(ObjectId cityId);
}
