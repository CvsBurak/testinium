package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import utilities.Wait;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class HomePage {
	public WebDriver driver;
	public Wait wait;
	
	public HomePage(WebDriver rdriver)
	{
		driver = rdriver;
		PageFactory.initElements(rdriver, this);
		wait = new Wait(rdriver);
	}
	
	@FindBy(className = "login-container")
	WebElement loginBtn;
	
	@FindBy(id = "logged-in-container")
	WebElement  loggedInTxt;
	
	@FindBy(className = "search-box")
	WebElement searchInput;
	
	@FindBy(className = "search-icon")
	WebElement searchBtn;
	
	@FindBy(xpath = "/html/body/div[8]/div/div/a")
	WebElement closePopUp;
	
	@FindBy(xpath = "/html/body/div[7]/div/div/a")
	WebElement closePopUp1;
	
    
    public void clickLogin() {
    	loginBtn.click();
    }
    
    public void setSearchItem(String item) {
    	wait.WaitForElement(searchInput, 5);
    	searchInput.clear();
    	searchInput.sendKeys(item);
    }
    
    public void clickSearch() {
    	searchBtn.click();
    }
    
    public String isLogged() {
    	wait.WaitForElement(loggedInTxt, 5);
    	return loggedInTxt.getText();
    }
    
    public void clickClosePopUp() {
    	try {
    		wait.WaitForElement(closePopUp, 2);
    		closePopUp.click();
    	}
    	catch (Exception e){
    		System.out.println("No Element");
    	}
    	
    	try {
    		closePopUp1.click();
    	}
    	catch (Exception e){
    		System.out.println("No Element");
    	}
    	
    	
    }
    


}
