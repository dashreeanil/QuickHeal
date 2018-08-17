package Com.INIT;


import java.io.FileInputStream;



import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import Com.DBUtil.DbManager;
import Com.Lib.TestUtil;
import Com.Lib.Xls_Reader;



public class BasePage {
	
	
	public static WebDriver driver;
	public static Properties Config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Xls_Reader excel = new Xls_Reader(System.getProperty("user.dir")+"\\src\\dd_properties\\testdata.xlsx");
	public static Logger logs = Logger.getLogger("devpinoyLogger");
	
	@BeforeSuite
	public void init() throws IOException, AddressException, SQLException, ClassNotFoundException, MessagingException{
		
		if(driver==null){
		
		fis = new FileInputStream(System.getProperty("user.dir")+"/src/dd_properties/Config.properties");
		Config.load(fis);
		logs.debug("Loaded the Config property file");
		
		fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\dd_properties\\OR.properties");
		OR.load(fis);
		logs.debug("loaded the OR property file");
		
		
		}
		
		driver.get(Config.getProperty("testsiteurl"));
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		DbManager.setMysqlDbConnection();
		}
		
		
	
	
	
	
	public static WebElement findElement(String key) throws IOException{
		
		try{
		
			
			return driver.findElement(By.xpath(OR.getProperty(key)));
		
		
		}catch(Throwable t){
			
			
			TestUtil.CaptureScreenshot();
			return null;
			
		}
		
		
	}
	
	
	
	@AfterSuite
	public void QuitDriver(){
		
		//send mail
		//driver.quit();
		
	}

}
