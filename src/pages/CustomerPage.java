package pages;

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
	private String email = "AutomationTeam_973@yopmail.com";
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

	//get set value
	public String getCustomerCreated() {
		return customerCreated;
	}

	public void setCustomerCreated(String customerCreated) {
		this.customerCreated = customerCreated;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return femaleRadio.getText();
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getVerifiedDob() {
		return verifiedDob;
	}

	public void setVerifiedDob(String verifiedDob) {
		this.verifiedDob = verifiedDob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getPinNo() {
		return pinNo;
	}

	public void setPinNo(String pinNo) {
		this.pinNo = pinNo;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
			setVerifiedDob(String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(date));
			gender = getGender();
			//fill data
			System.out.println("Fill data");
			nameTxb.sendKeys(getName());
			femaleRadio.click();
			dateOfBirth.sendKeys(dob);
			addressTxa.sendKeys(getAddress());
			cityTxb.sendKeys(getCity());
			stateTxb.sendKeys(getStage());
			pinTxb.sendKeys(getPinNo());
			phoneTxb.sendKeys(getPhoneNo());
			emailTxb.sendKeys(getEmail());
			passwordTxb.sendKeys(password);
			
			//click the submit button
			System.out.println("Click the submit button");
			submitBtn.click();
			
			//provide data for account acction
			customerId = customerIdLbl.getText();
			System.out.println("New customer Id is: " + customerId);
			customerInformation.add(customerId);
			customerInformation.add(getName());
			customerInformation.add(getEmail());
			customerInformation.add(gender);
			
			return customerInformation;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}
}
