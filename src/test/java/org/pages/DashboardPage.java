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
	/// -------------------------Dashboard Locators--------------------------
	@FindBy(xpath = "//div[text()='My Dashboard']")
	WebElement myDashbordText;
	@FindBy(xpath = "//*[text()=' LogOut']")
	WebElement logoutLink;

	/// -------------------------New Booking Locators--------------------------
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
	@FindBy(id = "refresh")
	WebElement refreshButton;
	@FindBy(xpath = "//table[@id='bookingHistoryTable']/tr/td")
	List<WebElement> bookingHistoryTableDatas;

	/// -------------------------Edit Profile Locators--------------------------
	@FindBy(xpath = "//*[text()=' Edit Profile']")
	WebElement editProfileLink;
	@FindBy(id = "editAccount")
	WebElement enableEditingButton;
	@FindBy(id = "genderM")
	WebElement maleRadioButton;
	@FindBy(id = "genderF")
	WebElement femaleRadioButton;
	@FindBy(id = "sel1")
	WebElement languageDropdown;
	@FindBy(id = "contactNum")
	WebElement mobileNumberField;
	@FindBy(id = "files")
	WebElement fileUpload;
	@FindBy(id = "clearAccount")
	WebElement resetAllButton;
	
	public DashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void login_Verify() {
		String expected = "My Dashboard";
		String dashboardText = myDashbordText.getText();
		Assert.assertEquals(dashboardText, expected, "Unable to login");

		System.out.println("Logged in");

	}

	public void logout() {
		logoutLink.click();
	}

	public void booking_Inputs() {
		Select fromSelect = new Select(fromDropDown);
		Select toSelect = new Select(toDropDown);
		fromSelect.selectByIndex(1);
		toSelect.selectByVisibleText("Hyderabad");
		searchBusButton.click();
		busSelectionRadioButton.click();
		proceedtoBookingButton.click();
	}

	public void booking_History_Verify(String source, String dest, String busName, String Fare) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		bookingHistoryLink.click();
		refreshButton.click();
		boolean bookingStatus = false;
		for (int i = 0; i <= bookingHistoryTableDatas.size() - 4; i += 4) {
			String dataSource = bookingHistoryTableDatas.get(i).getText();
			String dataDest = bookingHistoryTableDatas.get(i + 1).getText();
			String dataBusName = bookingHistoryTableDatas.get(i + 2).getText();
			String dataFare = bookingHistoryTableDatas.get(i + 3).getText();
			if (dataSource.equalsIgnoreCase(source) && dataDest.equalsIgnoreCase(dest)
					&& dataBusName.equalsIgnoreCase(dataBusName) && dataFare.equalsIgnoreCase(Fare)) {
				bookingStatus = true;
				System.out.println("Booking Details Available");

			}

		}
		SoftAssert s = new SoftAssert();
		s.assertTrue(bookingStatus);

		s.assertAll();

	}

	public void clickEditProfile() {
		editProfileLink.click();
	}

	public void clickEnableEditing() {
		enableEditingButton.click();
	}

	public void clickMaleRadioButton() {
		maleRadioButton.click();
	}

	public boolean isMaleRadioButtonEnabled() {
		return maleRadioButton.isEnabled();
	}

	public void clickFemaleRadioButton() {
		femaleRadioButton.click();
	}

	public boolean isFemaleRadioButtonEnabled() {
		return femaleRadioButton.isEnabled();
	}

	public void languageSelectionbyVisibleText(String language) {
		Select launguageSelect = new Select(languageDropdown);
		launguageSelect.selectByVisibleText(language);

	}

	public void languageSelectionbyIndex(int index) {
		Select launguageSelect = new Select(languageDropdown);
		launguageSelect.selectByIndex(index);

	}

	public boolean isLanguageDropDownEnable() {
		return languageDropdown.isEnabled();
	}

	public void sendMobileNumber(String number) {
		mobileNumberField.sendKeys(number);
	}

	public boolean isMobileNumberFieldEnable() {
		return mobileNumberField.isEnabled();
	}

	public boolean isFileUploadEnable() {
		return fileUpload.isEnabled();
	}
	public boolean isResetAllButtonEnable() {
		return resetAllButton.isEnabled();
	}

	public void editProfile(String language, String number) {
		clickEditProfile();
		clickEnableEditing();
		clickMaleRadioButton();
		languageSelectionbyVisibleText(language);
		sendMobileNumber(number);
		clickEnableEditing();
	}

	public boolean isEditFreezed() {
		if ((isMaleRadioButtonEnabled() == false) && (isFemaleRadioButtonEnabled() == false)
				&& (isLanguageDropDownEnable() == false) && (isMobileNumberFieldEnable() == false)
				&& (isFileUploadEnable() == false)&& (isResetAllButtonEnable()==false)) {
			System.out.println("Editing is freezed");
			
		}
		return true;
	}
}
