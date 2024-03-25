package Project;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class nop_Commerce {
    
	 WebDriver driver;
     WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
     ExtentReports report;
     ExtentTest test;
    		
	@BeforeTest
	public void Register() {
	    driver = new ChromeDriver();
		driver.get("https://demo.nopcommerce.com/register");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		report= new ExtentReports(System.getProperty("user.dir")+"./src/test/resources/Reports/new.html");
		test= report.startTest("nop");
		
		}
	@Test(priority = 1)
    public void startup () throws InterruptedException {
		try {
		//REGISTER-Your personal Details
		driver.findElement(By.id("gender-female")).click();
		driver.findElement(By.name("FirstName")).sendKeys("Geetha");
		driver.findElement(By.name("LastName")).sendKeys("S");
		
        WebElement date = driver.findElement(By.name("DateOfBirthDay"));
		Select s=new Select(date);
		s.selectByValue("16");
		
		WebElement month= driver.findElement(By.name("DateOfBirthMonth"));
		Select s1=new Select(month);
		s1.selectByVisibleText("February");
		
		WebElement year = driver.findElement(By.name("DateOfBirthYear"));
		Select s2=new Select(year);
		s2.selectByValue("1999");

		LocalDateTime date1 = LocalDateTime.now();
		driver.findElement(By.id("Email")).sendKeys("geetha"+date1+"@gmail.com");

		//Company Details
		driver.findElement(By.name("Company")).sendKeys("MoolyaED");

		//Option Newsletter
		driver.findElement(By.id("Newsletter")).click();
		
		//Your Password
		driver.findElement(By.id("Password")).sendKeys("Geetha@123");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("Geetha@123");
		//Register
		driver.findElement(By.name("register-button")).click();

		//Continue
		driver.findElement(By.partialLinkText("Continu")).click();

		}catch(Exception e) {
			System.out.println("exception");
			}
		test.log(LogStatus.PASS, "STEP 1=register by entering necessary credentials ->Successful" );
        }
	
	@Test(dependsOnMethods={"startup"})//,priority = 2)
	public void checkout() throws InterruptedException {
	   // check box,agreeing teams and conditions,checkout
		WebElement book  = driver.findElement(By.linkText("Books"));
		book.click();
		test.log(LogStatus.PASS, "STEP 2=Click on Book->Successful");
		
		WebElement add_to_cart = driver.findElement(By.xpath("(//button[text()='Add to cart'])[1]"));
		add_to_cart.click();
        test.log(LogStatus.PASS, "STEP 3=Add Below item to the Cart- 'Add to cart'->Successful");
		
		WebElement shopping_cart = driver.findElement(By.xpath("//span[text()='Shopping cart']"));
		shopping_cart.click();
		test.log(LogStatus.PASS, "STEP 4=Click on shoppingcart ->Successful");
		
		WebElement checkbox = driver.findElement(By.id("termsofservice"));
		checkbox.click();
		WebElement checkout = driver.findElement(By.id("checkout"));
		checkout.click();
		test.log(LogStatus.PASS, "STEP 5=Click on checkbox agreeing terms&Conditions and Click on Checkout ->Successful");
		}
	
	
	@Test (dependsOnMethods = {"checkout"})//(priority = 3)
	public void Guest() {
		//checkout as a guest
		WebElement Guest = driver.findElement(By.xpath("//button[text()='Checkout as Guest']"));
		Guest.click();
		test.log(LogStatus.PASS, "STEP 6=Check out as Guest ->Successful");
		}
		
	@Test (dependsOnMethods = {"Guest"})//(priority = 4)
	public void Billing_address() {
			//Billing address
			driver.findElement(By.id("BillingNewAddress_FirstName")).sendKeys("Geetha");
			driver.findElement(By.id("BillingNewAddress_LastName")).sendKeys("S");
			driver.findElement(By.id("BillingNewAddress_Email")).sendKeys("sweety@gmail.com");
			driver.findElement(By.name("BillingNewAddress.Company")).sendKeys("MoolyaED");
			
			WebElement country = driver.findElement(By.id("BillingNewAddress_CountryId"));
			Select c= new Select (country);
			c.selectByVisibleText("India");
			
			//WebElement state=driver.findElement(By.id("BillingNewAddress_StateProvinceId"));
			//Select st= new Select(state);
			//st.selectByVisibleText("Other");
			
			driver.findElement(By.id("BillingNewAddress_City")).sendKeys("Trichy");
			driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys("14, Bharathidasan nagar");
			driver.findElement(By.id("BillingNewAddress_Address2")).sendKeys("North kattur, Trichy.");
			driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys("620019");
			driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys("9789317513");
			driver.findElement(By.id("BillingNewAddress_FaxNumber")).sendKeys("123-456-0987");
			driver.findElement(By.name("save")).click();
			test.log(LogStatus.PASS, "STEP 7=Enter required BillingDetails ->Successful");
			
			//Shipping Method
			//driver.findElement(By.id("shippingoption_0")).click();
			driver.findElement(By.xpath("(//button[text()='Continue'])[3]")).click();
			test.log(LogStatus.PASS, "STEP 8=Continue with Shipping Method ->Successful");
			
			//Payment Method
			//driver.findElement(By.id("paymentmethod_0")).click();
			driver.findElement(By.xpath("(//button[text()='Continue'])[4]")).click();
			test.log(LogStatus.PASS, "STEP 9=Choose check/money order and continue ->Successful");
			
			//Payment Information
			driver.findElement(By.xpath("(//button[text()='Continue'])[5]")).click();
			test.log(LogStatus.PASS, "STEP 10=Continue with Below address ->Successful");
			
			
			//Confirm the order
			driver.findElement(By.xpath("//button[text()='Confirm']")).click();
			test.log(LogStatus.PASS, "STEP 11=Ensure to confirm the order ->Successful");
			
			//Display the message
			WebElement display = driver.findElement(By.xpath("//strong[text()='Your order has been successfully processed!']"));
			System.out.println(display.getText());
			test.log(LogStatus.PASS, "STEP 12=Display the message in your Console ->Successful");
		    }
	
	 
    @AfterTest 
    public  void close() {
        driver.close();
        report.endTest(test);
        report.flush();
       
   }
    
}
