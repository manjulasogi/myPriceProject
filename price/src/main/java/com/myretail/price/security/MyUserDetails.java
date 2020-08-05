package com.myretail.price.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.myretail.price.entities.User;

public class MyUserDetails implements UserDetails {
	
	private String userName;
	private String password;
	private int active;
	private List<GrantedAuthority> authorities;
	
	
		
	public MyUserDetails(User user) {
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.active = user.getActive();
		this.authorities = Arrays.stream(user.getRoles().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		//this.authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
				
	}
	
	public MyUserDetails() {
		
	}

	@Override
	public Collection<? extends GrantedAuthority>	getAuthorities(){
		return this.authorities;
	}
	
	@Override
	public String	getPassword() {
		return this.password;
	}
	
	@Override
	public String	getUsername() {
		return this.userName;
	}
	
	@Override
	public boolean	isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean	isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean	isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean	isEnabled() {
		return true;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
	
	
}
