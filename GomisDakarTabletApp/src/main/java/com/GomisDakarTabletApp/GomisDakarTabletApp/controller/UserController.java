package com.GomisDakarTabletApp.GomisDakarTabletApp.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.GomisDakarTabletApp.GomisDakarTabletApp.entity.User;
import com.GomisDakarTabletApp.GomisDakarTabletApp.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	
	@GetMapping("/userForm")
	public String getUserForm(Model model) {
		System.out.print("entra 1");
		model.addAttribute("userForm", new User());
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("listTab", "active");
		model.addAttribute("formFull", "false");
		return "user-form/user-view-full";
	}
	
	@GetMapping("/userForm/{dni}")
	public String getUserFormFull(@Valid @ModelAttribute("userForm")User user, BindingResult result, ModelMap model){
		System.out.print("entra 2 " + user.getDni());
		Optional<User> rellenarUser = userService.getUserByDni(user.getDni());
		if (!rellenarUser.isEmpty()) {
			model.addAttribute("userForm", rellenarUser.get());
			model.addAttribute("formTab", "active");
			model.addAttribute("userList", userService.getAllUsers());
		} else {
			User nouUser = new User();
			nouUser.setDni(user.getDni());
			model.addAttribute("userForm", nouUser);
		}
		model.addAttribute("formTab", "active");
		model.addAttribute("userList", userService.getAllUsers());
		return "user-form/user-view-full";
	}
	
	@PostMapping("/userForm")
	public String createUser(@Valid @ModelAttribute("userForm")User user, BindingResult result, ModelMap model) {
		
		System.out.print("entra 4");
		if(result.hasErrors()) {
			System.out.print("entra 5");
			System.out.print("Tiene errores de validacion.");
			model.addAttribute("userForm", user);
			model.addAttribute("formTab", "active");
			model.addAttribute("userList", userService.getAllUsers());
			return "user-form/user-view-full";
		} else {
			try {
				System.out.print("entra 6");
				userService.createUser(user);
				model.addAttribute("userForm", new User());
				model.addAttribute("listTab", "active");
				model.addAttribute("userList", userService.getAllUsers());
				return "user-form/user-view";
				}
			catch (Exception e) {
				System.out.print("entra 7");
				model.addAttribute("FormErrorMessage",e.getMessage());
				model.addAttribute("userForm", user);
				model.addAttribute("formTab", "active");
				model.addAttribute("userList", userService.getAllUsers());
				return "user-form/user-view-full";
			}
		}
	}
	
	@GetMapping("/editUser/{id}")
	public String getEditUserForm(Model model, @PathVariable(name="id")Long id) throws Exception {
		model.addAttribute("editMode", "true");
		User userToEdit = userService.getUserById(id);
		System.out.print(userToEdit.toString());
		
		model.addAttribute("userForm", userToEdit);
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("formTab", "active");
		System.out.println("ALERTA 0");
		return "user-form/user-view-full";
	}
	
	@PostMapping("/editUser")
	public String postEditUserForm(@Valid @ModelAttribute("userForm")User user, BindingResult result, ModelMap model) throws Exception {
		System.out.println("ALERTA 0.5");
		if(result.hasErrors()) {
			System.out.println("ALERTA 1");
			model.addAttribute("userForm", user);
			model.addAttribute("formTab", "active");
			model.addAttribute("userList", userService.getAllUsers());
			model.addAttribute("editMode", "true");
			return "user-form/user-view-full";
		} else {
			try {
				System.out.println("ALERTA 2");
				userService.updateUser(user);
				model.addAttribute("userForm", new User());
				model.addAttribute("listTab", "active");
				model.addAttribute("editMode", "false");
				model.addAttribute("userList", userService.getAllUsers());
				return "user-form/user-view";
				}
			catch (Exception e) {
				System.out.println("ALERTA 3");
				model.addAttribute("FormErrorMessage",e.getMessage());
				model.addAttribute("userForm", user);
				model.addAttribute("formTab", "active");
				model.addAttribute("userList", userService.getAllUsers());
				model.addAttribute("editMode", "true");
				return "user-form/user-view-full";
			}
		}
	}
	
	@GetMapping("editUser/cancel")
	public String cancelEditUser(ModelMap model) {
		return "redirect:/userForm";
	}
	
	@GetMapping("deleteUser/{id}")
	public String deleteUser(Model model, @PathVariable(name="id")Long id) {
		System.out.println("intenta borrar");
		try {
			userService.deleteUser(id);
		} catch (Exception e) {
			model.addAttribute("listErrorMessage", e.getMessage());
		}
		return getUserForm(model);
	}
	
}
