package baseTest;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import pageobject.RevenueCalculatorPage;

public class BaseClass {
	
public WebDriver driver;
public RevenueCalculatorPage revenuePage;
public Logger logger;

     @Parameters("browser")
	 @BeforeClass
	    public void setUp(String br) {
		 logger=LogManager.getLogger(this.getClass());
		 
		 
		 switch(br.toLowerCase()) {
			case "chrome" : driver= new ChromeDriver();break;
			case "edge"   : driver = new EdgeDriver(); break;
			case "firefox" : driver = new FirefoxDriver(); break;
			default : System.out.println("Invalid Browser name...");return;
			}
		 
	        
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.get("https://www.fitpeo.com/");
	        revenuePage = new RevenueCalculatorPage(driver);
	    }
	
	 @AfterClass
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	            
	        }
	    }
	
	 
	 public String captureScreen(String tname) {
	    	
	    	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date() );
	    	TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
	    	File sourcefile = takesScreenshot.getScreenshotAs(OutputType.FILE);
	    	
	    	String targetFilePath = System.getProperty("user.dir")+".\\screenshots\\" +tname+ "-" + timeStamp + ".png";
	    	File targetFile = new File(targetFilePath);
	    	
	    	sourcefile.renameTo(targetFile);
	    	
	    	return targetFilePath;
	    }
	 
}
