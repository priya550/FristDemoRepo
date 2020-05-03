package PracticePrgMaven.Practice;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LaunchBro {

	
	WebDriver driver;

	@BeforeTest
	public void openBro() {
		// Simple Launch

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Priyanga\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();

		System.out.println("Browser Launched..");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.get("https://www.mycontactform.com/index.php");

		System.out.println("Site Launched..");
	}

	@Test(priority = 1)
	public void getPgTitle() {
		String HomePageTitle = driver.getTitle();
		System.out.println("Home Page Title Is : " + HomePageTitle);
	}

	@Test(priority = 2)
	public void signIn() {
		// SignIn
		driver.findElement(By.id("user")).sendKeys("sahanasubbiah");
		driver.findElement(By.id("pass")).sendKeys("sahanasubbiah");
		driver.findElement(By.name("btnSubmit")).click();
	}

	// Next_Page
	@Test(priority = 3)
	public void nextPage_SampleForm() {
		driver.findElement(By.xpath("//a[contains(text(),'Sample Forms')]")).click();
	}

	// SamplePage_CheckBox
	@Test(priority = 4)
	public void samplePg_CheckBox() {
		WebElement element = driver.findElement(By.name("email_to[]"));
		for (int i = 1; i < 2; i++) {
			element.click();
			System.out.println(element.isSelected());
		}
		

		driver.findElement(By.id("subject")).sendKeys("Reg Complaint");
		driver.findElement(By.id("email")).sendKeys("sahanasubbiahmca@gmail.com");
	}
	
	@Test(priority=5)
	public void dropDown()
	{
		Select sel = new Select(driver.findElement(By.id("q3")));
		sel.selectByVisibleText("First Option");
	}
	
	@Test(priority=6)
	public void calender() throws InterruptedException
	{
		
		driver.findElement(By.id("q7")).click();
		
		Thread.sleep(2000);
		
		Select sel = new Select(driver.findElement(By.className("ui-datepicker-month")));
		sel.selectByVisibleText("Sep");
		
		
		List<WebElement> alldates = driver.findElements(By.xpath("//div[@id='ui-datepicker-div']/table//td"));
		
		int allnode = alldates.size();
			
		for(int i=0; i<allnode;i++)
		{
			String date = alldates.get(i).getText();
			if(date.equalsIgnoreCase("28"))
			{
				alldates.get(i).click();
				break;
			}
		}
		
	}
	
	@Test(priority=7)
	public void radio()
	{
		
		List<WebElement> li = driver.findElements(By.xpath("//input[@name='q4']"));
		
		for(int i=0;i<li.size();i++)
		{
			WebElement ele = li.get(i);
			String val = ele.getAttribute("value");
			
			System.out.println(val);
		
			if(val.equalsIgnoreCase("Third Option"))
					{
						ele.click();
					}
		}
		
	}

	@Test(priority=8)
	public void checkBox()
	{
		List<WebElement> list = driver.findElements(By.xpath("//input[@name='checkbox6[]']"));
		
		for(int i=0;i<list.size();i++)
		{
			WebElement ele = list.get(i);
			
			String item = ele.getAttribute("value");
			
			if(item.equalsIgnoreCase("Second Check Box"))
			{
				ele.click();
			}
			else if(item.equalsIgnoreCase("Third Check Box"))
			{
				ele.click();
			}
		}
		
	}
	
	@Test(priority=9)
	public void srollDown()
	{
		WebElement ele = driver.findElement(By.xpath("//input[@id='attach4589']"));
		Actions act = new Actions(driver);
		act.moveToElement(ele);
		act.perform();
	}
	
	@Test(priority=10)
	public void uploadFile()
	{
		driver.findElement(By.xpath("//input[@id='attach4589']")).sendKeys("C:\\Users\\Priyanga\\Desktop\\Test.txt");
		System.out.println("File Uploaded..");
	}
	
	@Test(priority=11)
	public void user_enters_verification_code() throws InterruptedException {

		WebElement elem = driver.findElement(By.xpath("//img[@id='verification_image']"));
		String code = elem.getText();
		System.out.println("Verification code is--" +code);
		driver.findElement(By.xpath("//input[@id='visver_code']")).sendKeys(code);
		Thread.sleep(5000);
	}
	
	
	@Test(priority=12)
	public void clicks_submit_button() {
		driver.findElement(By.xpath("//input[@name='submit']")).click();
	}
}
