package com.GomisDakarTabletApp.GomisDakarTabletApp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.GomisDakarTabletApp.GomisDakarTabletApp.entity.Factura;
import com.GomisDakarTabletApp.GomisDakarTabletApp.service.UserService;

public class FacturaController {
	
	@Autowired
	UserService facturaService;
	
	@Autowired
	UserService userService;

	@GetMapping("/{userid}/{motoid}/createFactura")
	public String getMotoForm(Model model, @PathVariable(name="userid")Long userid, @PathVariable(name="motoid")Long motoid) {
		System.out.println("empieza a factura");
		Factura factura = new Factura();
		model.addAttribute("facturaForm", factura);
		System.out.println(factura);
		
		model.addAttribute("facturaVarForm", "true");
		model.addAttribute("userList", userService.getAllUsers());
		
		model.addAttribute("userid", userid);
		model.addAttribute("motoid", motoid);
		
		model.addAttribute("formTab", "active");
		return "factura-form/factura-view";
	}
	
	@PostMapping("/{userid}/{motoid}/createFactura")
	public void createFactura(@Valid @ModelAttribute("factura") Factura factura, final BindingResult result, final ModelMap model) {
	      //algo  
	}
	
}
