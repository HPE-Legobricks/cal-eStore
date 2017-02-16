package com.hpe.calEStore.dao;

public class ProfileNotSavedOrUpdatedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * @param message
	 */
	public ProfileNotSavedOrUpdatedException(String message){
		
		super(message);
	}
}
