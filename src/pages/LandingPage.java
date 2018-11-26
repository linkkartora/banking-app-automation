package pages;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;

public class LandingPage {

	WebDriver driver;
	
	//define element
	@FindBy(xpath="//table//tr[@class='heading3']")
	public WebElement welcomeMessage;
	
	@FindBy(css="a[href*='addcustomerpage.php']")
	public WebElement customerTab;
	
	@FindBy(css="a[href*='addAccount.php']")
	public WebElement accountTab;
	
	@FindBy(css="a[href*='DepositInput.php']")
	public WebElement depositTab;
	
	public LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getManagerId() {
		return welcomeMessage.getText();
	}

}
