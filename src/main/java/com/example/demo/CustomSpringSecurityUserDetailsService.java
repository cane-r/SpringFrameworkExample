package com.example.demo;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.models.User;
import com.example.demo.services.interfaces.IUserService;
 
@Service
public class CustomSpringSecurityUserDetailsService implements UserDetailsService{

	private final IUserService userService;
	private final BCryptPasswordEncoder passwordEncoder;
	
	
	public CustomSpringSecurityUserDetailsService(IUserService userService, BCryptPasswordEncoder passwordEncoder) {
		super();
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}


	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
	User user=userService.getUserByMail(mail);
	Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
	user.getRoles().forEach(
	roles ->grantedAuthorities.add(new SimpleGrantedAuthority(roles.getRoleDescription())));
	return new org.springframework.security.core.userdetails.User(user.getUserName(), passwordEncoder.encode(user.getPassword()), grantedAuthorities);
	}
	

}
