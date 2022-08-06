package TestNg;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.crm.comcast.genericUtilities.ExcelUtility;
import com.crm.comcast.genericUtilities.FileUtility;
import com.crm.comcast.genericUtilities.IConstants;
import com.crm.comcast.genericUtilities.JavaUtility;
import com.crm.comcast.genericUtilities.WebDriverUtility;
import com.crm.vtiger.objectRepository.CreateNewInvoice;
import com.crm.vtiger.objectRepository.NewOrganisationPage;
import com.crm.vtiger.objectRepository.HomePage;
import com.crm.vtiger.objectRepository.InvoicePage;
import com.crm.vtiger.objectRepository.LoginPage;
import com.crm.vtiger.objectRepository.OrganisationSearch;
import com.crm.vtiger.objectRepository.ProductSearch;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateInvoiceWithsalesOrderAndOrgNameTest {
	@ Test(groups="SmokeTest")
	public void  main() throws IOException {
		JavaUtility jLib=new JavaUtility();
		ExcelUtility eLib=new ExcelUtility();
		FileUtility fLib=new FileUtility();
		WebDriverUtility wLib=new WebDriverUtility();
		
		//Fetch the common data from property file
		
		String BROWSER=fLib.getPropertyKeyValue("browser");
		String URL=fLib.getPropertyKeyValue("url");
		String USERNAME=fLib.getPropertyKeyValue("username");
		String PASSWORD=fLib.getPropertyKeyValue("password");
		
		
		//create object and specify path of property file
	//	FileInputStream fis=new FileInputStream(IConstants.filePath);
		//create object for property file
	//	Properties properties = new Properties();
	//	properties.load(fis);
//		String URL = properties.getProperty("url");
//		String USERNAME = properties.getProperty("username");
//		String PASSWORD = properties.getProperty("password");
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		//maximize bfrowser
		wLib.maximizeTheBrowser(driver);
//		driver.manage().window().maximize();
		//enter the url of the app
//		driver.get(URL);
		wLib.navigateApp(driver, URL);
		//implicitly wait for 10sec
		wLib.waitTillPageLoad(driver);
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//randomnum
//		Random random = new Random();
//		int randomNum = random.nextInt();
		int randomNum = jLib.getRandomNum();
		//un,pw and click' by pom
		LoginPage loginPage = new LoginPage(driver);
		HomePage homePage=new HomePage(driver);
		InvoicePage invoicePage=new InvoicePage(driver);
		CreateNewInvoice createNewInvoice= new CreateNewInvoice(driver);
		OrganisationSearch organisationSearch=new OrganisationSearch(driver);
		ProductSearch productSearch=new ProductSearch(driver);
		loginPage.loginToApp(USERNAME, PASSWORD);
		
		//create object and specify path for excelsheet
//    	FileInputStream fis1=new FileInputStream(IConstants.excelPath);
//		//read the data from excelsheet
//		Workbook workbook = WorkbookFactory.create(fis1);
//		Sheet sheet = workbook.getSheet("invoice");
//		Row row = sheet.getRow(1);
//		Cell cell = row.getCell(2);
//		String value = cell.getStringCellValue();
//		Sheet sheet1 = workbook.getSheet("address");
//		Row row1 = sheet1.getRow(1);
//		Cell cell1 = row1.getCell(3);
//		String value1 = cell1.getStringCellValue();
//		Cell cell2 = row1.getCell(3);
//		String value2 = cell2.getStringCellValue();
//		
//		
		String value = eLib.getDataFromExcel("invoice", 1, 2);
		String value1 = eLib.getDataFromExcel("invoice", 1, 3);
		String value2 = eLib.getDataFromExcel("invoice", 1, 3);
		
		String InvoiceSub = value+randomNum;
		//inspect dropdown
//		WebElement drpdwn = driver.findElement(By.xpath("//a[text()='More']"));
//		
//		Actions a=new Actions(driver);
//		a.moveToElement(drpdwn).perform();
//		//click on invoice
//		driver.findElement(By.xpath("//a[@name='Invoice']")).click();
		homePage.invoiceLink(wLib, driver);
		//click on create invoice lookup icon
//		driver.findElement(By.xpath("//img[@alt='Create Invoice...']")).click();
		invoicePage.clickOnInvoice();
		//insert name in subject text fieeld
//		driver.findElement(By.xpath("//input[@name='subject']")).sendKeys(value);
		createNewInvoice.invoiceSubject("invoicesub");
		//click sales order lookup icon
		driver.findElement(By.xpath("(//img[@align='absmiddle'])[3]")).click();
		//save parent browser
		String parentid = driver.getWindowHandle();
		//sale all id
		wLib.switchToWindow("SalesOrder&action", driver);
//		Set<String> allid = driver.getWindowHandles();
//		for(String string:allid)
//		{
//			driver.switchTo().window(string);
//			String title = driver.getTitle();
//			if(title.contains("SalesOrder&action"))
//			{
//				break;
//			}
//		}
		//select sales order
		driver.findElement(By.xpath("(//a[text()='SalesOrder'])[3]")).click();
		//move to parent id
		driver.switchTo().window(parentid);
		//click on organization name lookup icon
//		driver.findElement(By.xpath("(//img[@style='cursor:hand;cursor:pointer'])[3]")).click();
		createNewInvoice.orgName();
		wLib.switchToWindow("Accounts&action", driver);
//		Set<String> allid1 = driver.getWindowHandles();
//		for(String string1:allid1)
//		{
//			driver.switchTo().window(string1);
//			String title1 = driver.getTitle();
//			if(title1.contains("Accounts&action"))
//			{
//				break;
//			}
//		}
		//select organization
//    	driver.findElement(By.linkText("organisation2")).click();
		organisationSearch.organisation();
		
		//handle alert popup
		//wLib.switchToAlertPopupandAccept(driver, parentid);
//		Alert popup = driver.switchTo().alert();
//		popup.accept();
		organisationSearch.alertOrg(wLib, driver);
		//switch to parent browser
		driver.switchTo().window(parentid);
//		driver.findElement(By.name("bill_street")).sendKeys(value1);
		createNewInvoice.invoiceBillingAd("delhi");
//		driver.findElement(By.name("ship_street")).sendKeys(value2);
		createNewInvoice.invoiceShippingAd("mumbai");
		//click on products lookup icon
//		driver.findElement(By.id("searchIcon1")).click();
		createNewInvoice.product();
		wLib.switchToWindow("Products&action", driver);
//		Set<String> allid2 = driver.getWindowHandles();
//		for(String string2:allid2)
//		{
//			driver.switchTo().window(string2);
//			String title2 = driver.getTitle();
//			if(title2.contains("Products&action"))
//				break;
//		}
//		driver.findElement(By.linkText("mobile")).click();
		productSearch.prodSearch();
		driver.switchTo().window(parentid);
		
//		driver.findElement(By.id("qty1")).sendKeys("10");
		createNewInvoice.qty("5");
//		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		createNewInvoice.save();
		String title2 = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		if(title2.contains("invoice"))
		{
			System.out.println("invoice page created");
		}
		else
		{
			System.out.println("invoice page not created");
		}
		
		
		
		
	}

}
