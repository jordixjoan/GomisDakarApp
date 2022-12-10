package com.GomisDakarTabletApp.GomisDakarTabletApp.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GomisDakarTabletApp.GomisDakarTabletApp.entity.Factura;
import com.GomisDakarTabletApp.GomisDakarTabletApp.repository.FacturaRepository;

@Service
public class FacturaServiceImpl implements FacturaService{

	@Autowired
	FacturaRepository facturaRepository;
	
	private String passwordCorrecta  = "1964";
	
	@Override
	public Factura createFactura(@Valid Factura factura, String password) throws Exception {
		factura.setEstado("Por iniciar");
		factura.setAutorizacion(true);
		if(validarPassword(password)) {
			System.out.println("Contrasenya correcta");
			Factura nuevaFactura = facturaRepository.save(factura);
			nuevaFactura.setIdFactura(2022000 + nuevaFactura.getId().intValue());
			System.out.println("ID FACTURA: " + nuevaFactura.getIdFactura());
			return facturaRepository.save(nuevaFactura);
		}
		return null;
	}

	private boolean validarPassword(String password) throws Exception{
		System.out.println(password.equals(passwordCorrecta));
		if(password.equals(passwordCorrecta)) {
			return true;
		}
		else {
			throw new Exception("Password de Jordi incorrecta.");
		}
	}

}
