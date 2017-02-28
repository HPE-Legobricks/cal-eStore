package com.hpe.calEStore.util;

import java.security.SecureRandom;

public class RandomPasswordGeneratorUtil {


	public static String randomPassword()
	{
	    SecureRandom random = new SecureRandom();
	    char[] chars = new char[10];
	    for(int i=0;i<chars.length;i++)
	    {
	        int v = random.nextInt(10 + 26 + 26);
	        char c;
	        if (v < 10)
	        {
	            c = (char)('0' + v);
	        }
	        else if (v < 36)
	        {
	            c = (char)('a' - 10 + v);
	        }
	        else
	        {
	            c = (char)('A' - 36 + v);
	        }
	        chars[i] = c;
	    }
	    return new String(chars);
	}

}
