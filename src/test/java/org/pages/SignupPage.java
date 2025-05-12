package org.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
	private WebDriver driver;
	@FindBy(xpath="//*[text()=' Sign Up']") WebElement signupOption;
	@FindBy(id="usernameSignUp") WebElement userNameSignUpField;
	@FindBy(id="emailSignUp") WebElement emailSignUpField;
	@FindBy(id="passwordSignUp") WebElement passwordSignUpField;
	@FindBy(id="signUp") WebElement signUpButton;
	@FindBy(id="closeSignUp") WebElement closeButton;
	@FindBy(id="successS") WebElement successSignUpMessage;
	
	
	public SignupPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void enterUserName(String userName){
		userNameSignUpField.sendKeys(userName);
		
	}
	public void enterEmailAddress(String emailAddress){
		emailSignUpField.sendKeys(emailAddress);
		
	}
	public void enterPassword(String password){
		passwordSignUpField.sendKeys(password);
		
	}
	public void clickSignUpButton() {
		signUpButton.click();
	}
	public void clickSignUpOption() {
		signupOption.click();
	}
	public void clickCloseButton() {
		closeButton.click();
	}
	public String    getTextofSuccessSignUpMessage() {
	 return successSignUpMessage.getText();
		
	}
	
	
	public String  signUp(String userName, String emailAddress, String password) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		clickSignUpOption();
		enterUserName(userName);
		enterEmailAddress(emailAddress);
		enterPassword(password);
		clickSignUpButton();
		String message=getTextofSuccessSignUpMessage();
		closeButton.click();
		return message;
	}

}
