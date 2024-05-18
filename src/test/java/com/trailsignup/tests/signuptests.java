package com.trailsignup.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.trailsignup.Common.BasePage;
import com.trailsignup.Common.RandomGenerator;
import com.trailsignup.signup.Signup;


public class signuptests {
	
	Signup objsignup = new Signup();
	
	@BeforeClass
    public static void setUp() {
 
    	BasePage.setUpDriver();
    }
	
	@Test
	public void signUpTest() {
		InputStream input;
		Properties prop = null;
		try {
			input = new FileInputStream("/Users/mohseenshaik/Documents/project/Utilities/config.properties");
			prop = new Properties();
			prop.load(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        BasePage.openPage(prop.getProperty("url"));
        
        objsignup.setFirstName("test");
        objsignup.setLastName("5");
        objsignup.setEmail(RandomGenerator.randomEmail());
        objsignup.setPassword("Pass@123");
        objsignup.clickTerms();
        objsignup.clickRecaptcha();
        objsignup.clickTrialSignUp();
	}


}
