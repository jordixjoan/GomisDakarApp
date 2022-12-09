package com.GomisDakarTabletApp.GomisDakarTabletApp.controller;

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

import com.GomisDakarTabletApp.GomisDakarTabletApp.entity.Factura;
import com.GomisDakarTabletApp.GomisDakarTabletApp.entity.Moto;
import com.GomisDakarTabletApp.GomisDakarTabletApp.service.MotoService;
import com.GomisDakarTabletApp.GomisDakarTabletApp.service.UserService;

@Controller
public class MotoController {
	@Autowired
	MotoService motoService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("{userid}/createMoto")
	public String getMotoForm(Model model, @PathVariable(name="userid")Long userid) {
		Moto moto = new Moto();
		model.addAttribute("motoForm", moto);
		System.out.println(moto);
		
		model.addAttribute("motoVarForm", "true");
		Iterable<Moto> motoHistorial= motoService.getAllMotos();
		model.addAttribute("userList", userService.getAllUsers());
		System.out.println(motoHistorial);
		
		model.addAttribute("userid", userid);
		model.addAttribute("motoHistorial", motoHistorial);
		
		model.addAttribute("formTab", "active");
		return "moto-form/moto-view";
	}
	
	@PostMapping("{userid}/createMoto")
	public String postMotoForm(@Valid @ModelAttribute("motoForm")Moto moto, BindingResult result, ModelMap model, @PathVariable(name="userid")Long userid) {
		System.out.println("entra 4 " + userid);
		if(result.hasErrors()) {
			System.out.println("TIENE ERRORES VALIDACION");
			model.addAttribute("motoForm", moto);
			model.addAttribute("formTab", "active");
			model.addAttribute("userList", userService.getAllUsers());
			return "moto-form/moto-view";
		} else {
			try {
				Moto newMoto = motoService.createMoto(moto);
				System.out.println("CREA MOTO");
				userService.addMotoToUser(userService.getUserById(userid),newMoto);
				System.out.println(userid + " ahora moto" + newMoto);
				model.addAttribute("userList", userService.getAllUsers());
				return "redirect:/userMotos/"+ userid;
				}
			catch (Exception e) {
				System.out.println("TIENE ERRORES GENERALES");
				model.addAttribute("FormErrorMessage",e.getMessage());
				model.addAttribute("motoForm", moto);
				model.addAttribute("formTab", "active");
				model.addAttribute("userList", userService.getAllUsers());
				return "moto-form/moto-view";
			}
		}
	}
	
	@GetMapping("{userid}/{motoid}/editMoto")
	public String getEditMotoForm(Model model, @PathVariable(name="userid")Long userid, @PathVariable(name="motoid")Long motoid) throws Exception {
		Moto motoToEdit = motoService.getMotoById(motoid).get();
		System.out.print(motoToEdit.toString());
		
		model.addAttribute("motoForm", motoToEdit);
		model.addAttribute("editModeMoto", "true");
		model.addAttribute("userid", userid);
		model.addAttribute("motoid", motoid);
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("formTab", "active");
		return "moto-form/moto-view";
	}
	
	@PostMapping("{userid}/{motoid}/editMoto")
	public String postEditMotoForm(@Valid @ModelAttribute("motoForm")Moto moto, BindingResult result, ModelMap model, @PathVariable(name="userid")Long userid, @PathVariable(name="motoid")Long motoid) throws Exception {
		System.out.println(result);
		moto.setId(motoid);
		if(result.hasErrors()) {
			System.out.println("TIENE ERRORES VALIDACION");
			model.addAttribute("motoVarForm", "true");
			model.addAttribute("motoForm", moto);
			model.addAttribute("formTab", "active");
			model.addAttribute("userList", userService.getAllUsers());
			return "moto-form/moto-view";
		} else {
			try {
				System.out.println("quiere  hacer update");
				Moto newMoto = motoService.updateMoto(moto);
				System.out.println("update MOTO");
				System.out.println(userid + " ahora moto" + newMoto);
				model.addAttribute("userList", userService.getAllUsers());
				return "redirect:/userMotos/" + userid;
				}
			catch (Exception e) {
				System.out.println("TIENE ERRORES GENERALES");
				model.addAttribute("motoVarForm", "true");
				model.addAttribute("FormErrorMessage",e.getMessage());
				model.addAttribute("motoForm", moto);
				model.addAttribute("formTab", "active");
				model.addAttribute("userList", userService.getAllUsers());
				return "moto-form/moto-view";
			}
		}
	}
	
	@GetMapping("{userid}/{motoid}/deleteMoto")
	public String deleteUser(Model model, @PathVariable(name="userid")Long userid, @PathVariable(name="motoid")Long motoid) {
		System.out.println("intenta borrar" + userid + " user y " + motoid + " moto.");
		try {
			Moto moto = motoService.getMotoById(motoid).get();
			userService.deleteMoto(userid,moto);
			motoService.deleteMotoById(motoid);
		} catch (Exception e) {
			model.addAttribute("listErrorMessage", e.getMessage());
		}
		return "redirect:/userMotos/"+userid;
	}
}
