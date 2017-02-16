package com.hpe.calEStore.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.hpe.calEStore.dao.AbstractDAO;
import com.hpe.calEStore.dao.LoginDAO;
import com.hpe.calEStore.dao.entity.UserProfile;

/**
 * @author mishrani
 *
 */
@Repository
public class LoginDAOImpl extends AbstractDAO<Serializable, UserProfile> implements LoginDAO, UserDetailsService {


	
	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserProfile user = findByUsername(username);
		
		System.out.println(user.getUserId()+" :  "+user.getPassword());
		
		
		
		/*move this to the service layer*/
		/*new org.springframework.security.core.userdetails.User(user.getUserId(), user.getPassword(), 
				 user.getStatusInd().equals("Active"), true, true, true, getGrantedAuthorities(user));*/
		
		return null;
	}
	
	
	/**
	 * @param key
	 * @return
	 */
	private UserProfile findByUsername(Serializable key) {
			return getByKey(key);
	}
	
	
	/**
	 * @param user
	 * @return Collection
	 */
	private Collection<? extends GrantedAuthority> getGrantedAuthorities(UserProfile user) {
		
		Set<SimpleGrantedAuthority> grantedAuthorities = new java.util.HashSet<SimpleGrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority(String.valueOf(user.getProfileileInd())));
		
		return grantedAuthorities;
	}
	
	
	
}