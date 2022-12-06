package com.GomisDakarTabletApp.GomisDakarTabletApp.controller;

import java.util.Optional;
import java.util.Set;

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

import com.GomisDakarTabletApp.GomisDakarTabletApp.entity.Moto;
import com.GomisDakarTabletApp.GomisDakarTabletApp.entity.User;
import com.GomisDakarTabletApp.GomisDakarTabletApp.service.MotoService;
import com.GomisDakarTabletApp.GomisDakarTabletApp.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	MotoService motoService;
	
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	
	@GetMapping("/userForm")
	public String getUserForm(Model model) {
		model.addAttribute("userForm", new User());
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("listTab", "active");
		return "user-form/user-view-full";
	}
	
	@GetMapping("/userForm/{dni}")
	public String getUserFormFull(Model model, @PathVariable(name="dni")String dni){
		System.out.println("entra 3 " + dni);
		System.out.println("modelo dice que es " + model.toString());
		Optional<User> rellenarUser = userService.getUserByDni(dni);
		if (!rellenarUser.isEmpty()) {
			System.out.println("entra existe " + rellenarUser.toString());
			model.addAttribute("userForm", rellenarUser);
		} else {
			System.out.println("entra no existe");
			User nouUser = new User();
			nouUser.setDni(dni);
			model.addAttribute("userForm", nouUser);
		}
		System.out.println("sale");
		model.addAttribute("formTab", "active");
		model.addAttribute("full", "true");
		model.addAttribute("userList", userService.getAllUsers());
		return "user-form/user-view-full";
	}
	
	@PostMapping("/userForm")
	public String createUser(@Valid @ModelAttribute("userForm")User user, BindingResult result, ModelMap model) {
		System.out.println("entra 4 " + user.getDni());
		if(result.hasErrors()) {
			System.out.println("TIENE ERRORES VALIDACION");
			model.addAttribute("userForm", user);
			model.addAttribute("formTab", "active");
			model.addAttribute("full", "true");
			model.addAttribute("userList", userService.getAllUsers());
			return "user-form/user-view-full";
		} else {
			try {
				System.out.println("CREA USUARIO");
				userService.createUser(user);
				model.addAttribute("userForm", new User());
				model.addAttribute("listTab", "active");
				model.addAttribute("userList", userService.getAllUsers());
				return "redirect:" + user.getId() + "/userMotos";
				}
			catch (Exception e) {
				System.out.println("TIENE ERRORES GENERALES");
				model.addAttribute("FormErrorMessage",e.getMessage());
				model.addAttribute("userForm", user);
				model.addAttribute("formTab", "active");
				model.addAttribute("full", "true");
				model.addAttribute("userList", userService.getAllUsers());
				return "user-form/user-view-full";
			}
		}
	}
	
	@GetMapping("/editUser/{id}")
	public String getEditUserForm(Model model, @PathVariable(name="id")Long id) throws Exception {
		model.addAttribute("editMode", "true");
		model.addAttribute("full", "true");
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
		model.addAttribute("editMode", "true");
		model.addAttribute("full", "true");
		System.out.println("ALERTA 0.5");
		if(result.hasErrors()) {
			System.out.println("ALERTA 1");
			model.addAttribute("userForm", user);
			model.addAttribute("formTab", "active");
			model.addAttribute("userList", userService.getAllUsers());
			return "user-form/user-view-full";
		} else {
			try {
				System.out.println("ALERTA 2");
				userService.updateUser(user);
				model.addAttribute("userForm", new User());
				model.addAttribute("listTab", "active");
				model.addAttribute("full", "false");
				model.addAttribute("editMode", "true");
				model.addAttribute("userList", userService.getAllUsers());
				return "redirect:/" + user.getId() + "/userMotos";
				}
			catch (Exception e) {
				System.out.println("ALERTA 3");
				model.addAttribute("FormErrorMessage",e.getMessage());
				model.addAttribute("userForm", user);
				model.addAttribute("formTab", "active");
				model.addAttribute("userList", userService.getAllUsers());
				return "user-form/user-view-full";
			}
		}
	}
	
	@GetMapping("editUser/cancel")
	public String cancelEditUser(ModelMap model) {
		model.addAttribute("editMode", "false");
		System.out.println("pasa por el cancel s");
		return "redirect:/userForm";
	}
	
	@GetMapping("userForm/atras")
	public String atras(ModelMap model) {
		model.addAttribute("userForm", new User());
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("formTab", "active");
		return "user-form/user-view-full";
	}
	
	@GetMapping("deleteUser/{id}")
	public String deleteUser(Model model, @PathVariable(name="id")Long id) {
		System.out.println("intenta borrar");
		try {
			Set<Moto> motos = userService.getUserById(id).getMotos();
			userService.deleteUser(id);
			motoService.deleteMotos(motos);
			
		} catch (Exception e) {
			model.addAttribute("listErrorMessage", e.getMessage());
		}
		return getUserForm(model);
	}
	
	@GetMapping("{id}/userMotos")
	public String getMotoList(Model model, @PathVariable(name="id")Long id) {
		System.out.println("pasa por aqui");
		try {
			Set<Moto> motos;
			motos = userService.getUserById(id).getMotos();
			model.addAttribute("motoList", motos);
			model.addAttribute("userid", id);
			model.addAttribute("dni",userService.getUserById(id).getDni());
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("formTab", "active");
		model.addAttribute("motoVarList", "true");
		return "user-form/user-view-full";
	}
	
}
