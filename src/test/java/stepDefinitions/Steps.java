package stepDefinitions;

import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.en.*;
import junit.framework.Assert;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.ProductPage;
import pageObjects.BoutiquePage;

public class Steps {
	
	public WebDriver driver;
	public HomePage hp;
	public LoginPage lp;
	public ProductPage productP;
	public BoutiquePage productsP;
	
	@Given("the home page of hepsiburada is displayed")
	public void the_home_page_of_hepsiburada_is_displayed() {
		String path = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", "/Users/cvsburak/Desktop/chromedriver");
		ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-gpu");
        options.addArguments("Chrome/8.0");
        options.addArguments("--no-sandbox");
		driver = new ChromeDriver(options);
		
		
		hp = new HomePage(driver);
		lp = new LoginPage(driver);
		productsP = new BoutiquePage(driver);
		productP = new ProductPage(driver);
		driver.get("https://www.hepsiburada.com");
		
	}

	@When("the user clicks to login button")
	public void the_user_clicks_to_login_button() throws InterruptedException {
		hp.clickMyAccount();
		Thread.sleep(500);
		hp.clickLogin();
		Thread.sleep(4000);
	}
	
	
	@Then("the user on the login page")
	public void the_user_on_the_login_page() {
		Assert.assertEquals("Giriş yap", lp.inLoginPage());

	}

	@When("the user enters username {string} and password {string}")
	public void the_user_enters_username_and_password(String username, String password) {
		lp.setUserName(username);
		lp.setPassword(password);
	}

	@When("Clicks to login button")
	public void clicks_to_login_button() throws InterruptedException {
		lp.clickLogin();
		Thread.sleep(3000);
	}

	@Then("User must be on home page and login text should change to {string}")
	public void user_must_be_on_home_page_and_login_text_should_change_to(String string) {
		if (driver.getPageSource().contains("Bilgileriniz eksik veya hatalı.")) {
			driver.close();
			Assert.assertTrue(false);
		}
		else {
			Assert.assertEquals(string, hp.isLogged());
		}
	}
	
	@Then("Close driver")
	public void close_driver() {
		driver.quit();
	}
	
	// ----------------------------- ----------------------- -----------------------
	@When("the user types {string} to search bar")
	public void the_user_types_to_search_bar(String item) throws InterruptedException {
	    hp.setSearchItem(item);
	    hp.clickSearch();
	    Thread.sleep(2000);
	}

	@Then("the user will be on the {string} page")
	public void the_user_will_be_on_the_page(String item) {
		Assert.assertEquals(item, productsP.itemName());
	}

	@When("the user selects random product on the product and clicks it")
	public void the_user_selects_random_product_on_the_product() throws InterruptedException {
	    productsP.clickRandomItem();
	    Thread.sleep(2000);
	}


	@Then("User must be on products page and can see {string}")
	public void user_must_be_on_products_page(String string) {
		Assert.assertEquals(string, productP.sepet());
	}

	@When("the user adds item to cart")
	public void the_user_adds_item_to_cart() throws InterruptedException {
	    productP.addToCart();
	    Thread.sleep(500);
	    productP.closePopUp();
	}

	@When("the user adds item to cart from different seller")
	public void the_user_adds_item_to_cart_from_different_seller() throws InterruptedException{
	    try{
	    	productP.addFromOtherSeller();
	    } catch(Exception e) {
	    	Assert.assertTrue("There is no other seller for this item", true);
	    }
	    Thread.sleep(500);
	    try{
	    	productP.closePopUp();
	    } catch(Exception e) {

	    }
	}

	@Then("there are items on the cart")
	public void there_are_items_on_the_cart() {
		System.out.println(productP.itemCount());
	    if (productP.itemCount() == "2") {
	    	Assert.assertTrue(true);
	    }
	    else if (productP.itemCount() == "1") {
	    	Assert.assertTrue(true);
	    }
	    else {
	    	Assert.assertFalse("You couldn't add item to basket.", false);
	    }
		

	}

	//-------------------------- ------------------------------- -----------------------------
	@When("the user goes to rc section under the hobi tab")
	public void the_user_goes_to_rc_section_under_the_hobi_tab() throws InterruptedException {
	    hp.clickHobi();
	    Thread.sleep(500);
	    hp.clickRC();
	}

	@Then("the user will see {string}")
	public void the_user_will_see(String string) {
		if (driver.getPageSource().contains(string)) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

	@When("the user finds a {string} and clicks it")
	public void the_user_finds_a_and_clicks_it(String string) throws InterruptedException {
	    List<WebElement> items = productsP.titleName();
	    List<WebElement> click_item = productsP.clickItem();
	    Integer item_count = click_item.size();
	    Integer i = 1;
	    Integer page = 2;
	    while (i < item_count+1) {
	    	String item = items.get(i).getAttribute("title");
	    	if (item.contains(string)) {
	    		click_item.get(i).click();
	    		
	    		break;
	    	}
	    	
	    	else if (i == 24) {
	    		String pageCount = String.format("page-%2d", page);
	    		driver.findElement(By.className(pageCount)).click();
	    		i = 1;
	    		page += 1;
	    		items = productsP.titleName();
	    		click_item = productsP.clickItem();
	    		Thread.sleep(500);
	    		
	    	i += 1;
	    	
	    	Assert.assertEquals(string, productP.sepet());
	    	}
	    }
	    
	    
	}



}
