package org.tests;

import org.demo.Base;
import org.pages.BookingPage;
import org.pages.DashboardPage;
import org.pages.LoginPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BookingTest extends Base {
	String User_Name;
	String Pass_Word;

	@Parameters({"User","Pass","Url"})
	@BeforeClass
	public void setUp(String user,String pass,String url ){
		User_Name=user;
		Pass_Word=pass;
		driver = setUpEdge(url);
	}
	@AfterClass
	public void tearDown(){
		tearDownBrowser();
	}
	@Test 
	public void booking_Verify() {
		LoginPage lPage = new LoginPage(driver);
		lPage.login(User_Name, Pass_Word);
		DashboardPage dPage= new DashboardPage(driver);
		dPage.login_Verify();
		dPage.booking_Inputs();
		BookingPage bPage= new BookingPage(driver);
		bPage.booking("1");
		dPage.booking_History_Verify("Bengaluru", "Hyderabad", "BNGHYD2100", "1200");
		dPage.logout();
	}
}
