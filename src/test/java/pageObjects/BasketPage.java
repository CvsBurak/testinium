package pageObjects;

import java.util.List;
import java.util.Random;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Wait;

public class BasketPage {
	
	public WebDriver driver;
	public Wait wait;
	
	public BasketPage(WebDriver rdriver)
	{
		driver = rdriver;
		PageFactory.initElements(rdriver, this);
		wait = new Wait(rdriver);
	}

	
	@FindBy(xpath = "//*[@id=\"partial-basket\"]/div/div[1]")
	WebElement count;
	
	@FindBy(xpath = "//*[@id=\"partial-basket\"]/div/div[3]/div[2]/div[3]/div[1]/div/button[2]")
	WebElement addOne;
	
	
	public String productCount() {
		wait.WaitForElement(count, 5);
		return count.getText();
	}
	
	public boolean addOneMoreEnabled() {
		wait.WaitForElement(addOne, 5);
		boolean canClickable = addOne.isEnabled();
		return canClickable;

	}
	
	public void addOneMore() {
		wait.WaitForElement(addOne, 5);
		addOne.click();
	}
	
}

