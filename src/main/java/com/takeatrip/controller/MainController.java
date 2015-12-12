package com.takeatrip.controller;

import com.takeatrip.domain.City;
import com.takeatrip.domain.User;
import com.takeatrip.service.CityService;
import com.takeatrip.service.UserService;
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
    UserService userService;

    @Autowired
    CityService cityService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printIndexPage(ModelMap model) {
        User user = userService.getUserByEmail("riaval@yandex.ru");
        model.addAttribute("user", user);

        return "index-tiles";
    }

    @RequestMapping(value = "/cities", method = RequestMethod.POST)
    public String printIndexPage(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String name = request.getParameter("name");
        String country = request.getParameter("country");
        String region = request.getParameter("region");
        Integer priceLife = Integer.parseInt(request.getParameter("priceLife"));
        Integer priceFood = Integer.parseInt(request.getParameter("priceFood"));

        cityService.add(new City(name, country, region, priceLife, priceFood));

        return "redirect:/";
    }

}
