package com.it_academy.polyclinic.service;


import com.it_academy.polyclinic.model.user_Info.User;
import com.it_academy.polyclinic.service.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class AuthProvider implements AuthenticationProvider
{
	@Autowired
	private IUserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public AuthProvider(IUserService userService) {
		this.userService = userService;
	}

	public Authentication authenticate(Authentication authentication) throws AuthenticationException
	{
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();

		User user = (User) userService.getUserByEmail(username);

		if(user != null && (user.getUsername().equals(username) || user.geteMail().equals(username)))
		{
			if(!passwordEncoder.matches(password, user.getPassword()))
			{
				throw new BadCredentialsException("Wrong password");
			}

			Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

			return new UsernamePasswordAuthenticationToken(user, password, authorities);
		}
		else
			throw new BadCredentialsException("Username not found");
	}

	public boolean supports(Class<?> arg)
	{
		return true;
	}
}
