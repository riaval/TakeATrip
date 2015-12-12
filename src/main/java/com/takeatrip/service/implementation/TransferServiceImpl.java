package com.takeatrip.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
