package pages;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DepositPage {
	protected WebDriver driver;
	private int amount = 50000;
	private String description = "This is description";
	private String headingTransaction = "Transaction details of Deposit for Account ";
	
	public DepositPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//define element
	//form
	@FindBy(name="accountno")
	public WebElement accountIdTxt;
	
	@FindBy(name="ammount")
	public WebElement amountTxt;
	
	@FindBy(name="desc")
	public WebElement descriptionTxt;
	
	@FindBy(xpath="//input[@type=\"submit\"]")
	public WebElement submitBtn;
	
	@FindBy(xpath="//input[@type=\"reset\"]")
	public WebElement resetBtn;
	
	//confirm page
	@FindBy(className="heading3")
	public WebElement depositStatusLbl;
	
	@FindBy(xpath="//*[@id=\"deposit\"]//td[contains(.,\"Account No\")]/following-sibling::td[1]")
	public WebElement accountIdLbl;
	
	@FindBy(xpath="//*[@id=\"deposit\"]//td[contains(.,\"Amount Credited\")]/following-sibling::td[1]")
	public WebElement amoutLbl;
	
	@FindBy(xpath="//*[@id=\"deposit\"]//td[contains(.,\"Description\")]/following-sibling::td[1]")
	public WebElement descriptionLbl;
	
	public void addNewDeposit(String accountId) {
		//Fill the add deposit form
		System.out.println("Fill the deposit form");
		accountIdTxt.sendKeys(accountId);
		amountTxt.sendKeys(String.valueOf(amount));
		descriptionTxt.sendKeys(description);
		
		//click the submit btn
		System.out.println("Click the submit button");
		submitBtn.click();
		
		//check the created page
		assertTrue("Heading transaction is exit with corresponding account Id", depositStatusLbl.getText().contains(headingTransaction + accountId));
		assertTrue("Account number is correctedly input with value " + accountId, accountIdLbl.getText().contains(accountId));
		assertTrue("Amount Credited is correctedly selected with value " + amount, amoutLbl.getText().contains(String.valueOf(amount)));
		assertTrue("Description is correctedly input with value " + description, descriptionLbl.getText().contains(description));
	}
}
