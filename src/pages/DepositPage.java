package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DepositPage {
	protected WebDriver driver;
	private int amount = 50000;
	private String description = "Description";
	private String headingTransaction = "Transaction details of Deposit for Account ";
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHeadingTransaction() {
		return headingTransaction;
	}

	public void setHeadingTransaction(String headingTransaction) {
		this.headingTransaction = headingTransaction;
	}
	
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
		try {
			//Fill the add deposit form
			System.out.println("Fill the deposit form");
			accountIdTxt.sendKeys(accountId);
			amountTxt.sendKeys(String.valueOf(getAmount()));
			descriptionTxt.sendKeys(getDescription());
			
			//click the submit btn
			System.out.println("Click the submit button");
			submitBtn.click();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
