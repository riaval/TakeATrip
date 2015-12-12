package com.takeatrip.controller;

import com.takeatrip.domain.User;
import com.takeatrip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String printIndexPage(ModelMap model) {
        User user = userService.getUserByEmail("riaval@yandex.ru");
        model.addAttribute("user", user);

        return "index-tiles";
    }

}
