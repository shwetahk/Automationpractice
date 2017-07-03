package com.testApp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class orderSummary {
	WebDriver driver;
	
	public orderSummary(WebDriver driver){
		this.driver = driver;
	}
	
	By previousOrderLocator = By.xpath("//*[@id='center_column']/div/div[1]/ul/li[1]/a/span");
	By itemFromOrderLocator = By.xpath("//*[@id='order-list']/tbody/tr[1]/td[1]/a");
	By msgBoxLocator = By.name("msgText");
	By submitMessageLocator = By.xpath("//*[@id='sendOrderMessage']/div/button");
	By savedMessageLocator = By.xpath("//*[@id='block-order-detail']/div[5]");
	By item1DetailsLocator = By.xpath("//*[@id='order-detail-content']/table/tbody/tr[1]/td[2]");

	public void viewPreviousOrders() throws InterruptedException {
		driver.findElement(previousOrderLocator).click();
		Thread.sleep(2000);
		
	}

	public void selectItemFromOrder() throws InterruptedException {
		driver.findElement(itemFromOrderLocator).click();
		Thread.sleep(2000);
		
	}

	public void addComment(String msgText) throws InterruptedException {
		driver.findElement(msgBoxLocator).sendKeys(msgText);
		driver.findElement(submitMessageLocator).submit();
		Thread.sleep(2000);
		
	}

	public void confirmCommentAdded(String msgText) {
		String orderDetailHTML = driver.findElement(savedMessageLocator).getAttribute("innerHTML"); 
		Assert.assertTrue(orderDetailHTML.contains(msgText));
		
	}


	public void confirmOrderColour(String orderColour) throws InterruptedException {
		String orderDetailHTML = driver.findElement(item1DetailsLocator).getAttribute("innerHTML");
		Assert.assertTrue(orderDetailHTML.contains(orderColour));
		
	}
	
	
	

}
