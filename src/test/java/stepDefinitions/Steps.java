package stepDefinitions;

import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import enums.Context;
import io.cucumber.java.en.*;
import junit.framework.Assert;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.ProductPage;
import pageObjects.BoutiquePage;
import pageObjects.BasketPage;
import utilities.ScenarioContext;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Steps {
	
	public WebDriver driver;
	public HomePage hp;
	public LoginPage lp;
	public ProductPage productP;
	public BoutiquePage boutiqueP;
	public BasketPage basketP;
	public ScenarioContext context;
	public static Logger logger;
	
	@Given("the home page of trendyol is displayed")
	public void the_home_page_of_hepsiburada_is_displayed() throws InterruptedException {
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
		
		logger = Logger.getLogger("trendyol");
		PropertyConfigurator.configure("Log4j.properties");
		hp = new HomePage(driver);
		lp = new LoginPage(driver);
		boutiqueP = new BoutiquePage(driver);
		productP = new ProductPage(driver);
		basketP = new BasketPage(driver);
		context = new ScenarioContext();
		
		logger.info("********** Launching Browser **********");
		logger.info("Going to trendyol's homepage");
		driver.get("https://www.trendyol.com");
		hp.clickClosePopUp();
		
	}
	
	@Then("user must me on the home page")
	public void the_user_on_the_home_page() {
		Assert.assertEquals("En Trend Ürünler Türkiye'nin Online Alışveriş Sitesi Trendyol'da", driver.getTitle());
	}

	@When("the user clicks to login button")
	public void the_user_clicks_to_login_button() throws InterruptedException {
		logger.info("Going to login page");
		hp.clickLogin();
	}
	
	
	@Then("the user on the login page")
	public void the_user_on_the_login_page() {
		Assert.assertEquals("GIRIŞ YAP", lp.inLoginPage());
	}

	@When("the user enters username {string} and password {string}")
	public void the_user_enters_username_and_password(String username, String password) {
		String userInfo = String.format("Entering username %s and password %s", username, password);
		logger.info(userInfo);
		lp.setUserName(username);
		lp.setPassword(password);
	}

	@When("Clicks to login button")
	public void clicks_to_login_button() throws InterruptedException {
		logger.info("Logging in");
		lp.clickLogin();
		Thread.sleep(500);
	}

	@Then("User must be on home page and login text should change to {string}")
	public void user_must_be_on_home_page_and_login_text_should_change_to(String string) {
		if (driver.getPageSource().contains("E-posta adresiniz ve/veya şifreniz hatalı.") || driver.getPageSource().contains("Lütfen geçerli bir e-posta adresi giriniz.")) {
			logger.info("Email and password is wrong or empty, terminating the test");
			driver.close();
			Assert.assertTrue(false);
		}
		else {
			logger.info("Logged in succesfully");
			Assert.assertEquals(string, hp.isLogged());
		}
	}
	
	@Then("Close driver")
	public void close_driver() {
		logger.info("************* Test finished *************");
		driver.quit();
	}
	
	
	    // ADD ITEM TO BASKET STEPS
	
	@When("user writes {string} on the search bar")
	public void writes_on_the_search_bar(String string) throws InterruptedException {
		String search = String.format("Searching %s", string);
		logger.info(search);
	    hp.setSearchItem(string);
	    Thread.sleep(500);
	}

	@When("clicks the search")
	public void clicks_the_search() throws InterruptedException {
	    hp.clickSearch();
	}

	@Then("the user on the {string} page")
	public void the_user_on_the_boutique_page(String string) {
		Assert.assertEquals(string, boutiqueP.getTitle());
	}

	@When("the user selects random product on the page")
	public void the_user_selects_random_product_on_the_page() throws InterruptedException {
		logger.info("Selecting random product");
	    boutiqueP.clickRandomItem();
	}

	@Then("user must be on the products page")
	public void user_must_be_on_the_products_page() {
		Assert.assertEquals("Ürün Bilgileri", productP.getItemInfo());
	}

	@When("user adds item to the basket")
	public void user_adds_item_to_the_basket() throws InterruptedException {
		logger.info("Checking if the product is avaible to buy");
		String basket = productP.addToCartAvaible();
		System.out.print(basket);
	    if (basket.contains("Sepet")){
	    	logger.info("Adding product to basket");
	    	productP.addToCart();
	    	String price = productP.itemPrice();
		    context.setContext(Context.PRODUCT_PRICE, price);
		    Thread.sleep(3000);
	    } else {
	    	logger.info("Product is not avaible to buy, terminating the test");
	    	driver.close();
	    }
	    
	}

	@Then("basket count must increased one")
	public void basket_count_must_increased_one() {
		productP.hoverBasket();
		String count = productP.itemCount();
		Assert.assertTrue(count.contains("1"));
	}

	@When("user clicks the basket and goes to basket page")
	public void user_clicks_the_basket_and_goes_to_basket_page() throws InterruptedException {
		logger.info("Going to basket page");
	    productP.clickBasket();
	}

	@Then("user must see the value of item price in the products page and the baskets page must be equal")
	public void prices_equal() {
		basketP.productCount();
		String source = driver.getPageSource();
		String price = (String) context.getContext(Context.PRODUCT_PRICE);
		boolean result = source.contains(price);
		Assert.assertTrue(result);
	    
	}

	@When("user increase the product count to two")
	public void user_increase_the_product_count_to_two() throws InterruptedException {
		logger.info("Checking if product is addable for more");
		String addOne = basketP.addOneMoreEnabled();
		System.out.print(addOne);
	    if (!addOne.contains("passive")) {
	    	logger.info("Increasing quantity of product");
	    	basketP.addOneMore();
	    	Thread.sleep(1500);
	    } else {
	    	logger.info("You can only buy one, termimating the test");
	    	driver.close();
	    }
	}

	@Then("item count must increased to two")
	public void item_count_must_increased_to_two() {
		String basket = basketP.productCount();
		boolean contain = basket.contains("2");
		Assert.assertTrue(contain);
	}
	    



}
