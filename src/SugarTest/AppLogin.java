package SugarTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AppLogin 
{
	public static WebDriver login() throws Exception
	{
		System.setProperty("webdriver.chrome.driver","lib/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    driver.get("http://localhost/SugarPro-Full-7.7.1.2");
	    driver.manage().window().maximize();
	    
	    WebElement userNameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement submitButton = driver.findElement(By.name("login_button"));
        userNameField.sendKeys("regularuser1");
        passwordField.sendKeys("Admin@99");
        submitButton.click();
        
        WebElement welcomeButton = driver.findElement(By.className("done-btn"));
        welcomeButton.click();
        
        TimeUnit.SECONDS.sleep(2);
        return driver;
	}
}
