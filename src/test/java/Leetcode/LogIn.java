package Leetcode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;
@Listeners(Listener.ListnerTestng.class)
public class LogIn {
	public static WebDriver driver;
	@Parameters({ "Browser" })
	@BeforeTest
	public void browserStartup(String Browser) {
		if (Browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (Browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (Browser.equalsIgnoreCase("fireFox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
			System.out.println("Wrong browser name");
		}
		driver.manage().window().maximize();
		driver.get("https://leetcode.com/");
	}
	@Test(priority = 1)
	public void HomePage() throws InterruptedException {
		String currentTitle = driver.getTitle();
		String actualTitle = "LeetCode - The World's Leading Online Programming Learning Platform";
		Assert.assertEquals(actualTitle, currentTitle);
		SoftAssert sc = new SoftAssert();
		WebElement Signbtn = driver.findElement(By.xpath("//span[text()='Sign in']"));
		sc.assertTrue(Signbtn.isDisplayed());
		Signbtn.click();
		sc.assertAll();
		Thread.sleep(6000);
	}
	@Parameters({ "user" })
	@Test(priority = 2, enabled = true, description = "Username test case")
	public void verifyUsername(String user) {
		String url = driver.getCurrentUrl();
		String actualurl = "https://leetcode.com/accounts/login/";
		Assert.assertEquals(actualurl, url);
		SoftAssert sc = new SoftAssert();
		WebElement username = driver.findElement(By.name("login"));
		sc.assertTrue(username.isDisplayed());
		username.sendKeys(user);
		sc.assertAll();
	}
	@Parameters({ "password" })
	@Test(priority = 3, dependsOnMethods = "verifyUsername")
	public void verifyPassword(String password) {
		SoftAssert sc = new SoftAssert();
		WebElement pass = driver.findElement(By.name("password"));
		sc.assertTrue(pass.isDisplayed());
		pass.sendKeys(password);
		sc.assertAll();
	}
	@Test(priority = 4, dependsOnMethods = "verifyPassword")
	public void SignIn() throws InterruptedException {
		WebElement Signbtn = driver
				.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div[2]/div/div/div/button/div"));
		Assert.assertTrue(Signbtn.isDisplayed());
		Signbtn.click();
		Thread.sleep(5000);
	}
	@Test(priority = 5, dependsOnMethods = "SignIn", invocationCount = 1, groups = "sanity testing")
	public void getsingpageTitle() {
		String actualTitle = "(2) LeetCode - The World's Leading Online Programming Learning Platform";
		String ExpectedTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, ExpectedTitle);
	}
	@AfterTest(alwaysRun = true,enabled = true)
	public void tearUp() throws InterruptedException {
		Thread.sleep(3000);
		driver.close();
	}
}
