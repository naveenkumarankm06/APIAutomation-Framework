package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserLaunch {

	public static void main(String[] args) {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		
		WebDriver driver = WebDriverManager.chromedriver().capabilities(options).create();
		//WebDriver driver = WebDriverManager.chromedriver().create();
		
		driver.get("https://www.w3schools.com/python/python_syntax.asp");
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());
		driver.quit();
	}

}
