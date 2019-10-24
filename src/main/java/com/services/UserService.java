package com.services;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.models.Role;
import com.models.User;
import com.repositories.RoleRepository;
import com.repositories.UserRepository;

@Service("userService")
public class UserService  {
	private UserRepository userRepo;
	private RoleRepository roleRepo;
	private BCryptPasswordEncoder bCryptPassEncode;
	
	@Autowired
	public UserService(UserRepository userRepo, RoleRepository roleRepo, BCryptPasswordEncoder bCryptPassEncode) {
		
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.bCryptPassEncode = bCryptPassEncode;
	}
	
	public User findUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	public void saveUser(User user) {
		user.setPassword(bCryptPassEncode.encode(user.getPassword()));
		user.setActive(1);
		Role userRole = roleRepo.findByRole("ADMIN");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepo.save(user);
	}
}
