package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
	public WebDriver driver;
	
	public ProductPage(WebDriver rdriver)
	{
		driver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(className = "addToCartButton")
    WebElement btnAddToCart;
	
	@FindBy(className = "hb_sfc_close")
	WebElement closeBtn;
	
	@FindBy(xpath = "/html/body/div[2]/main/div[3]/section[1]/div[4]/div/div[4]/div[2]/div[2]/div/div[2]/table/tbody/tr[1]/td[3]/div/form/button")
	WebElement anotherSeller;
	
	@FindBy(xpath = "//*[@id='cartItemCount']")
	WebElement itemCount;
	
	@FindBy(xpath = "//*[@id='addToCart']/span[2]")
	WebElement sepet;
	
	public void addToCart() {
		btnAddToCart.click();
	}
	
	public void closePopUp() {
		closeBtn.click();
	}
	
	public void addFromOtherSeller() {
		anotherSeller.click();
	}
	
	public String itemCount() {
		return itemCount.getText();
	}
	
	public String sepet() {
		return sepet.getText();
	}
}
