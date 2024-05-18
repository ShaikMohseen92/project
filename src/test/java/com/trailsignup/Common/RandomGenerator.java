package com.trailsignup.Common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RandomGenerator {
	
	public static String randomEmail() {
		
		Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");  
        String dateValue = dateFormat.format(date);  
        return "random" + dateValue + "@example.com";
    }
	
	public static String randomString(int length) { 
		
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

		StringBuilder sb = new StringBuilder(length);

		for (int i = 0; i <= length; i++) {
			int index = (int)(AlphaNumericString.length()* Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}
		

}
