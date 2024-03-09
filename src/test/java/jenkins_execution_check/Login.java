package jenkins_execution_check;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Login extends TestBase {

	public Login() throws Exception {
		super();
	}
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndLoadApplication("Chrome");
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Login")).click();
		
	}
	@Test (priority=1)
	public void verifyLoginwithValidCredintails() {
		
		
		driver.findElement(By.xpath("//input[@name ='email' or @id = 'input-email']")).sendKeys("yasso12345@gmail.com");
		driver.findElement(By.xpath("//input[starts-with(@id,'input-passw')]")).sendKeys("123456@");
		driver.findElement(By.xpath("//input[@class = 'btn btn-primary']")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
		driver.quit();
	}
	
	@Test (priority=2)
	public void verifyLoginwithinValidCredintails() {
		
	
		driver.findElement(By.xpath("//input[@name ='email' or @id = 'input-email']")).sendKeys("yasso123456@gmail.com");
		driver.findElement(By.xpath("//input[starts-with(@id,'input-passw')]")).sendKeys("1234567@");
		driver.findElement(By.xpath("//input[@class = 'btn btn-primary']")).click();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		driver.quit();
	}
	@Test (priority=3)
	public void verifyLoginWithValidEmailValidPassword() {
	
		driver.findElement(By.xpath("//input[@name ='email' or @id = 'input-email']")).sendKeys("yasso12345@gmail.com");
		driver.findElement(By.xpath("//input[starts-with(@id,'input-passw')]")).sendKeys("1234567@");
		driver.findElement(By.xpath("//input[@class = 'btn btn-primary']")).click();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		driver.quit();
	}
	
	@Test (priority=4)
	public void verifyLoginWithInvalidEmailValidPassword() {
		
	
		driver.findElement(By.xpath("//input[@name ='email' or @id = 'input-email']")).sendKeys("yasso123456@gmail.com");
		driver.findElement(By.xpath("//input[starts-with(@id,'input-passw')]")).sendKeys("123456@");
		driver.findElement(By.xpath("//input[@class = 'btn btn-primary']")).click();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		// driver.quit();
	}
	
	
	@Test (priority=5)
	public void verifyLoginwithNocredintails() {
		

		driver.findElement(By.xpath("//input[@class = 'btn btn-primary']")).click();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		driver.quit();
	}
	
	@AfterMethod
	public void tearDown() {
		//driver.quit();
	}
	
	
}
