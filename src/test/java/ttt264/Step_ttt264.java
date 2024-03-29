package ttt264;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.*;
public class Step_ttt264 {
	public WebDriver driver ;
 @Given("User Launch {string}")
public void user_Launch(String string) {
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//Drivers/chromedriver.exe") ; 
		driver=new ChromeDriver(); 
	   
	}
@When("User opens URL {string}")
public void user_opens_URL(String url) {
	   
		driver.get(url);
	}
@When("User clic in input has xpath {string} and Value as {string}")
	public void user_clic_in_input_has_xpath_and_Value_as(String string, String string2) {
		driver.findElement(By.xpath(string)).clear(); 
		driver.findElement(By.xpath(string)).sendKeys(string2);}
@When("User clic in input submit has xpath {string}")
public void user_clic_in_input_submit_has_xpath(String string) throws Exception {
		driver.findElement(By.xpath(string)).click();
Thread.sleep(2000);
	}

@When("close browser")
public void close_browser() {
		driver.quit();
	}
}