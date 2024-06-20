package com.javainuse.bootmysqlcrud.service;

import java.util.ArrayList;

import com.javainuse.bootmysqlcrud.entity.User;
import com.javainuse.bootmysqlcrud.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (StringUtils.isNotBlank(username)) {
			User u = this.userRepository.getAccountByUsername(username);
			return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getPassword(),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}