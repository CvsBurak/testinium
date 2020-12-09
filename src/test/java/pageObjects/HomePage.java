package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class HomePage {
	public WebDriver driver;
	
	public HomePage(WebDriver rdriver)
	{
		driver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(id = "myAccount")
	WebElement myAccountBtn;
	
    @FindBy(id = "login")
	WebElement loginBtn;
    
    @FindBy(xpath = "//*[@id=\"SearchBoxOld\"]/div/div/div[1]/div[2]/input")
    WebElement searchInput;
	
    @FindBy(xpath = "//*[@id=\"SearchBoxOld\"]/div/div/div[2]")
    WebElement searchBtn;
    
    @FindBy(xpath = "//*[@id='myAccount']/span/a/span[1]")
    WebElement myAccountTxt;
    
    @FindBy(xpath = "//*[@class='sf-MenuItems-1Sj7h']/li[9]")
    WebElement hobi;
    
    @FindBy(xpath = "//span[text()='Uzaktan Kumandalı Araçlar']")
    WebElement rcCar;
    
    public void clickMyAccount() {
    	Actions action = new Actions(driver);
    	action.moveToElement(myAccountBtn).perform();
    }
    
    public void clickLogin() {
    	loginBtn.click();
    }
    
    public void setSearchItem(String item) {
    	searchInput.clear();
    	searchInput.sendKeys(item);
    }
    
    public void clickSearch() {
    	searchBtn.click();
    }
    
    public String isLogged() {
    	return myAccountTxt.getText();
    }
    
    public void clickHobi() {
    	hobi.click();
    }
    
    public void clickRC() {
    	rcCar.click();
    }

}
