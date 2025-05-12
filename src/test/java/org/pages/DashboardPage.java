package org.pages;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class DashboardPage {
	private WebDriver driver;

	@FindBy(xpath = "//div[text()='My Dashboard']")
	WebElement myDashbordText;
	@FindBy(xpath = "//*[text()=' LogOut']")
	WebElement logoutLink;
	@FindBy(id = "fromDD")
	WebElement fromDropDown;
	@FindBy(id = "toDD")
	WebElement toDropDown;
	@FindBy(id = "searchBus")
	WebElement searchBusButton;
	@FindBy(id = "radio2")
	WebElement busSelectionRadioButton;
	@FindBy(id = "book")
	WebElement proceedtoBookingButton;
	@FindBy(xpath = "//*[text()=' Booking History']")
	WebElement bookingHistoryLink;
	@FindBy(id ="refresh")
	WebElement refreshButton;
	@FindBy(xpath ="//table[@id='bookingHistoryTable']/tr/td")
	List<WebElement> bookingHistoryTableDatas;
 

	public DashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void login_Verify(){
		String expected="My Dashboard";
		String dashboardText=myDashbordText.getText();
		Assert.assertEquals(dashboardText, expected, "Unable to login");
		
		System.out.println("Logged in");
		
	}
	public void logout() {
		logoutLink.click();
	}
	public void booking_Inputs(){
		Select fromSelect= new Select(fromDropDown);
		Select toSelect= new Select(toDropDown);
		fromSelect.selectByIndex(1);
		toSelect.selectByVisibleText("Hyderabad");
		searchBusButton.click();
		busSelectionRadioButton.click();
		proceedtoBookingButton.click();
	}
	
	public void booking_History_Verify(String source,String dest,String busName,String Fare){
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		bookingHistoryLink.click();
		refreshButton.click();
		boolean bookingStatus=false;
		for(int i=0;i<=bookingHistoryTableDatas.size()-4;i+=4) {
			String dataSource=bookingHistoryTableDatas.get(i).getText();
			String dataDest=bookingHistoryTableDatas.get(i+1).getText();
			String dataBusName=bookingHistoryTableDatas.get(i+2).getText();
			String dataFare=bookingHistoryTableDatas.get(i+3).getText();
			if(dataSource.equalsIgnoreCase(source) && dataDest.equalsIgnoreCase(dest) &&
					dataBusName.equalsIgnoreCase(dataBusName) && dataFare.equalsIgnoreCase(Fare)) {
				bookingStatus=true;
				System.out.println("Booking Details Available");
				
			}
				
		}
		SoftAssert s = new SoftAssert();
		s.assertTrue(bookingStatus);
		
		s.assertAll();
		
	}
}
