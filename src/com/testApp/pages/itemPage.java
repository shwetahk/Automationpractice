package com.testApp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;


public class itemPage {
	WebDriver driver;
	
	By itemQuickviewLocator = By.className("icon-eye-open");
	By itemPriceLocator = By.id("our_price_display");
	By itemSizeLocator = By.xpath("//*[contains(@class,'form-control attribute_select no-print')]");
	By addToCartLocator = By.xpath("//*[@id='add_to_cart']/button/span");
	By continueLocator = By.xpath("//span[contains(@title,'Continue shopping')]");
	By categoryBlouseLocator = By.linkText("http://automationpractice.com/index.php?id_category=7&controller=category");
	
	public itemPage(WebDriver driver){
		this.driver = driver;
	}
	
	public void quickViewItem() throws InterruptedException{
			driver.findElement(By.className("icon-eye-open")).click();
			Thread.sleep(1000);
			
	}
	
	public String getItemPrice(){
		String itemPrice = null;
		itemPrice = driver.findElement(itemPriceLocator).getAttribute("innerHTML");
		return itemPrice;	
	}

	public void changeItemSize(String sizeToSelect){
		
			Select size = new Select(driver.findElement(itemSizeLocator));
			size.selectByVisibleText(sizeToSelect);;	
		
	}

	public void addToCart() throws InterruptedException{
		
			driver.findElement(addToCartLocator).click();
			Thread.sleep(1000);
		
	}
	
	public void continueShopping() throws InterruptedException{		
		driver.findElement(continueLocator).click();
		Thread.sleep(1000);
	}
	
	public void selectBlouse() throws InterruptedException {
		driver.get("http://automationpractice.com/index.php?id_category=7&controller=category");
		quickViewItem();	
	}

	
	
	
	

}
