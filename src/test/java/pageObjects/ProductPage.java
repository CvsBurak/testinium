package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Wait;

public class ProductPage {
	public WebDriver driver;
	public Wait wait;
	
	public ProductPage(WebDriver rdriver)
	{
		driver = rdriver;
		PageFactory.initElements(rdriver, this);
		wait = new Wait(rdriver);
	}
	
	@FindBy(className = "add-to-bs")
    WebElement btnAddToCart;
	
	@FindBy(className = "add-to-bs-tx")
    WebElement btnAddToCartTxt;
	
	@FindBy(xpath = "//*[@id=\"basketPreviewcontent\"]/div[1]/div[1]/p")
	WebElement itemCount;
	
	@FindBy(xpath = "//*[@class='pr-new-br']/span")
	WebElement itemName;
	
	@FindBy(className = "prc-dsc")
	WebElement lastDiscountPrice;
	
	@FindBy(className = "prc-slg")
	WebElement discountPrice;
	
	@FindBy(className = "prc-org")
	WebElement price;
	
	@FindBy(className = "basket-button-container")
	WebElement basketBtn;
	
	@FindBy(xpath = "//*[@id=\"product-detail-app\"]/div/div[2]/div[2]/div[3]/div[1]/div[1]")
	WebElement itemInfo;
	
	public void addToCart() {
		btnAddToCart.click();
	}
	
	public String addToCartAvaible() {
		boolean visibility = false;
		try {
    		wait.WaitForElement(btnAddToCartTxt, 2);
    		visibility = true;
    	}
    	catch (Exception e){
    		System.out.println("No Element");
    	}
		
		if (visibility) {
			return btnAddToCartTxt.getText();
		} else {
			return btnAddToCart.getText();
		}
    	
    	
		
	}
	
	public String itemCount() {
		wait.WaitForElement(itemCount, 5);
		return itemCount.getText();
	}
	
	public String itemName() {
		wait.WaitForElement(itemName, 5);
		return itemName.getText();
	}
	
	public String itemPrice() {
		wait.WaitForElement(lastDiscountPrice, 3);
		if (lastDiscountPrice.isDisplayed()) {
			return lastDiscountPrice.getText();
		}
		else if (discountPrice.isDisplayed()) {
			return discountPrice.getText();
		}
		else {
			return price.getText();
		}
	}
	
	public void clickBasket() {
		basketBtn.click();
	}
	
	public void hoverBasket() {
		Actions action = new Actions(driver);
		action.moveToElement(basketBtn).perform();
	}
	
	public String getItemInfo() {
		wait.WaitForElement(itemInfo, 5);
		return itemInfo.getText();
	}
	
}
