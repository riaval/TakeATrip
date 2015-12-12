package com.takeatrip.service.implementation;

import java.util.ArrayList;
import java.util.List;

import dto.CityReport;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.takeatrip.domain.City;
import com.takeatrip.domain.Transfer;
import com.takeatrip.domain.TransferType;
import com.takeatrip.repository.CityRepository;
import com.takeatrip.service.CityService;
import com.takeatrip.service.TransferService;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private TransferService transferService;
    
    @Override
    public City findById(String id) {
        return cityRepository.findOne(id);
    }

    @Override
    public List<City> searchFirstLetters(String beginning) {
        return cityRepository.searchFirstLetteres(beginning);
    }

    @Override
    public void add(City city) {
        cityRepository.save(city);
    }

    @Override
    public List<City> getAvailableCities(String cityId) {
    	City c=findById(cityId);
        ObjectId id = new ObjectId(cityId);
    	List<Transfer> tList=transferService.getAllWithCity(id);
    	List<City> res=new ArrayList<>();
    	for(Transfer t:tList){
    		if(t.getCityA().equals(c))
    			res.add(t.getCityB());
    		else res.add(t.getCityA());
    	}
        return res;
    }

    @Override
    public CityReport getPrices(String cityStartId, String cityFinishId) {    	
    	City cityStart=findById(cityStartId);
    	City cityFinish=findById(cityFinishId);
    	List<Transfer> tList=transferService.findByCityPair(cityStart, cityFinish);
    	
    	ArrayList<TransferType> typeList=new ArrayList<>();
    	ArrayList<Integer> priceList=new ArrayList<>();
    	for(Transfer t:tList){
    		typeList.add(t.getType());
    		priceList.add(t.getPrice());
    	}
    	CityReport cr=new CityReport(typeList,priceList,cityFinish.getPriceLive(),cityFinish.getPriceFood());
        return cr;
    }

	@Override
	public void add(List<City> cities) {
		for(City c:cities)
			add(c);
	}
}
