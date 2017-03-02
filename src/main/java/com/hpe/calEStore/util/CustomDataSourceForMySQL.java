package com.hpe.calEStore.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

public class CustomDataSourceForMySQL extends BasicDataSource {

	final static Logger logger = Logger
			.getLogger(CustomDataSourceForMySQL.class);

	public CustomDataSourceForMySQL() {
		super();
	}

	public synchronized void setPassword(String encodedPassword) {
		this.password = decode(encodedPassword);
	}

	private String decode(String password) {
		String decodedPassword = null;
		try {
			decodedPassword = new String(Base64.decodeBase64(password
					.getBytes()));

		} catch (Exception e) {
			logger.info("Exception occurred: Not able to decode the encrypted DB password.");
			throw e;
		}
		return decodedPassword;
	}
}