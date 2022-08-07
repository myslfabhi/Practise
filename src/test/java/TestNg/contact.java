package TestNg;

import java.io.IOException;
import java.time.Duration;
import java.util.Base64;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.crm.comcast.genericUtilities.ExcelUtility;
import com.crm.comcast.genericUtilities.FileUtility;
import com.crm.comcast.genericUtilities.JavaUtility;
import com.crm.comcast.genericUtilities.WebDriverUtility;
import com.crm.vtiger.objectRepository.CreateContactsPage;
import com.crm.vtiger.objectRepository.HomePage;
import com.crm.vtiger.objectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class contact extends Base64 {
	@Test(groups="SmokeTest")//not done//
	public void main() throws IOException {
		JavaUtility jLib=new JavaUtility();
		ExcelUtility eLib=new ExcelUtility();
		FileUtility fLib=new FileUtility();
		WebDriverUtility wLib=new WebDriverUtility();
		
		//Fetch the common data from property file
		
		String BROWSER=fLib.getPropertyKeyValue("browser");
		String URL=fLib.getPropertyKeyValue("url");
		String USERNAME=fLib.getPropertyKeyValue("username");
		String PASSWORD=fLib.getPropertyKeyValue("password");//
		
	//setting up browser
	WebDriverManager.chromedriver().setup();
	//creating object for browser
	WebDriver driver = new ChromeDriver();
	LoginPage loginPage=new LoginPage(driver);
	HomePage homePage=new HomePage(driver);
	CreateContactsPage createContactsPage=new CreateContactsPage(driver);
	//maximizing the browser
//	driver.manage().window().maximize();
	wLib.maximizeTheBrowser(driver);
	//passing the url
//	driver.get("http://localhost:8888/");
	wLib.navigateApp(driver, URL);
	//applying implicitly wait 
//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	wLib.waitTillPageLoad(driver);
	//giving login details and clicking on login
//	driver.findElement(By.xpath("//input[@type='text']")).sendKeys("admin");
//	driver.findElement(By.xpath("//input[@type='password']")).sendKeys("admin");
//	driver.findElement(By.id("submitButton")).click();
	loginPage.loginToApp(USERNAME, PASSWORD);
	//click on contact module
//	driver.findElement(By.xpath("(//a[text()='Contacts'])[2]")).click();
	homePage.contactsLink();
	//click on create contact lookup icon
//	driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
	createContactsPage.clickContactLkp();
}

	
	
}