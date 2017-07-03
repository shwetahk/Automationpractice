package com.testApp.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class loginPage {
	WebDriver driver;
	By emailLocator = By.name("email");
	By passwordLocator = By.name("passwd");
	By submitLocator = By.id("SubmitLogin");
	
	public loginPage(WebDriver driver){
		this.driver = driver;
	}
	
	public void setEmail(String loginEmail){
		if(emailLocator!=null){
			driver.findElement(emailLocator).sendKeys(loginEmail);
		}
	
	}
	
	public void setPassword(String loginPassword){
		if(passwordLocator!=null){
			driver.findElement(passwordLocator).sendKeys(loginPassword);
		}
	
	}
	
	public void clickSignin(){
		if(submitLocator!=null){
			driver.findElement(submitLocator).click();		
		}
		
	}
	
	public void loginToMyStore(String loginEmail, String loginPassword ){
		this.setEmail(loginEmail);
		this.setPassword(loginPassword);
		this.clickSignin();
		
		
	}
	
	

}