package com.GomisDakarTabletApp.GomisDakarTabletApp.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.GomisDakarTabletApp.GomisDakarTabletApp.entity.Moto;
import com.GomisDakarTabletApp.GomisDakarTabletApp.entity.User;

public interface MotoService {
	
	public Iterable<Moto> getAllMotos();
	
	public Optional<Moto> getMotoById(Long id);

	public Moto createMoto(Moto moto) throws Exception;

	public Moto updateMoto(@Valid Moto moto);

}
