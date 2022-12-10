package com.GomisDakarTabletApp.GomisDakarTabletApp.service;

import java.util.Optional;

import javax.validation.Valid;

import com.GomisDakarTabletApp.GomisDakarTabletApp.entity.Factura;
import com.GomisDakarTabletApp.GomisDakarTabletApp.entity.Moto;
import com.GomisDakarTabletApp.GomisDakarTabletApp.entity.User;

public interface UserService {
	
	public Iterable<User> getAllUsers();
	
	public long count();

	public void createUser(User user) throws Exception;
	
	public User updateUser(User formUser) throws Exception;
	
	public void deleteUser(Long id) throws Exception;

	public Optional<User> getUserByDni(String dni);

	public User getUserById(Long id) throws Exception;

	public void addMotoToUser(User user,Moto moto);

	public void deleteMoto(Long userid, Moto moto) throws Exception;

	public void registrarUser(@Valid User user) throws Exception;
}
