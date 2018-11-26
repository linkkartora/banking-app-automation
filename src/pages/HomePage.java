package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	//define element
	@FindBy(name="uid")
	WebElement userIdTxb;
	
	@FindBy(name="password")
	WebElement passwordTxb;
	
	@FindBy(name="btnLogin")
	WebElement loginBtn;
	
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//action
	public void LoginToSite(String userId, String password) {
		System.out.println("Input userId: " + userId);
		userIdTxb.sendKeys(userId);
		
		System.out.println("Input password: " + password);
		passwordTxb.sendKeys(password);
		
		System.out.println("Click the login btn");
		loginBtn.click();
	}
}
