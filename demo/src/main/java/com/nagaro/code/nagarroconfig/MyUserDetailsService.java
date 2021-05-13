package com.nagaro.code.nagarroconfig;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		if ("admin".equals(username)) {
			return new User("admin", "admin", getAuthorities("ADMIN"));
		}

		if ("user".equals(username)) {
			return new User("user", "user", getAuthorities("USER"));
		}

		return null;
	}


	private Collection<? extends GrantedAuthority> getAuthorities(String role) {
		String ROLE_PREFIX = "ROLE_";
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

		list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role));

		return list;
	}
}
