package com.example.PrimePicks.controllers;


import com.example.PrimePicks.dto.UserModelDTO;
import com.example.PrimePicks.services.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserModelController {

    private UserModelService userModelService;


    @Autowired
    public UserModelController(UserModelService userModelService) {
        this.userModelService = userModelService;
    }


    @GetMapping("/UserModel")
    public String ListUsers(Model model) {

        List<UserModelDTO> users = userModelService.listAllUser();
        model.addAttribute("users", users);
        return "UserList";

    }


}
