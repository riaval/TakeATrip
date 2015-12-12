package com.takeatrip.controller;

import com.takeatrip.domain.City;
import com.takeatrip.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MainController {

    @Autowired
    CityService cityService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printIndexPage(ModelMap model) {
        
        return "index-tiles";
    }

    @RequestMapping(value = "/cities", method = RequestMethod.POST)
    public String printIndexPage(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String name = request.getParameter("name");
        String country = request.getParameter("country");
        String region = request.getParameter("region");

        //cityService.add(new City(name, country, region));

        return "redirect:/";
    }

}
