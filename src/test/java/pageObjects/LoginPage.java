package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Wait;

public class LoginPage {
	public WebDriver driver;
	public Wait wait;
	
	public LoginPage(WebDriver rdriver) {
		driver = rdriver;
		PageFactory.initElements(rdriver, this);
		wait = new Wait(rdriver);
		
	}
	 	@FindBy(id = "login-email")
	    WebElement txtUser;
	    
	    @FindBy(id = "login-password-input")
	    WebElement txtPassword;
	    
	    @FindBy(xpath = "//*[@id='login-register']/div[3]/div[1]/form/button")
	    WebElement btnLogin;
	    
	    public String inLoginPage() {
	    	wait.WaitForElement(btnLogin, 5);
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
