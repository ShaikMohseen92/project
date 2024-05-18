package com.trailsignup.Locators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.trailsignup.Common.BasePage;

public class TrialSignUpLocators {
	
	WebDriver driver;
	
	public TrialSignUpLocators(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id="first_name")
    public WebElement firstName;
  
    @FindBy(id = "last_name")
    public WebElement lastName;
    
    @FindBy(id = "user_email")
    public WebElement email;
     
    @FindBy(id = "user_password")
    public WebElement password;
    
    @FindBy(id = "terms")
    public WebElement termsCheckbox;
    
    @FindBy(id = "recaptcha-anchor")
    public WebElement recaptchaCheckbox;
    
    @FindBy(id = "send")
    public WebElement trialSignUp;
    
}
