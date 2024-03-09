package jenkins_execution_check;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Register extends TestBase {

	public Register() throws Exception {
	
		super();
	}
	
	public WebDriver driver;
	public SoftAssert softassert;
	
	@BeforeMethod
	public void register() {
		
		driver = initializeBrowserAndLoadApplication("Chrome");
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
	}
	
	@Test(priority=1)
	public void registerWithExisitingEmail() {
		
		driver.findElement(By.cssSelector("input#input-firstname")).sendKeys("Yasso");
		driver.findElement(By.cssSelector("input#input-lastname")).sendKeys("Megosaged");
		driver.findElement(By.cssSelector("input#input-email")).sendKeys("sago1234@gmail.com");
		driver.findElement(By.cssSelector("input#input-telephone")).sendKeys("9876543210");
		driver.findElement(By.cssSelector("input#input-password")).sendKeys("Yasso123@");
		driver.findElement(By.cssSelector("input#input-confirm")).sendKeys("Yasso123@");
		driver.findElement(By.cssSelector("div.col-sm-10>label:nth-child(1)>input[name=newsletter]")).click();
		driver.findElement(By.cssSelector("a.agree+input")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String expectedErrorMessage ="Warning: E-Mail Address is already registered!";
		String ActualErrorMessage= driver.findElement(By.cssSelector("div.alert.alert-danger.alert-dismissible")).getText();
		Assert.assertEquals(expectedErrorMessage, ActualErrorMessage);
		
		

	}
	
	@Test(priority=2)
	public void registerWithMandatoryfields() throws Exception {
		driver.findElement(By.cssSelector("input#input-firstname")).sendKeys("Yassom");
		driver.findElement(By.cssSelector("input#input-lastname")).sendKeys("Megosagedd");
		driver.findElement(By.cssSelector("input#input-email")).sendKeys("ssayop@gmail.com");
		driver.findElement(By.cssSelector("input#input-telephone")).sendKeys("9876543210");
		driver.findElement(By.cssSelector("input#input-password")).sendKeys("Yasso123@");
		driver.findElement(By.cssSelector("input#input-confirm")).sendKeys("Yasso123@");
		driver.findElement(By.cssSelector("div.col-sm-10>label:nth-child(1)>input[name=newsletter]")).click();
		driver.findElement(By.cssSelector("a.agree+input")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.linkText("Continue")).isDisplayed());
		
		
	}
	
	@Test(priority=3)
	public void registerWithrequiredfields() {
		//passed
		driver.findElement(By.cssSelector("input#input-firstname")).sendKeys("Yassom");
		driver.findElement(By.cssSelector("input#input-lastname")).sendKeys("Megosagedd");
		driver.findElement(By.cssSelector("input#input-email")).sendKeys("sayyo1245@gmail.com");
		driver.findElement(By.cssSelector("input#input-telephone")).sendKeys("9876543210");
		driver.findElement(By.cssSelector("input#input-password")).sendKeys("Yasso123@");
		driver.findElement(By.cssSelector("input#input-confirm")).sendKeys("Yasso123@");
		driver.findElement(By.cssSelector("a.agree+input")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String expectedErrorMessage ="Your Account Has Been Created!";
		String ActualErrorMessage= driver.findElement(By.xpath("//div[@id= 'content']/child::h1")).getText();
		Assert.assertTrue(expectedErrorMessage.contains(ActualErrorMessage));
	
	}
	@Test(priority=4)
	public void verifyRegisterWithExistingEmail() {
		driver.findElement(By.cssSelector("input#input-firstname")).sendKeys("Selenium");
		driver.findElement(By.cssSelector("input#input-lastname")).sendKeys("Panda");
		driver.findElement(By.cssSelector("input#input-email")).sendKeys("seleniumpanda4@gmail.com");
		driver.findElement(By.cssSelector("input#input-telephone")).sendKeys("9876543210");
		driver.findElement(By.cssSelector("input#input-password")).sendKeys("Selenium@123");
		driver.findElement(By.cssSelector("input#input-confirm")).sendKeys("Selenium@123");
		driver.findElement(By.xpath("//input[@name = 'newsletter' and @value='1']")).click();
		driver.findElement(By.cssSelector("a.agree+input")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		String actualExistingEmailWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedExistingEmailWarningMessage = "Warning: E-Mail Address is already registered!";
		Assert.assertTrue(actualExistingEmailWarningMessage.contains(expectedExistingEmailWarningMessage));
		
	}
	
	@Test(priority=5)
	public void verifyRegisterWithWrongConfirmPassword() {
		driver.findElement(By.cssSelector("input#input-firstname")).sendKeys("Selenium");
		driver.findElement(By.cssSelector("input#input-lastname")).sendKeys("Panda");
		driver.findElement(By.cssSelector("input#input-email")).sendKeys("seleniumpanda1122113@gmail.com");
		driver.findElement(By.cssSelector("input#input-telephone")).sendKeys("9876543210");
		driver.findElement(By.cssSelector("input#input-password")).sendKeys("Selenium@123");
		driver.findElement(By.cssSelector("input#input-confirm")).sendKeys("Selenium@123456");
		driver.findElement(By.xpath("//input[@name = 'newsletter' and @value='1']")).click();
		driver.findElement(By.cssSelector("a.agree+input")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("input#input-confirm+div")).isDisplayed());
		
	}
	
	@AfterMethod
	public void closeBrowser() {
		
		driver.quit();
	}
}


