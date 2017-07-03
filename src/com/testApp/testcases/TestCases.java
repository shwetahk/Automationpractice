package com.testApp.testcases;
import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.testApp.pages.itemPage;
import com.testApp.pages.loginPage;
import com.testApp.pages.myCart;
import com.testApp.pages.orderSummary;



public class TestCases {
	private static WebDriver driver;
	@BeforeSuite
  public void beforeSuite() {
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
  }

	
 
  @BeforeMethod(alwaysRun = true)
  public void beforeTest() {
	driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");  
	loginPage objLogin = new loginPage(driver);
	objLogin.loginToMyStore("test0617@yopmail.com", "BJSSTest"); 
  }
  
  @Test
  public void purchase2Items() throws InterruptedException { 
	  
	  
	  
	  	  
	  String item1Size = "M";
	  String Item1SizeSelected = "size"+"-"+item1Size.toLowerCase();	  
	  String item2Size = "S";
	  String Item2SizeSelected = "size"+"-"+item2Size.toLowerCase();
	  
	  
	  driver.navigate().to("http://automationpractice.com/index.php?id_category=4&controller=category#/categories-t_shirts");
	  driver.manage().window().maximize();
	  Thread.sleep(1000);
	  
	  itemPage item = new itemPage(driver);
	  //View item1 - change size - add to basket
	  item.quickViewItem();
	  driver.switchTo().frame(driver.findElement(By.className("fancybox-iframe")));
	  String item1Cost = item.getItemPrice();
	  item.changeItemSize(item1Size); 	  
	  item.addToCart();
	  item.continueShopping();
	  driver.switchTo().defaultContent();
	  //View Item2 - add to basket
	  item.selectBlouse(); 
	  driver.switchTo().frame(driver.findElement(By.className("fancybox-iframe")));
	  String item2Cost = item.getItemPrice();
	  item.addToCart();  
	  
	  //View items in cart and verify size, amount, total product cost, total cost with shipping
	  myCart cart = new myCart(driver);
	  cart.viewCart();	  
	  String pgTitle = cart.getPgTitle();
	  Assert.assertEquals(pgTitle, "Order - My Store");
	  cart.verifyItem1Size(Item1SizeSelected);
	  cart.verifyItem2Size(Item2SizeSelected);	  
	  cart.verifyItem1Price(item1Cost);  
	  cart.verifyItem2Price(item2Cost);	  
	  Double totalItemAmount = (Double.parseDouble(item1Cost.substring(1)))+(Double.parseDouble(item2Cost.substring(1)));
	  totalItemAmount = Math.round(totalItemAmount*100)/100.0d;
	  cart.verifyTotalProducts(totalItemAmount);  
	  cart.verifyTotal();
	  //Checkout
	  cart.checkout();	
	  //Logout
	  driver.findElement(By.className("logout")).click();
	  Thread.sleep(1000);
  }
  
  @Test(priority=1)
  public void reviwOrder() throws InterruptedException{
	  orderSummary myOrderSummary1 = new orderSummary(driver);
	  myOrderSummary1.viewPreviousOrders();
	  myOrderSummary1.selectItemFromOrder();
	  String msgText = "Change my order delivery date";
	  myOrderSummary1.addComment(msgText);
	  myOrderSummary1.confirmCommentAdded(msgText);
	  driver.findElement(By.className("logout")).click();
	  Thread.sleep(1000);
  }

 @Test(priority=2)
  public void captureImages() throws InterruptedException{
	  orderSummary myOrderSummary = new orderSummary(driver);
	  myOrderSummary.viewPreviousOrders();
	  myOrderSummary.selectItemFromOrder();
	  String orderColour = "Pink";
	  myOrderSummary.confirmOrderColour(orderColour);
	  driver.findElement(By.className("logout")).click();
	  Thread.sleep(1000);
	  
  }
 
@AfterMethod
  public void afterMethod(ITestResult result) throws InterruptedException, IOException, AWTException {
	  if(result.getStatus() == ITestResult.FAILURE)
	    {	
		  driver.manage().window().setSize(new Dimension(1024,768));
		  Date date = new Date();

		  File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  FileUtils.copyFile(screenshot, new File("failed_test_screenshot_"+result.getName()+"_"+date.getDate()+".png"));

	    }

  }
  
  @AfterTest
  public void afterTest(){
	  driver.quit();
  }
  
  

}
