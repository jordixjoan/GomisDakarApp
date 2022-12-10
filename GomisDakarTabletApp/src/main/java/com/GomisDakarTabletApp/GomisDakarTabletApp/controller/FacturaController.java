package com.GomisDakarTabletApp.GomisDakarTabletApp.controller;

import java.time.LocalDate;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.GomisDakarTabletApp.GomisDakarTabletApp.entity.Factura;
import com.GomisDakarTabletApp.GomisDakarTabletApp.service.FacturaService;
import com.GomisDakarTabletApp.GomisDakarTabletApp.service.MotoService;
import com.GomisDakarTabletApp.GomisDakarTabletApp.service.UserService;

@Controller
public class FacturaController {
	
	@Autowired
	FacturaService facturaService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	MotoService motoService;

	@GetMapping("/{userid}/{motoid}/createFactura")
	public String getFacturaForm(Model model, @PathVariable(name="userid")Long userid, @PathVariable(name="motoid")Long motoid) {
		System.out.println("empieza a factura");
		LocalDate localDate = LocalDate.now();
		Factura factura = new Factura();
		factura.setFechaEntrada(localDate);
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
	public String createFactura(@Valid @ModelAttribute("factura") Factura factura,@RequestParam("password") String password, @PathVariable(name="motoid")Long motoid, final BindingResult result, final ModelMap model) {
		if(result.hasErrors()) {
			System.out.println("TIENE ERRORES VALIDACION");
			model.addAttribute("facturaForm", factura);
			model.addAttribute("formTab", "active");
			model.addAttribute("userList", userService.getAllUsers());
			return "factura-form/factura-view";
		} else {
			try {
				System.out.println("VALIDA factura " + password);
				
				Factura newFactura = facturaService.createFactura(factura,password);
				System.out.println("CREA MOTO");
				motoService.addFacturaToMoto(motoService.getMotoById(motoid).get(),newFactura);
				System.out.println(motoid + " ahora factura" + newFactura);
				model.addAttribute("userList", userService.getAllUsers());
				return "redirect:/facturaCreada";
				}
			catch (Exception e) {
				System.out.println("TIENE ERRORES GENERALES");
				model.addAttribute("FormErrorMessage",e.getMessage());
				model.addAttribute("facturaForm", factura);
				model.addAttribute("formTab", "active");
				model.addAttribute("userList", userService.getAllUsers());
				return "factura-form/factura-view";
			}
		}
	}
	
	@GetMapping("/facturaCreada")
	public String getFacturaCreada(Model model) {
		model.addAttribute("userList", userService.getAllUsers());
		
		model.addAttribute("facturaCreada", true);
		
		model.addAttribute("formTab", "active");
		return "factura-form/factura-view";
	}
	
}
