package com.trailsignup.Common;

import java.time.Duration;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	private static BasePage BasePage;
    
    private static WebDriver driver;
    public final static int TIMEOUT = 10;
       
     private BasePage() {
    	 
    	System.setProperty("webdriver.gecko.driver", "/Users/mohseenshaik/Documents/project/Driver/geckodriver");
        
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        driver.manage().window().maximize();
     }      
               
    public static void openPage(String url) {
    	
    	driver.get(url);
    }
           
    public static WebDriver getDriver() {
        return driver;              
    }
       
    public static void setUpDriver() {
        if (BasePage==null) {
        	BasePage = new BasePage();
        }
    }
       
    public static void tearDown() {
        if(driver!=null) {
             driver.close();
             driver.quit();
        }   
        BasePage = null;
   } 
	
	public void load() throws Exception{
		
		System.setProperty("webdriver.gecko.driver", "/Users/mohseenshaik/Documents/project/Driver/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		
		
	}

}
