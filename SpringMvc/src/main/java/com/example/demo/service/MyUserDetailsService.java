package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.dao.UserRepo;
import com.example.demo.model.Users;

@Component
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String uname) throws UsernameNotFoundException {
		Users user=userRepo.findByUsername(uname);
		if(user==null)
			throw new UsernameNotFoundException("user 404");
		
		return new UserPrincipal(user);
	}

	

}
