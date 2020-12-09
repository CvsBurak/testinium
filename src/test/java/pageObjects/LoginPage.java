package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	public WebDriver driver;
	
	public LoginPage(WebDriver rdriver) {
		driver = rdriver;
		PageFactory.initElements(rdriver, this);
		
	}
	 	@FindBy(id = "txtUserName")
	    WebElement txtUser;
	    
	    @FindBy(id = "txtPassword")
	    WebElement txtPassword;
	    
	    @FindBy(id = "btnLogin")
	    WebElement btnLogin;
	    
	    public String inLoginPage() {
	    	return btnLogin.getText();
	    }
	    
	    public void setUserName(String name) {
	    	txtUser.clear();
	    	txtUser.sendKeys(name);
	    }
	   
	    public void setPassword(String pwd) {
	    	txtPassword.clear();
	    	txtPassword.sendKeys(pwd);
	    }
	    
	    public void clickLogin() {
	    	btnLogin.click();
	    }

}
