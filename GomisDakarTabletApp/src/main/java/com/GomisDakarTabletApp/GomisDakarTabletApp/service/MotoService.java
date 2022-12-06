package com.GomisDakarTabletApp.GomisDakarTabletApp.service;

import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import com.GomisDakarTabletApp.GomisDakarTabletApp.entity.Moto;

public interface MotoService {
	
	public Iterable<Moto> getAllMotos();
	
	public Optional<Moto> getMotoById(Long id);

	public Moto createMoto(Moto moto) throws Exception;

	public Moto updateMoto(@Valid Moto moto);

	public void deleteMotoById(Long motoid) throws Exception;

	public void deleteMotos(Set<Moto> motos);

}
