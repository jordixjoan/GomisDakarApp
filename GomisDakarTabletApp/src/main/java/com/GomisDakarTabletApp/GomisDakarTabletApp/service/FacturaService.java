package com.GomisDakarTabletApp.GomisDakarTabletApp.service;

import javax.validation.Valid;

import com.GomisDakarTabletApp.GomisDakarTabletApp.entity.Factura;

public interface FacturaService {
	
	public Factura createFactura(@Valid Factura factura, String password) throws Exception;

}
