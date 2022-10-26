package com.GomisDakarTabletApp.GomisDakarTabletApp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.GomisDakarTabletApp.GomisDakarTabletApp.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{

}
