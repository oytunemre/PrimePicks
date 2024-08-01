package com.example.PrimePicks.controllers;

import com.example.PrimePicks.models.UserModel;
import com.example.PrimePicks.repository.UserModelRepository;
import com.example.PrimePicks.services.UserModelService;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class RegisterController {

    private UserModelService userModelService;

    public RegisterController(UserModelService userModelService) {
        this.userModelService = userModelService;
    }


    @ModelAttribute("UserModel")
    public UserModel userModel() {
        return new UserModel();
    }


    @GetMapping("/RegisterPage")
    public String showRegisterPage(Model model) {
        return "RegisterPage";
    }


    @PostMapping("/createUser")
    public String saveUser(@Validated @ModelAttribute("UserModel") UserModel userModel, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "RegisterPage";
        }
        if (userModelService.findByUsername(userModel.getUsername()) != null) {
            model.addAttribute("error", "Username already exists.");
            return "RegisterPage";
        }
        userModelService.saveUser(userModel);
        return "redirect:/LoginPage";
    }


}
