package pages;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerPage {
	protected WebDriver driver;
	
	//data
	private String name = "Linh Nguyen";
	private String address = "2 Nguyen Hue";
	private String city = "Ho Chi Minh City";
	private String stage = "Ho Chi Minh";
	private String pinNo = "700000";
	private String phoneNo = "0123456789";
	private String email = "AutomationTeam_975@yopmail.com";
	private String password = "abc123";
	private String gender;
	private String customerCreated = "Customer Registered Successfully!!!";
	private String customerId;
	public ArrayList<String> customerInformation = new ArrayList<String>();
	
	//handle date
	private String dob;
	private String verifiedDob;
	private int year = 1990, month = 11, date = 12;
	
	public CustomerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//define element
	//input page
	@FindBy(name="name")
	public WebElement nameTxb;
	
	@FindBy(xpath="//input[@type=\"radio\"][@value=\"m\"]")
	public WebElement maleRadio;
	
	@FindBy(xpath="//input[@type=\"radio\"][@value=\"f\"]")
	public WebElement femaleRadio;
	
	@FindBy(id="dob")
	public WebElement dateOfBirth;
	
	@FindBy(name="addr")
	public WebElement addressTxa;
	
	@FindBy(name="city")
	public WebElement cityTxb;
	
	@FindBy(name="state")
	public WebElement stateTxb;
	
	@FindBy(name="pinno")
	public WebElement pinTxb;
	
	@FindBy(name="telephoneno")
	public WebElement phoneTxb;
	
	@FindBy(name="emailid")
	public WebElement emailTxb;
	
	@FindBy(name="password")
	public WebElement passwordTxb;
	
	@FindBy(xpath="//input[@type=\"submit\"]")
	public WebElement submitBtn;
	
	@FindBy(xpath="//input[@type=\"reset\"]")
	public WebElement resetBtn;
	
	//Confirm page
	@FindBy(className="heading3")
	public WebElement customerStatusLbl;
	
	@FindBy(xpath="//*[@id=\"customer\"]//td[contains(.,\"Customer ID\")]/following-sibling::td[1]")
	public WebElement customerIdLbl;
	
	@FindBy(xpath="//*[@id=\"customer\"]//td[contains(.,\"Customer Name\")]/following-sibling::td[1]")
	public WebElement customerNameLbl;
	
	@FindBy(xpath="//*[@id=\"customer\"]//td[contains(.,\"Gender\")]/following-sibling::td[1]")
	public WebElement genderLbl;
	
	@FindBy(xpath="//*[@id=\"customer\"]//td[contains(.,\"Birthdate\")]/following-sibling::td[1]")
	public WebElement birthdayLbl;
	
	@FindBy(xpath="//*[@id=\"customer\"]//td[contains(.,\"Address\")]/following-sibling::td[1]")
	public WebElement addressLbl;
	
	@FindBy(xpath="//*[@id=\"customer\"]//td[contains(.,\"City\")]/following-sibling::td[1]")
	public WebElement cityLbl;
	
	@FindBy(xpath="//*[@id=\"customer\"]//td[contains(.,\"State\")]/following-sibling::td[1]")
	public WebElement stateLbl;
	
	@FindBy(xpath="//*[@id=\"customer\"]//td[contains(.,\"Pin\")]/following-sibling::td[1]")
	public WebElement pinLbl;
	
	@FindBy(xpath="//*[@id=\"customer\"]//td[contains(.,\"Mobile No.\")]/following-sibling::td[1]")
	public WebElement phoneLbl;
	
	@FindBy(xpath="//*[@id=\"customer\"]//td[contains(.,\"Email\")]/following-sibling::td[1]")
	public WebElement emailLbl;
	
	//action
	public ArrayList<String> inputCorrectData() {
		try {
			//create data
			dob = String.valueOf(month) + String.valueOf(date) + String.valueOf(year);
			verifiedDob = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(date);
			gender = femaleRadio.getText();
			
			//fill data
			System.out.println("Fill data");
			nameTxb.sendKeys(name);
			femaleRadio.click();
			dateOfBirth.sendKeys(dob);
			addressTxa.sendKeys(address);
			cityTxb.sendKeys(city);
			stateTxb.sendKeys(stage);
			pinTxb.sendKeys(pinNo);
			phoneTxb.sendKeys(phoneNo);
			emailTxb.sendKeys(email);
			passwordTxb.sendKeys(password);
			
			//click the submit button
			System.out.println("Click the submit button");
			submitBtn.click();
			
			//check the created page
			assertTrue("Customer created succesfully", customerStatusLbl.getText().contains(customerCreated));
			assertTrue("Name is correctedly input with value " + name, customerNameLbl.getText().contains(name));
			assertTrue("Gender is correctedly selected with value " + gender, genderLbl.getText().contains(gender));
			assertTrue("Birthday is correctedly input with value " + verifiedDob, birthdayLbl.getText().contains(verifiedDob));
			assertTrue("Address is correctedly input with value " + address, addressLbl.getText().contains(address));
			assertTrue("City is correctedly input with value " + city, cityLbl.getText().contains(city));
			assertTrue("State is correctedly input with value " + stage, stateLbl.getText().contains(stage));
			assertTrue("Pin is correctedly input with value " + pinNo, pinLbl.getText().contains(pinNo));
			assertTrue("Mobile No. is correctedly input with value " + phoneNo, phoneLbl.getText().contains(phoneNo));
			assertTrue("Email is correctedly input with value " + email, emailLbl.getText().contains(email));
			
			//provide data for account acction
			customerId = customerIdLbl.getText();
			System.out.println("New customer Id is: " + customerId);
			customerInformation.add(customerId);
			customerInformation.add(name);
			customerInformation.add(email);
			
			return customerInformation;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}
}
