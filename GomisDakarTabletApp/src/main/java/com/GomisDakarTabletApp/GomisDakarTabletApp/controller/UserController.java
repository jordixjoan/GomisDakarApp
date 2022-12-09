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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		model.addAttribute("formTab", "active");
		model.addAttribute("start", true);
		return "user-form/user-view";
	}
	
	@PostMapping("/userForm")
	public String postUserForm(@Valid @ModelAttribute("userForm")User user, BindingResult result, Model model) {
		if(result.getErrorCount()>5) {
			System.out.println("errores " + result);
			model.addAttribute("userForm", user);
			model.addAttribute("userList", userService.getAllUsers());
			model.addAttribute("formTab", "active");
			model.addAttribute("start", true);
			return "user-form/user-view";
		}
		else {
			System.out.println("entra a postuserform para ahcer cosas");
			String formDni = user.getDni();
			Optional<User> userForm = userService.getUserByDni(formDni);
			if(userForm.isPresent()) {
				return "redirect:/user/"+userForm.get().getId();
			}
			else{
				model.addAttribute("exists", false);
				return getUserFormFull(model,formDni);
			}
		}
	}
	
	@GetMapping("/user/{id}")
	public String getUser(Model model, @PathVariable(name="id")Long id) throws Exception {
		User user = userService.getUserById(id);
		if(!user.getRegistrado()) {
			System.out.println("no registrado");
			model.addAttribute("noRegistrado",true);
		}
		System.out.println("peque;os exitos");
		model.addAttribute("userForm",user);
		model.addAttribute("formTab", "active");
		model.addAttribute("userList", userService.getAllUsers());
		return "user-form/user-view";
	}
	
	@GetMapping("/userFormFull")
	public String getUserFormFull(Model model, String dni){
		System.out.println("no existe " + dni);
		User nouUser = new User();
		nouUser.setDni(dni);
		model.addAttribute("userForm", nouUser);
		model.addAttribute("formTab", "active");
		model.addAttribute("edit",true);
		model.addAttribute("userList", userService.getAllUsers());
		return "user-form/user-view";
	}
	
	@PostMapping("/userRegistrar/{id}")
	public String postUserRegistrar(@Valid @ModelAttribute("userForm")User user, BindingResult result, Model model) {
		System.out.println(result);
		if(result.hasErrors()) {
			System.out.println("ALERTA 1");
			model.addAttribute("noRegistrado",true);
			model.addAttribute("userForm", user);
			model.addAttribute("formTab", "active");
			model.addAttribute("userList", userService.getAllUsers());
			return "user-form/user-view";
		} else {
			try {
				System.out.println("ALERTA 2");
				userService.registrarUser(user);
				model.addAttribute("userForm",user);
				model.addAttribute("formTab", "active");
				model.addAttribute("userList", userService.getAllUsers());
				return "redirect:/user/" + user.getId();
				}
			catch (Exception e) {
				System.out.println("ALERTA 3");
				model.addAttribute("noRegistrado",true);
				model.addAttribute("FormErrorMessage",e.getMessage());
				model.addAttribute("userForm", user);
				model.addAttribute("formTab", "active");
				model.addAttribute("userList", userService.getAllUsers());
				return "user-form/user-view";
			}
		}
	}
	
	@PostMapping("/userFormFull")
	public String createUser(@Valid @ModelAttribute("userForm")User user, BindingResult result, ModelMap model) {
		System.out.println(result);
		System.out.println("entra 4 " + user.getDni());
		if(result.hasErrors()) {
			System.out.println("TIENE ERRORES VALIDACION");
			model.addAttribute("userForm", user);
			model.addAttribute("edit",true);
			model.addAttribute("formTab", "active");
			model.addAttribute("userList", userService.getAllUsers());
			return "user-form/user-view";
		} else {
			try {
				user.setRegistrado(true);
				user.setRole("client");
				userService.createUser(user);
				System.out.println("CREA USUARIO");
				model.addAttribute("userForm", new User());
				model.addAttribute("listTab", "active");
				model.addAttribute("userList", userService.getAllUsers());
				return "redirect:/userMotos/" + user.getId();
				}
			catch (Exception e) {
				System.out.println("TIENE ERRORES GENERALES");
				model.addAttribute("FormErrorMessage",e.getMessage());
				model.addAttribute("userForm", user);
				model.addAttribute("formTab", "active");
				model.addAttribute("edit",true);
				model.addAttribute("userList", userService.getAllUsers());
				return "user-form/user-view";
			}
		}
	}
	
	@GetMapping("/editUser/{id}")
	public String getEditUserForm(Model model, @PathVariable(name="id")Long id) throws Exception {
		model.addAttribute("edit",true);
		model.addAttribute("editMode",true);
		User userToEdit = userService.getUserById(id);
		System.out.print(userToEdit.toString());
		
		model.addAttribute("userForm", userToEdit);
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("formTab", "active");
		return "user-form/user-view";
	}
	
	@PostMapping("/editUser")
	public String postEditUserForm(@Valid @ModelAttribute("userForm")User user, BindingResult result, ModelMap model) throws Exception {
		System.out.println("ALERTA 0.5");
		if(result.hasErrors()) {
			System.out.println("ALERTA 1");
			model.addAttribute("edit",true);
			model.addAttribute("editMode", "true");
			model.addAttribute("userForm", user);
			model.addAttribute("formTab", "active");
			model.addAttribute("userList", userService.getAllUsers());
			return "user-form/user-view";
		} else {
			try {
				System.out.println("ALERTA 2");
				userService.updateUser(user);
				model.addAttribute("userForm", new User());
				model.addAttribute("listTab", "active");
				model.addAttribute("userList", userService.getAllUsers());
				return "redirect:/user/"+user.getId();
				}
			catch (Exception e) {
				System.out.println("ALERTA 3");
				model.addAttribute("FormErrorMessage",e.getMessage());
				model.addAttribute("edit",true);
				model.addAttribute("editMode", "true");
				model.addAttribute("userForm", user);
				model.addAttribute("formTab", "active");
				model.addAttribute("userList", userService.getAllUsers());
				return "user-form/user-view";
			}
		}
	}
	
	@RequestMapping(value = "/motoOptions", method = RequestMethod.POST, params = "cancel")
	public String cancel(@Valid @ModelAttribute("id")Long id, BindingResult result, ModelMap model) {
		System.out.println(id);
		System.out.println("pasa por el cancel");
		return "redirect:/user/" + id;
	}
	
	@GetMapping("userForm/atras")
	public String atras(ModelMap model) {
		model.addAttribute("userForm", new User());
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("formTab", "active");
		return "redirect:/userForm";
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
	
	@GetMapping("/userMotos/{id}")
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
		model.addAttribute("id", id);
		model.addAttribute("start", true);
		model.addAttribute("formTab", "active");
		model.addAttribute("motoVarList", "true");
		return "moto-form/moto-view";
	}
	
}
