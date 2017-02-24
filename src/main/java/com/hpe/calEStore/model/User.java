package com.hpe.calEStore.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Constraint;
import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.hpe.calEStore.dao.entity.Department;

/**
 * @author mishrani
 *
 */

public class User {
	
	private Integer userId;
	
	private Department department;
	
	private String firstName;
	
	private String lastName;
	
	private String emailId;
	
	private String password;
	
	private String confirmPassword;
	
	private String mobileNumber;
	
	private String gender;
	private String profileileInd;
	private Date createdDate;
	private String statusInd;
	private Date endDate;
	private boolean emailPref;
	private boolean smsPref;
	
	User[]  selectedUserBox;
	
	
	/**
	 * @return
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	/**
	 * 
	 */
	List<User> userCheckBoxList = new ArrayList<User>();
	
	

	public User[] getSelectedUserBox() {
		return selectedUserBox;
	}

	public void setSelectedUserBox(User[] selectedUserBox) {
		this.selectedUserBox = selectedUserBox;
	}

	/**
	 * @return
	 */
	public List<User> getUserCheckBoxList() {
		return userCheckBoxList;
	}

	/**
	 * @param userCheckBoxList
	 */
	public void setUserCheckBoxList(List<User> userCheckBoxList) {
		this.userCheckBoxList = userCheckBoxList;
	}

	private UserAddress address;
	
	
	/**
	 * @return
	 */
	public UserAddress getAddress() {
		return address;
	}

	/**
	 * @param address
	 */
	public void setAddress(UserAddress address) {
		this.address = address;
	}

	/**
	 * @return
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}
		
	/**
	 * @param confirmPassword
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	/**
	 * @return
	 */
	public Department getDepartment() {
		return department;
	}
	/**
	 * @param department
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}
	/**
	 * @return
	 */
	/**
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return
	 */
	public String getEmailId() {
		return emailId;
	}
	/**
	 * @param emailId
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	/**
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	/**
	 * @return
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}
	/**
	 * @param mobileNumber
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	/**
	 * @return
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return
	 */
	public String getProfileileInd() {
		return profileileInd;
	}
	/**
	 * @param profileileInd
	 */
	public void setProfileileInd(String profileileInd) {
		this.profileileInd = profileileInd;
	}
	/**
	 * @return
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return
	 */
	public String getStatusInd() {
		return statusInd;
	}
	/**
	 * @param statusInd
	 */
	public void setStatusInd(String statusInd) {
		this.statusInd = statusInd;
	}
	/**
	 * @return
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate
	 */
	/**
	 * @param endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isEmailPref() {
		return emailPref;
	}

	public void setEmailPref(boolean emailPref) {
		this.emailPref = emailPref;
	}

	public boolean isSmsPref() {
		return smsPref;
	}

	public void setSmsPref(boolean smsPref) {
		this.smsPref = smsPref;
	}

	
	
}
