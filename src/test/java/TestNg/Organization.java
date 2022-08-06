package TestNg;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.crm.comcast.genericUtilities.ExcelUtility;
import com.crm.comcast.genericUtilities.FileUtility;
import com.crm.comcast.genericUtilities.JavaUtility;
import com.crm.comcast.genericUtilities.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Organization {
@Test
	public void main() throws IOException {
		JavaUtility jLib=new JavaUtility();
		ExcelUtility eLib=new ExcelUtility();
		FileUtility fLib=new FileUtility();
		WebDriverUtility wLib=new WebDriverUtility();
		
		//Fetch the common data from property file
		
		String BROWSER=fLib.getPropertyKeyValue("browser");
		String URL=fLib.getPropertyKeyValue("url");
		String USERNAME=fLib.getPropertyKeyValue("username");
		String PASSWORD=fLib.getPropertyKeyValue("password");
		
		Properties properties=new Properties();
		
		FileInputStream fis=new FileInputStream("./src/test/resources/commondata.properties");
		properties.load(fis);
		
//		String URL = properties.getProperty("url");
//		String USERNAME = properties.getProperty("username");
//		String PASSWORD = properties.getProperty("password");
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(URL);
		System.out.println("url entered");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		WebElement ele = driver.findElement(By.xpath("//input[@name='user_name']"));
//		ele.clear();
//		ele.sendKeys(USERNAME);
//		WebElement ele1 = driver.findElement(By.name("user_password"));
//		ele1.clear();
//		ele1.sendKeys(PASSWORD);
		
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(properties.getProperty("username"));
		System.out.println("username entered");
		driver.findElement(By.name("user_password")).sendKeys(properties.getProperty("password"));
		System.out.println("pasword entered");
		driver.findElement(By.id("submitButton")).submit();
		System.out.println("submit button clicked");
		
		
		FileInputStream fis1=new FileInputStream(".\\src\\test\\resources\\Sdet37Testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
	//	wb.getSheet(USERNAME)
		
		
		
	}

}

