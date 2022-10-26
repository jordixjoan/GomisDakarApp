package com.GomisDakarTabletApp.GomisDakarTabletApp.service;

import com.GomisDakarTabletApp.GomisDakarTabletApp.entity.User;

public interface UserService {
	
	public Iterable<User> getAllUsers();
	
	public long count();

}
