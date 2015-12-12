package com.takeatrip.controller;

import com.takeatrip.domain.City;
import com.takeatrip.service.CityService;
import dto.CityReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping(value="/suggested", method = RequestMethod.GET)
    public List<City> searchCities(@RequestParam(value = "city") String city) {
        return cityService.searchFirstLetters(city);
    }

    @RequestMapping(value="/available", method = RequestMethod.GET)
    public List<City> getAvailableCities(@RequestParam(value = "city") String city) {
        return cityService.getAvailableCities(city);
    }

    @RequestMapping(value="/available", method = RequestMethod.GET)
    public CityReport getPrice(@RequestParam(value = "cityStart") String cityStart,
                         @RequestParam(value = "cityFinish") String cityFinish) {
        return cityService.getPrices(cityStart, cityFinish);
    }

}
