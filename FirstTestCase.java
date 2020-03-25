package automationFramework;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.JavascriptExecutor;


public class FirstTestCase {
	
	static WebDriver driver;
	static JavascriptExecutor js;

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "/opt/chromedriver");
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--headless");
		driver=new ChromeDriver();
		//casting an object
		js=(JavascriptExecutor)driver;
		
		login();
		
	}
	
	public static void login() {
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://localhost:5001");
		String login=driver.findElement(By.tagName("h2")).getText();
		
		if(login.equals("Login Page"))
		{
			System.out.println("Connected to home page.");
		}
		else
		{
			System.out.println("Problem with login title test.");
		}
		
		driver.findElement(By.name("username")).sendKeys("test");
		driver.findElement(By.name("password")).sendKeys("password");
		driver.findElement(By.id("submit")).click();
		
		String welcomepage=driver.findElement(By.tagName("h1")).getText();
		
		if(welcomepage.equals("Welcome"))
		{
			System.out.println("Connected to welcome page.");
		}
		else
		{
			System.out.println("Problem with welcome page test");
		}
		
		companies();
		
	}
	
	public static void companies() {
		
		driver.findElement(By.id("addCompany")).click();
		String title=driver.findElement(By.tagName("h1")).getText();
		if(title.equals("Index"))
		{
			System.out.println("Connected to companies page.");
		}
		else
		{
			System.out.println("Unable to connect to companies page.");
		}
		
		createCom();
		
	}
	
	public static void createCom() {
		
		driver.findElement(By.linkText("Add Company")).click();
		String title=driver.findElement(By.tagName("h1")).getText();
		if(title.equals("Create"))
		{
			System.out.println("Connected to create company page.");
		}
		else
		{
			System.out.println("Unable to reach create company page.");
		}
		
		driver.findElement(By.id("CompanyName")).sendKeys("Test Name");
		driver.findElement(By.id("PublicEmail")).sendKeys("info@testname.com");
		driver.findElement(By.id("ContactPersonOne")).sendKeys("Test Person One");
		driver.findElement(By.id("PersonalEmailOne")).sendKeys("personone@test.com");
		driver.findElement(By.id("ContactPersonTwo")).sendKeys("Test Person Two");
		driver.findElement(By.id("PersonalEmailTwo")).sendKeys("persontwo@test.com");
		driver.findElement(By.id("DateCreated")).sendKeys("03/23/2020");
		driver.findElement(By.id("GroupName")).sendKeys("03/23");
		driver.findElement(By.id("Comments")).sendKeys("Test comment.");
		driver.findElement(By.id("AccountManager")).sendKeys("Ben Clark");
		driver.findElement(By.id("Submit")).click();		
		
		
		List<WebElement> collapsibles=driver.findElements(By.className("collapsible"));
		List<String> strCompany=new ArrayList<String>();

		for(WebElement strElement:collapsibles) {
			String company=strElement.getText();
			strCompany.add(company);
		}
		
		if(strCompany.contains("Test Name"))
		{
			System.out.println("Company created.");
		}
		else
		{
			System.out.println("Unable to find company created");
		}
		
		checkCreate();
		
	}
	
	public static void checkCreate() {
		
		List<WebElement> updatedButtons=driver.findElements(By.className("collapsible"));
		//Need to find a way to deal with sibling elements
		for(WebElement companyButton:updatedButtons) {
			String buttonText=companyButton.getText();
			
			if(buttonText.equals("Test Name"))
			{
				companyButton.click();
				System.out.println("Checking accuracy of company details.");
				break;
			}
			else
			{
				continue;
			}
		}
		
		String publicEmail=driver.findElement(By.xpath(
				"//*[@name='UpperTable']//td[contains(text(),'info@testname.com')]")).getText();
		String contactPersonOne=driver.findElement(By.xpath(
				"//*[@name='UpperTable']//td[contains(text(),'Test Person One')]")).getText();
		String personalEmailOne=driver.findElement(By.xpath(
				"//*[@name='UpperTable']//td[contains(text(),'personone@test.com')]")).getText();
		String contactPersonTwo=driver.findElement(By.xpath(
				"//*[@name='UpperTable']//td[contains(text(),'Test Person Two')]")).getText();
		String personalEmailTwo=driver.findElement(By.xpath(
				"//*[@name='UpperTable']//td[contains(text(),'persontwo@test.com')]")).getText();
		String dateCreated=driver.findElement(By.xpath(
				"//*[@name='LowerTable']//td[contains(text(),'3/23/2020')]")).getText();
		String groupName=driver.findElement(By.xpath(
				"//*[@name='LowerTable']//td[contains(text(),'03/23')]")).getText();
		String comments=driver.findElement(By.xpath(
				"//*[@name='LowerTable']//td[contains(text(),'Test comment.')]")).getText();
		String accountManager=driver.findElement(By.xpath(
				"//*[@name='LowerTable']//td[contains(text(),'Ben Clark')]")).getText();
		
		
		if(publicEmail.equals("info@testname.com") && personalEmailOne.equals("personone@test.com") &&
				contactPersonTwo.equals("Test Person Two") && personalEmailTwo.equals("persontwo@test.com") &&
				dateCreated.equals("3/23/2020") && groupName.equals("03/23") && comments.equals("Test comment.") && 
				contactPersonOne.equals("Test Person One") && accountManager.equals("Ben Clark"))
		{
			System.out.println("Successfully verified accuracy of all entries.");
		}
		else
		{
			System.out.println("Unable to verify accuracy of company create.");

		}
		
		updateCompanies();
		
	}
	
	public static void updateCompanies() {
		
		driver.findElement(By.linkText("Edit")).click();
		
		String title=driver.findElement(By.tagName("h1")).getText();
		
		if(title.contentEquals("Edit"))
		{
			System.out.println("Connected to edit page for Test Company.");
		}
		else
		{
			System.out.println("Unable to connect to edit page for Test Company.");

		}
		
		driver.findElement(By.id("PublicEmail")).clear();
		driver.findElement(By.id("PublicEmail")).sendKeys("update@testname.com");
		driver.findElement(By.id("ContactPersonOne")).clear();
		driver.findElement(By.id("ContactPersonOne")).sendKeys("测试人一");
		driver.findElement(By.id("PersonalEmailOne")).clear();
		driver.findElement(By.id("PersonalEmailOne")).sendKeys("ceshirenyi@test.com");
		driver.findElement(By.id("ContactPersonTwo")).clear();
		driver.findElement(By.id("ContactPersonTwo")).sendKeys("Test Update Person Two");
		driver.findElement(By.id("PersonalEmailTwo")).clear();
		driver.findElement(By.id("PersonalEmailTwo")).sendKeys("persontwo@testupdate.com");
		driver.findElement(By.id("DateCreated")).clear();
		driver.findElement(By.id("DateCreated")).sendKeys("03/28/2020");
		driver.findElement(By.id("GroupName")).clear();
		driver.findElement(By.id("GroupName")).sendKeys("03/28");
		driver.findElement(By.id("Comments")).clear();
		driver.findElement(By.id("Comments")).sendKeys("Update comment.");
		driver.findElement(By.id("AccountManager")).clear();
		driver.findElement(By.id("AccountManager")).sendKeys("Benjamin");
		driver.findElement(By.id("submit")).click();
		
		System.out.println("Updating company information.");
		
		checkUpdate();
		
	}
		
	public static void checkUpdate() {
		
		List<WebElement> updatedButtons=driver.findElements(By.className("collapsible"));
		//Need to find a way to deal with sibling elements
		for(WebElement companyButton:updatedButtons) {
			String buttonText=companyButton.getText();
			
			if(buttonText.equals("Test Name"))
			{
				companyButton.click();
				break;
			}
			else
			{
				continue;
			}
		}
		
		String publicEmail=driver.findElement(By.xpath(
				"//*[@name='UpperTable']//td[contains(text(),'update@testname.com')]")).getText();
		String contactPersonOne=driver.findElement(By.xpath(
				"//*[@name='UpperTable']//td[contains(text(),'测试人一')]")).getText();
		String personalEmailOne=driver.findElement(By.xpath(
				"//*[@name='UpperTable']//td[contains(text(),'ceshirenyi@test.com')]")).getText();
		String contactPersonTwo=driver.findElement(By.xpath(
				"//*[@name='UpperTable']//td[contains(text(),'Test Update Person Two')]")).getText();
		String personalEmailTwo=driver.findElement(By.xpath(
				"//*[@name='UpperTable']//td[contains(text(),'persontwo@testupdate.com')]")).getText();
		String dateCreated=driver.findElement(By.xpath(
				"//*[@name='LowerTable']//td[contains(text(),'3/28/2020')]")).getText();
		String groupName=driver.findElement(By.xpath(
				"//*[@name='LowerTable']//td[contains(text(),'03/28')]")).getText();
		String comments=driver.findElement(By.xpath(
				"//*[@name='LowerTable']//td[contains(text(),'Update comment.')]")).getText();
		String accountManager=driver.findElement(By.xpath(
				"//*[@name='LowerTable']//td[contains(text(),'Benjamin')]")).getText();
		
		
		if(publicEmail.equals("update@testname.com") && personalEmailOne.equals("ceshirenyi@test.com") &&
			contactPersonTwo.equals("Test Update Person Two") && personalEmailTwo.equals("persontwo@testupdate.com") &&
			dateCreated.equals("3/28/2020") && groupName.equals("03/28") && comments.equals("Update comment.") &&
			accountManager.equals("Benjamin") && contactPersonOne.equals("测试人一"))
		{
			System.out.println("Successfully edited all fields.");
		}
		else
		{
			System.out.println("Unable to edit public email.");

		}
		
		deleteCompany();
		
	}
	
	public static void deleteCompany() {
		
		driver.findElement(By.linkText("Delete")).click();
		driver.findElement(By.id("delete")).click();
		
		List<WebElement> collapsibles=driver.findElements(By.className("collapsible"));
		List<String> strCompany=new ArrayList<String>();

		for(WebElement strElement:collapsibles) {
			String company=strElement.getText();
			strCompany.add(company);
		}
		
		if(strCompany.contains("Test Name"))
		{
			System.out.println("Unable to delete company.");
		}
		else
		{
			System.out.println("Company deleted");
		}
		
	}
	
}
