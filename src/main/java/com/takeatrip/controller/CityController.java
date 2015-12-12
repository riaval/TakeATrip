package com.takeatrip.controller;

import com.takeatrip.domain.City;
import com.takeatrip.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping(method = RequestMethod.GET)
    public List<City> searchCities(@RequestParam(value = "city") String city) {
        return cityService.searchFirstLetters(city);
    }

}
