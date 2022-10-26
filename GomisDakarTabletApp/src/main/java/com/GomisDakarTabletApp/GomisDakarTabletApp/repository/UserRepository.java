package com.GomisDakarTabletApp.GomisDakarTabletApp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.GomisDakarTabletApp.GomisDakarTabletApp.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	//public Set<User> findByDNI(String DNI);

}
