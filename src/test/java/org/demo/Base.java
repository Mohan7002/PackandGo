package org.demo;


import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;




public class Base {
	 protected WebDriver driver;
	
	 
	 
	
	public  WebDriver setUpEdge(String url) {
		System.setProperty("webdriver.edge.driver","C:\\Users\\mohankumar.p06\\Copilot\\Edgedriver\\msedgedriver.exe");
     driver = new EdgeDriver();
     driver.get(url);
     driver.manage().window().maximize();
     return driver;
	}

	public  void tearDownBrowser() {
		driver.close();
	}
}
