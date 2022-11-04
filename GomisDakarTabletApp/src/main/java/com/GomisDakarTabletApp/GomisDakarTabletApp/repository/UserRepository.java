package com.GomisDakarTabletApp.GomisDakarTabletApp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.GomisDakarTabletApp.GomisDakarTabletApp.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	public Optional<User> findBydni(String dni);

	public Optional<User> findById(Long id);

}
