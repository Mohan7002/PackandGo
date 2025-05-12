package org.tests;

import org.demo.Base;
import org.pages.DashboardPage;
import org.pages.LoginPage;
import org.pages.SignupPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class SignupTest extends Base {
	String User_Name="Ram";
	String Pass_Word="Ram@123";
	String Email_Address="Ram@123.com";
@Parameters({"Url"})	
@BeforeClass
public void setUp(String url) {
	driver= setUpEdge(url);
}
@AfterClass
public void tearDown() {
	tearDownBrowser();
}
@Parameters({"User","Email","Pass"})
@Test
public void signup_Test(/*String UName, String email, String password*/) throws InterruptedException {
	SignupPage singnupPage = new SignupPage(driver);
	singnupPage.signUp(User_Name, Email_Address, Pass_Word);
	Assert .assertEquals(singnupPage.signUp(User_Name, Email_Address, Pass_Word), "Congratulations ! You have been registered successfully.", "Unable to signup");
	System.out.println("Signed Up Successfully");
	
}
}
