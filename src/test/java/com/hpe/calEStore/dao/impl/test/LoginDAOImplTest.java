package com.hpe.calEStore.dao.impl.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author mishrani
 *
 */
@ContextConfiguration("file:src/main/webapp/WEB-INF/dispatcher-servlet.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class LoginDAOImplTest {

	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * @throws
	 */
	@Test
	public void tesLoadByUsername() {

		System.out.println("***********" + userDetailsService);
		UserDetails user = userDetailsService.loadUserByUsername("dude@d");
	}

	/**
	 * @param user
	 * @return
	 */
	private List<GrantedAuthority> getGrantedAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		System.out.print("authorities :" + authorities);
		return authorities;
	}

}
