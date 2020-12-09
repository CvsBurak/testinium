package pageObjects;

import java.util.List;
import java.util.Random;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BoutiquePage {
	
	public WebDriver driver;
	
	public BoutiquePage(WebDriver rdriver)
	{
		driver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(className = "search-item")
	List<WebElement> items;
	
	@FindBy(className = "keyword")
	WebElement item;
	
	@FindBy(className = "product-title")
	List<WebElement> title;
	
	public String itemName() {
		return item.getText();
	}
	
	public void clickRandomItem() {
		int itemNum = items.size();
		Random rand = new Random();
		int randomNum = rand.nextInt((itemNum - 1) + 1) + 1;
		items.get(randomNum).click();		

	}
	
	public List<WebElement> clickItem() {
		return items;
	}
	
	public List<WebElement> titleName() {
		return title;
	}
	
	

}
