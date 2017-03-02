package com.hpe.calEStore.util;

import org.apache.commons.codec.binary.Base64;

public class DecodeUtil {

	/**
	 * @param password
	 * @return
	 */
	public static CharSequence decodeWithBase64(String encodedPassword) {
		return new String(Base64.decodeBase64(encodedPassword.getBytes()));
	}
	
	
	/**
	 * @param plainpassword
	 * @return
	 */
	public static String encodedeWithBase64(String plainpassword){
		
		Base64 base64 = new Base64();
		return  new String(base64.encode(plainpassword.getBytes()));
		
	}
	
}
