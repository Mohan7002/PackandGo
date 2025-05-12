package org.tests;

import org.demo.Base;
import org.pages.DashboardPage;
import org.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class EditProfileTest extends Base {
	String User_Name;
	String Pass_Word;
	String language_Name;
	String Mobile_Number;
	@Parameters({"User","Pass","Url","Language","MobileNumber"})
	@BeforeClass
	public void setUp(String user,String pass,String url,String language,String mobile ){
		User_Name=user;
		Pass_Word=pass;
		language_Name=language;
		Mobile_Number=mobile;
		driver = setUpEdge(url);
	}
	@AfterClass
	public void tearDown(){
		tearDownBrowser();
	}
	@Test
	public void editProfile_Test() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(User_Name, Pass_Word);
		DashboardPage dashboardPage = new DashboardPage(driver);
		dashboardPage.editProfile(language_Name, Mobile_Number);
		Assert.assertTrue(dashboardPage.isEditFreezed(),"Able to edit the fields");
		dashboardPage.logout();
	}
}
