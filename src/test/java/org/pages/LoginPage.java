package org.pages;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	private WebDriver driver;
	@FindBy(xpath="//*[text()=' Login']") WebElement loginOption;
	@FindBy(id="usernameLogin") WebElement userNameField;
	@FindBy(id="passwordLogin") WebElement passwordField;
	@FindBy(how=How.ID, using="login") WebElement loginButton;
	public LoginPage(WebDriver driver) {
		this .driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void login(String UserName, String Password){
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		loginOption.click();
		userNameField.sendKeys(UserName);
		passwordField.sendKeys(Password);
		loginButton.click();
		
	}
	
}
