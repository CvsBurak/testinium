package pageObjects;

import java.util.List;
import java.util.Random;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Wait;

public class BoutiquePage {
	
	public WebDriver driver;
	public Wait wait;
	
	public BoutiquePage(WebDriver rdriver)
	{
		driver = rdriver;
		PageFactory.initElements(rdriver, this);
		wait = new Wait(rdriver);
	}
	
	@FindBy(className = "p-card-wrppr")
	List<WebElement> items;
	
	@FindBy(xpath = "//*[@id=\"search-app\"]/div/div[1]/div[2]/div[1]/div[1]/div/h1")
	WebElement title;
	
	
	public void clickRandomItem() {
		Actions action = new Actions(driver);
		int itemNum = items.size();
		Random rand = new Random();
		int randomNum = rand.nextInt((itemNum - 1) + 1) + 1;
		action.moveToElement(items.get(randomNum)).click().perform();		
	}
	
	public String getTitle() {
		wait.WaitForElement(title, 5);
		return title.getText();
	}
	

	
	

}
