package com.hpe.calEStore.service.impl;

import java.util.Collection;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hpe.calEStore.dao.LoginDAO;
import com.hpe.calEStore.dao.entity.UserProfile;

/**
 * @author mishrani
 *
 */
@Service
public class LoginServiceImpl implements UserDetailsService {

	/**
	 * 
	 */
	@Autowired
	private LoginDAO dao;

	static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	private static final String ROLE_PREFIX = "ROLE_";
	private static final Object ACTIVE_INDICATOR = "A";

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		UserProfile user = findByUsername(username);
		System.out.println(user.getUserId() + " :  " + user.getPassword());

		String encodedPassword = passwordEncoder.encode(decodeWithBase64(user
				.getPassword()));

		return new org.springframework.security.core.userdetails.User(
				user.getEmailId(), encodedPassword, user.getStatusInd().equals(ACTIVE_INDICATOR), true, true, true,
				getGrantedAuthorities(user));
	}

	/**
	 * @param username
	 * @return
	 */
	private UserProfile findByUsername(String username) {
		return dao.getUserByUsername(username);
	}

	/**
	 * @param user
	 * @return Collection
	 */
	private Collection<? extends GrantedAuthority> getGrantedAuthorities(
			UserProfile user) {

		Set<SimpleGrantedAuthority> grantedAuthorities = null;

		if (user.getProfileInd() != null) {

			grantedAuthorities = new java.util.HashSet<SimpleGrantedAuthority>();
			grantedAuthorities.add(new SimpleGrantedAuthority(String.valueOf(ROLE_PREFIX + user.getProfileInd())));
		}

		return grantedAuthorities;
	}

	/**
	 * @param password
	 * @return
	 */
	private CharSequence decodeWithBase64(String encodedPassword) {
		return new String(Base64.decodeBase64(encodedPassword.getBytes()));
	}

}