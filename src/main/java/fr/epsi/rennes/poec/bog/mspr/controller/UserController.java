package fr.epsi.rennes.poec.bog.mspr.controller;

import fr.epsi.rennes.poec.bog.mspr.domain.User;
import fr.epsi.rennes.poec.bog.mspr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/cio/add_employee")
    public void addEmployee(User user) {
        userService.addEmployee(user);
    }

    @PostMapping("/cio/add_salesman")
    public void addSalesman(User user) {
        userService.addSalesman(user);
    }
}