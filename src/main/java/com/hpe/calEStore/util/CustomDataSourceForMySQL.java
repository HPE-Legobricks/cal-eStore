package com.hpe.calEStore.util;

import java.io.IOException;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;

public class CustomDataSourceForMySQL extends BasicDataSource {


	final static Logger logger = Logger.getLogger(CustomDataSourceForMySQL.class);
	
	public CustomDataSourceForMySQL() {
		super();
	}

	public synchronized void setPassword(String encodedPassword) {
		this.password = decode(encodedPassword);
	}

	private String decode(String password) {
		BASE64Decoder decoder = new BASE64Decoder();
		String decodedPassword = null;
		try {
			decodedPassword = new String(decoder.decodeBuffer(password));
			
		} catch (IOException e) {
			
			logger.info("Exception occurred: Not able to decode the encrypted DB password.");
		}
		return decodedPassword;
	}
}