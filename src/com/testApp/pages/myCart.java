package com.testApp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class myCart {
	WebDriver driver;
	
	By item1SizeLocator = By.className("first_item");
	By item2SizeLocator = By.xpath("//*[@id='product_2_7_0_20840']/td[1]/a");
	By item1NewSizeLocator = By.xpath("//*[@id='product_1_3_0_20840']/td[1]/a");
	By item1PriceLocator = By.xpath("//*[@id='total_product_price_1_3_20840']");
	By item2PriceLocator = By.xpath("//*[@id='total_product_price_2_7_20840']");
	By productTotalLocator = By.xpath("//*[@id='total_product']");
	By totalExclTaxLocator = By.id("total_price_without_tax");
	By totalProductPriceLocator = By.xpath("//*[@id='total_product']");
	By shippingCostLocator = By.id("total_shipping");
	By checkOutLocator = By.xpath("//*[@id='center_column']/p[2]/a[1]/span");
	By processAddressLocator = By.name("processAddress");
	By termsCheckboxLocator = By.xpath("//*[@id='cgv']");
	By processCarrierLocator = By.name("processCarrier");
	By payBankWireLocator = By.xpath("//*[@id='HOOK_PAYMENT']/div[1]/div/p/a");
	By confirmOrderLocator = By.xpath("//*[@id='cart_navigation']/button/span");
	
	public myCart(WebDriver driver){
		this.driver = driver;
	}

	public void viewCart(){
		driver.get("http://automationpractice.com/index.php?controller=order");
	}
	
	public String getPgTitle(){
		String pgTitle = driver.getTitle();
		return pgTitle;
	}
	
	public String getItemSize(){
		String itemSize = driver.findElement(item1SizeLocator).getAttribute("innerHTML");
		System.out.println(itemSize.contains("size-m"));
		return itemSize;
		
	}

	public void verifyItem2Size(String item2sizeSelected) {
		String itemSize1new = driver.findElement(item2SizeLocator).getAttribute("href");
		Assert.assertTrue(itemSize1new.contains(item2sizeSelected));
		
	}

	public void verifyItem1Size(String item1SizeSelected) {
		String itemSize1new = driver.findElement(item1NewSizeLocator).getAttribute("href");
		Assert.assertTrue(itemSize1new.contains(item1SizeSelected));
			
	}

	public void verifyItem1Price(String item1Cost) {
		String item1PriceInCart = driver.findElement(item1PriceLocator).getAttribute("innerHTML");
		Assert.assertTrue(item1PriceInCart.trim().contentEquals(item1Cost));
	}

	public void verifyItem2Price(String item2Cost) {
		String item1PriceInCart = driver.findElement(item2PriceLocator).getAttribute("innerHTML");
		Assert.assertTrue(item1PriceInCart.trim().contentEquals(item2Cost));
	}




	public void verifyTotalProducts(Double totalItemAmount) {
		String totalProductPrice = driver.findElement(productTotalLocator).getAttribute("innerHTML");
		String orderAmount = "$"+String.valueOf(totalItemAmount);
		Assert.assertTrue(totalProductPrice.contentEquals(orderAmount));
		// TODO Auto-generated method stub
		
	}




	public void verifyTotal() {
		String total = driver.findElement(totalExclTaxLocator).getAttribute("innerHTML");
		String totalProductPrice = driver.findElement(totalProductPriceLocator).getAttribute("innerHTML");
		String shippingCost = driver.findElement(shippingCostLocator).getAttribute("innerHTML");
		Double calcTotal = ((Double.parseDouble(shippingCost.substring(1)))+(Double.parseDouble(totalProductPrice.substring(1))));
		calcTotal = Math.round(calcTotal*100)/100.0d;
		String calcTotalAmount = "$"+String.valueOf(calcTotal);
		Assert.assertTrue(total.contentEquals(calcTotalAmount));	
	}




	public void checkout() throws InterruptedException {
		driver.findElement(checkOutLocator).click();
		driver.findElement(processAddressLocator).click();
		Thread.sleep(1000);
		driver.findElement(termsCheckboxLocator).click();
		Thread.sleep(1000);
		driver.findElement(processCarrierLocator).click();
		Thread.sleep(1000);
		driver.findElement(payBankWireLocator).click();
		Thread.sleep(1000);
		driver.findElement(confirmOrderLocator).click();
		Assert.assertTrue(driver.getTitle().contentEquals("Order confirmation - My Store"));
		
	}







	

}
