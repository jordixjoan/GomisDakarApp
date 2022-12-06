package com.GomisDakarTabletApp.GomisDakarTabletApp.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GomisDakarTabletApp.GomisDakarTabletApp.entity.Moto;
import com.GomisDakarTabletApp.GomisDakarTabletApp.repository.MotoRepository;

@Service
public class MotoServiceImpl implements MotoService{

	@Autowired
	MotoRepository motoRepository;
	
	@Override
	public Optional<Moto> getMotoById(Long id) {
		return motoRepository.findById(id);
	}

	@Override
	public Iterable<Moto> getAllMotos() {
		return motoRepository.findAll();
	}

	@Override
	public Moto createMoto(Moto moto) throws Exception {
		System.out.println("crea moto: " + moto);
		if(checkMotoNotExists(moto)) {
			System.out.println("no existe matricula: " + moto);
			return motoRepository.save(moto);
		}
		return null;
	}
	
	private boolean checkMotoNotExists(Moto moto) throws Exception {
		System.out.println(moto.getMatricula());
		if(motoRepository.findByMatricula(moto.getMatricula()).isPresent()) {
			throw new Exception("La matricula ya esta registrada.");
		}
		return true;
	}

	@Override
	public Moto updateMoto(Moto formMoto) {
		Moto toMoto = getMotoById(formMoto.getId()).get();
		mapUser(formMoto, toMoto);
		System.out.println("from " + formMoto);
		System.out.println("to " + toMoto);
		return motoRepository.save(toMoto);
	}

	private void mapUser(Moto from, Moto to) {
		to.setMotocicleta(from.getMotocicleta());
		to.setModelo(from.getModelo());
		to.setMatricula(from.getMatricula());
		to.setXasis(from.getXasis());
	}

	@Override
	public void deleteMotoById(Long id) throws Exception{
		Moto moto = getMotoById(id).get();
		motoRepository.delete(moto);
	}

	@Override
	public void deleteMotos(Set<Moto> motos) {
		motoRepository.deleteAll(motos);
	}
	
}
