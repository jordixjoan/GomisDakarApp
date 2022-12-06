package com.GomisDakarTabletApp.GomisDakarTabletApp.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GomisDakarTabletApp.GomisDakarTabletApp.entity.Moto;
import com.GomisDakarTabletApp.GomisDakarTabletApp.entity.User;
import com.GomisDakarTabletApp.GomisDakarTabletApp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public long count() {
		return userRepository.count();
	}
	
	private boolean checkUsernameAvailable(User user) throws Exception {
		System.out.print("entra 10");
		if(userRepository.findBydni(user.getDni()).isPresent()) {
			System.out.print("entra 11");
			throw new Exception("Existe el DNI.");
		}
		return true;
	}
	
	private boolean checkPasswordValid(User user) throws Exception {
		if(user.getConfirmPassword()==null || user.getPassword().isEmpty()) {
			throw new Exception("Crea tu password y confirmala.");
		}
		
		
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			System.out.print("entra 11");
			throw new Exception("Las contraseñas no coinciden.");
		}
		return true;
	}

	@Override
	public void createUser(User user) throws Exception {
		System.out.print("entra 9");
		if(checkUsernameAvailable(user) && checkPasswordValid(user)) {
			System.out.print("entra 8");
			userRepository.save(user);
		}
	}
	
	@Override
	public User updateUser(User formUser) throws Exception {
		User toUser = getUserById(formUser.getId());
		mapUser(formUser, toUser);
		return userRepository.save(toUser);
	}
	
	protected void mapUser(User from,User to) {
		to.setDni(from.getDni());
		to.setNombre(from.getNombre());
		to.setDireccion(from.getDireccion());
		to.setCiudad(from.getCiudad());
		to.setTelefono(from.getTelefono());
		to.setCp(from.getCp());
		to.setEmail(from.getEmail());
	}
	
	@Override
	public void deleteUser(Long id) throws Exception {
		User user = getUserById(id);
		userRepository.delete(user);
	}

	
	@Override
	public Optional<User> getUserByDni(String dni){
		return userRepository.findBydni(dni);
	}

	@Override
	public User getUserById(Long id) throws Exception{
		return userRepository.findById(id).orElseThrow(() -> new Exception("El usuario no existe"));
	}

	@Override
	public void addMotoToUser(User user, Moto moto) {
		Set<Moto> motos = user.getMotos();
		motos.add(moto);
		user.setMotos(motos);
		userRepository.save(user);
	}

	@Override
	public void deleteMoto(Long userid, Moto moto) throws Exception {
		User user = getUserById(userid);
		Set<Moto> motos = user.getMotos();
		motos.remove(moto);
		user.setMotos(motos);
		userRepository.save(user);
	}
	
}
