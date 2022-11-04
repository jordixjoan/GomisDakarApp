package com.GomisDakarTabletApp.GomisDakarTabletApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.GomisDakarTabletApp.GomisDakarTabletApp.entity.Moto;

@Repository
public interface MotoRepository extends CrudRepository<Moto, Long>{

	public Optional<Moto> findById(Long id);
	
	public Iterable<Moto> findAll();

	public Optional<Moto> findByMatricula(String matricula);
}
