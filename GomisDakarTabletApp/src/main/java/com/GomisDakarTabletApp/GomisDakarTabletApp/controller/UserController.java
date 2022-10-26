package com.GomisDakarTabletApp.GomisDakarTabletApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.GomisDakarTabletApp.GomisDakarTabletApp.service.UserService;

@Controller
public class UserController {
	/*
	@Autowired
	UserService userService;
	
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/userForm")
	public String userForm(Model model) {
		//model.addAttribute("userList", userService.getAllUsers());
		return "user-form/user-view";
	}
	*/
}
