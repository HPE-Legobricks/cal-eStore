package com.hpe.calEStore.dao.impl.test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.hpe.calEStore.dao.ProfileDAO;
import com.hpe.calEStore.dao.ProfileNotSavedOrUpdatedException;
import com.hpe.calEStore.dao.entity.Address;
import com.hpe.calEStore.dao.entity.Department;
import com.hpe.calEStore.dao.entity.UserProfile;

/**
 * @author mishrani
 *
 */
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/dispatcher-servlet.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ProfileDAOImplTest {

	/**
	 * 
	 */
	@Autowired
	ProfileDAO dao;

	/**
	 * @throws ProfileNotSavedOrUpdatedException
	 */
	@Test
	@Rollback(false)
	public void testSaveOrUpdateUserProfile() {

		UserProfile up = new UserProfile();
		up.setCreatedDate(new Date());
		up.setEmailId("suresh1@s.com");
		up.setEndDate(new Date());
		up.setFirstName("d");
		up.setGender("M");
		up.setLastName("mishra");
		up.setMobileNumber("99992");
		up.setStatusInd("A");
		up.setEmailPrefInd("Y");
		up.setPassword("test123");
		up.setProfileInd("A");
		up.setSmsPrefInd("Y");

		Set<Address> sa = new HashSet<Address>();
		Address a = new Address();
		a.setAddressLine1("dwd");
		a.setAddressLine2("hrg");
		a.setCity("gegegeg");
		a.setState("OD");
		a.setIsDfltInd("Y");
		a.setZipCode("560100");
		a.setUserProfile(up);

		sa.add(a);

		Department dept = new Department();
		dept.setDepartmentId(1);
		dept.setDepartmentName("D1");

		up.setAddresses(sa);
		up.setDepartment(dept);

		try {
			dao.saveOrUpdateUserProfile(up);

		} catch (Exception e) {
			System.out.println("*****************************"
					+ e.getLocalizedMessage());
			e.printStackTrace();
		}

	}

}
