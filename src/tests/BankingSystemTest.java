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
		
		homePage = new HomePage(driver);
		customerPage = new CustomerPage(driver);
		landingPage = new LandingPage(driver);
		depositPage = new DepositPage(driver);
		accountPage = new AccountPage(driver);
	}
	
	//Login to main page and verify landing page is contain corresponding Manager Id
	@Test
	public static void testLogin() {
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
		landingPage.customerTab.click();
		
		wait.until(ExpectedConditions.visibilityOf(customerPage.nameTxb));
		
		customerInformation = customerPage.inputCorrectData();
		String gender = customerInformation.get(3);
		
		//check the created page
		assertTrue("Customer created succesfully", 
				customerPage.customerStatusLbl.getText().contains(customerPage.getCustomerCreated()));
		assertTrue("Name is correctedly input with value " + customerPage.getName(), 
				customerPage.customerNameLbl.getText().contains(customerPage.getName()));
		assertTrue("Gender is correctedly selected with value " + gender, 
				customerPage.genderLbl.getText().contains(gender));
		assertTrue("Birthday is correctedly input with value " + customerPage.getVerifiedDob(), 
				customerPage.birthdayLbl.getText().contains(customerPage.getVerifiedDob()));
		assertTrue("Address is correctedly input with value " + customerPage.getAddress(), 
				customerPage.addressLbl.getText().contains(customerPage.getAddress()));
		assertTrue("City is correctedly input with value " + customerPage.getCity(), 
				customerPage.cityLbl.getText().contains(customerPage.getCity()));
		assertTrue("State is correctedly input with value " + customerPage.getStage(), 
				customerPage.stateLbl.getText().contains(customerPage.getStage()));
		assertTrue("Pin is correctedly input with value " + customerPage.getPinNo(), 
				customerPage.pinLbl.getText().contains(customerPage.getPinNo()));
		assertTrue("Mobile No. is correctedly input with value " + customerPage.getPhoneNo(), 
				customerPage.phoneLbl.getText().contains(customerPage.getPhoneNo()));
		assertTrue("Email is correctedly input with value " + customerPage.getEmail(), 
				customerPage.emailLbl.getText().contains(customerPage.getEmail()));
		
		//return customer information
		return customerInformation;
	}
	
	//Create new account and return new account Id
	@Test
	public static String testCreateAccount() {		
		//get data from customer information
		String customerId = customerInformation.get(0);
		String customerName = customerInformation.get(1);
		String email = customerInformation.get(2);
		landingPage.accountTab.click();
		
		wait.until(ExpectedConditions.visibilityOf(accountPage.customerIdTxt));
		accountId = accountPage.addNewAccount(customerInformation);
		
		//Verify coressponding value
		assertTrue("Account generated succesfully", 
				accountPage.accountStatusLbl.getText().contains(accountPage.getAccountGenMess()));
		assertTrue("Customer Id is correctedly input with value " + customerId, 
				accountPage.customerIdLbl.getText().contains(customerId));
		assertTrue("Customer name is correctedly input with value " + customerName, 
				accountPage.customerNameLbl.getText().contains(customerName));
		assertTrue("Email is correctedly input with value " + email, 
				accountPage.emailLbl.getText().contains(email));
		
		return accountId;
	}
	
	//Ad new Deposit
	@Test
	public static void testAddDeposit() {		
		landingPage.depositTab.click();
		wait.until(ExpectedConditions.visibilityOf(depositPage.amountTxt));
		
		//action
		depositPage.addNewDeposit(accountId);
		
		//check the created page
		assertTrue("Heading transaction is exit with corresponding account Id", 
				depositPage.depositStatusLbl.getText().contains(depositPage.getHeadingTransaction() + accountId));
		assertTrue("Account number is correctedly input with value " + accountId, 
				depositPage.accountIdLbl.getText().contains(accountId));
		assertTrue("Amount Credited is correctedly selected with value " + depositPage.getAmount(), 
				depositPage.amoutLbl.getText().contains(String.valueOf(depositPage.getAmount())));
		assertTrue("Description is correctedly input with value " + depositPage.getDescription(), 
				depositPage.descriptionLbl.getText().contains(depositPage.getDescription()));
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
