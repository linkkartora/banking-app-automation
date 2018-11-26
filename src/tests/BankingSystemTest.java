package tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.*;

public class BankingSystemTest {
	protected static WebDriver driver;
	protected StringBuffer verificationErrors = new StringBuffer();
	protected static HomePage homePage;
	protected static LandingPage landingPage;
	protected static AccountPage accountPage;
	protected static CustomerPage customerPage;
	protected static DepositPage depositPage;
	protected static WebDriverWait wait;
	private static ArrayList<String> customerInformation = new ArrayList<String>();
	private static String accountId;
	
	@BeforeTest
	public static void setUp() {
		System.setProperty("webdriver.chrome.driver", "..\\bankingsystem\\libs\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://demo.guru99.com/V4/");
		wait = new WebDriverWait(driver, 5);
	}
	
	//Login to main page and verify landing page is contain corresponding Manager Id
	@Test
	public static void testLogin() {
		homePage = new HomePage(driver);
		homePage.LoginToSite("mngr164729", "ydUbeda");
		
		landingPage = new LandingPage(driver);
		System.out.println("Wait to new page loaded");
		wait.until(ExpectedConditions.visibilityOf(landingPage.welcomeMessage));
		System.out.println("Verify correct Id: ");
		assertTrue(landingPage.getManagerId().contains("mngr164729"));
	}
	
	//Create new customer and return the customer Id
	@Test
	public static ArrayList<String> testCreateCustomer() {
		customerPage = new CustomerPage(driver);
		landingPage = new LandingPage(driver);
		
		landingPage.customerTab.click();
		
		wait.until(ExpectedConditions.visibilityOf(customerPage.nameTxb));
		
		customerInformation = customerPage.inputCorrectData();
		
		return customerInformation;
	}
	
	//Create new account and return new account Id
	@Test
	public static String testCreateAccount() {
		accountPage = new AccountPage(driver);
		landingPage = new LandingPage(driver);
		
		landingPage.accountTab.click();
		
		wait.until(ExpectedConditions.visibilityOf(accountPage.customerIdTxt));
		return accountId = accountPage.addNewAccount(customerInformation);
	}
	
	//Ad new Deposit
	@Test
	public static void testAddDeposit() {
		depositPage = new DepositPage(driver);
		landingPage = new LandingPage(driver);
		
		landingPage.depositTab.click();
		wait.until(ExpectedConditions.visibilityOf(depositPage.amountTxt));
		depositPage.addNewDeposit(accountId);
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		driver.quit();
	}
	
	public static void main(String[] args) throws Exception {
		setUp();
		testLogin();
		testCreateCustomer();
		testCreateAccount();
		testAddDeposit();
		tearDown();
	}
}
