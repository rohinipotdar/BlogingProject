package com.BikkadIT.CrudOperation.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.BikkadIT.CrudOperation.Exception.ResourceNotFoundException;
import com.BikkadIT.CrudOperation.Repository.UserRepository;
import com.BikkadIT.CrudOperation.model.User;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// loading user from database by username
		User user = this.repository.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User", "email :", username));
				
		return user;
	}

}
