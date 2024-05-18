package com.trailsignup.signup;

import org.openqa.selenium.support.PageFactory;

import com.trailsignup.Common.BasePage;
import com.trailsignup.Locators.TrialSignUpLocators;


public class Signup {

	TrialSignUpLocators trailSignUpLocators;
	    

		public Signup() {
			this.trailSignUpLocators = new TrialSignUpLocators(BasePage.getDriver());
	        PageFactory.initElements(BasePage.getDriver(),trailSignUpLocators);
	    }

		public void setFirstName(String firstname) {
			trailSignUpLocators.firstName.click();
			trailSignUpLocators.firstName.sendKeys(firstname);
	    }
	  
		public void setLastName(String lastname) {
			trailSignUpLocators.lastName.click();
			trailSignUpLocators.lastName.sendKeys(lastname);
	    }

		public void setEmail(String email) {
			trailSignUpLocators.email.click();
			trailSignUpLocators.email.sendKeys(email);
	    }
	    
		public void setPassword(String loginPassword) {
			trailSignUpLocators.password.click();
			trailSignUpLocators.password.sendKeys(loginPassword);
	    }
	    
		public void clickTerms() {
			trailSignUpLocators.termsCheckbox.click();
	    }
	    
		public void clickRecaptcha() {
			trailSignUpLocators.recaptchaCheckbox.click();
	    }
		
		public void clickTrialSignUp() {
			trailSignUpLocators.trialSignUp.click();
		}

}
