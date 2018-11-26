package pages;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AccountPage {

	protected WebDriver driver;
	private int deposit = 30000;
	private String accountType = "Current";
	private String accountGenMess="Account Generated Successfully!!!";
	public String accountId;
	
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Define element
	//Create page
	@FindBy(name="cusid")
	public WebElement customerIdTxt;
	
	@FindBy(name="selaccount")
	public WebElement accountBox;
	
	@FindBy(name="inideposit")
	public WebElement depositTxt;
	
	@FindBy(xpath="//input[@type=\"submit\"]")
	public WebElement submitBtn;
	
	@FindBy(xpath="//input[@type=\"reset\"]")
	public WebElement resetBtn;
	
	//Confirm page
	@FindBy(className="heading3")
	public WebElement accountStatusLbl;
	
	@FindBy(xpath="//*[@id=\"account\"]//td[contains(.,\"Account ID\")]/following-sibling::td[1]")
	public WebElement accountIdLbl;
	
	@FindBy(xpath="//*[@id=\"account\"]//td[contains(.,\"Customer ID\")]/following-sibling::td[1]")
	public WebElement customerIdLbl;
	
	@FindBy(xpath="//*[@id=\"account\"]//td[contains(.,\"Customer Name\")]/following-sibling::td[1]")
	public WebElement customerNameLbl;
	
	@FindBy(xpath="//*[@id=\"account\"]//td[contains(.,\"Email\")]/following-sibling::td[1]")
	public WebElement emailLbl;
	
	//action
	public String addNewAccount(ArrayList<String> customerInformation) {
		try {
			//get data from customer information
			String customerId = customerInformation.get(0);
			String customerName = customerInformation.get(1);
			String email = customerInformation.get(2);
			
			//fill values
			System.out.println("Fill the data with customer Id is: " + customerInformation.get(0));
			customerIdTxt.sendKeys(customerInformation.get(0));
			Select selectValue = new Select(accountBox);
			selectValue.selectByValue(accountType);
			depositTxt.sendKeys(String.valueOf(deposit));
			
			//click the submit button
			System.out.println("Click the submit button");
			submitBtn.click();
			
			//Verify coressponding value
			assertTrue("Account generated succesfully", accountStatusLbl.getText().contains(accountGenMess));
			assertTrue("Customer Id is correctedly input with value " + customerId, customerIdLbl.getText().contains(customerId));
			assertTrue("Customer name is correctedly input with value " + customerName, customerNameLbl.getText().contains(customerName));
			assertTrue("Email is correctedly input with value " + email, emailLbl.getText().contains(email));
			
			//return the account Id for Deposit Method
			accountId = accountIdLbl.getText();
			System.out.println("New created account Id is: " + accountId);
			return accountId;
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		}
	}
}
