package org.pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookingPage {
	
	private WebDriver driver;

	@FindBy(id = "counter")
	WebElement noOfPassengersField;
	@FindBy(xpath = "//input[@class='w3-btn w3-blue']")
	WebElement calculateTotalBillButton;
	@FindBy(id = "tFare")
	WebElement fareText;
	@FindBy(id = "confirmBooking")
	WebElement confirmBookingButton;
public BookingPage(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver, this);
	
	
}
public void booking(String noOfPassengers) {
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	
	noOfPassengersField.clear();
	noOfPassengersField.sendKeys(noOfPassengers);
	calculateTotalBillButton.click();
	System.out.println("Total Fare : "+fareText.getText());
	confirmBookingButton.click();
	Alert alert = driver.switchTo().alert();
	System.out.println(alert.getText());
	alert.accept();
	
	
}
	
}
