package com.takeatrip.controller;

import com.takeatrip.domain.City;
import com.takeatrip.domain.TransferType;
import com.takeatrip.getinfo.InfoSearcher;
import com.takeatrip.service.CityService;
import com.takeatrip.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class MainController {

    @Autowired
    CityService cityService;

    @Autowired
    TransferService transferService;

    @Autowired
    InfoSearcher infoSearcher;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printIndexPage(ModelMap model) {
        
        return "index-tiles";
    }

    @RequestMapping(value = "/addcity", method = RequestMethod.GET)
    public String printCitiesPage(ModelMap model) {
        return "city-tiles";
    }

    @RequestMapping(value = "/addtransfer", method = RequestMethod.GET)
    public String printTransferPage(ModelMap model) {
        return "transfer-tiles";
    }

    @RequestMapping(value = "/cities", method = RequestMethod.POST)
    public String addCity(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String name = request.getParameter("name");
        String country = request.getParameter("country");
        String region = request.getParameter("region");
        Integer priceLive = Integer.parseInt(request.getParameter("priceLive"));
        Integer priceFood = Integer.parseInt(request.getParameter("priceFood"));

        cityService.add(new City(name, country, region, 0, priceLive, priceFood, null));

        return "redirect:/addcity";
    }

    @RequestMapping(value = "/addtransfer", method = RequestMethod.POST)
    public String printIndexPage(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String city1 = request.getParameter("city1");
        String city2 = request.getParameter("city2");
        Integer price = Integer.parseInt(request.getParameter("price"));
        Integer duration = Integer.parseInt(request.getParameter("duration"));
        TransferType type = TransferType.valueOf(request.getParameter("type"));

        transferService.add(city1, city2, price, duration, type);

        return "redirect:/addtransfer";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() throws IOException {
        infoSearcher.updateFood(cityService.getAll());
        return "redirect:/";
    }

}
