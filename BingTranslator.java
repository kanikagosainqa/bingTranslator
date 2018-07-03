package Bing.BingTranslator;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BingTranslator 
{
	WebDriver driver;
	WebElement textArea,translateArea;
	JavascriptExecutor je;
	
    @BeforeClass
    public void OpenBing() throws Exception 
    {
    	System.setProperty("webdriver.chrome.driver", "/home/qainfotech/Desktop/chromedriver");
    	driver=new ChromeDriver();
    	driver.get("https://www.bing.com/translator");
    	textArea = driver.findElement(By.xpath("//*[@id=\"t_sv\"]"));
    	translateArea = driver.findElement(By.xpath("//*[@id=\"t_tv\"]"));
        je = (JavascriptExecutor)driver;
        driver.manage().window().maximize();
    }
    
    @AfterClass
	public void after()
	{
		//driver.quit();
	}
    
    @Test(priority=1)
    public void sameText() {
    	textArea.sendKeys("hello");
    	textArea.clear();
    	Assert.assertEquals("hello","hello");	
    }
    
    @Test(priority=2)
    public void dropDownListAndOptionSelect() {
    	Select language = new Select(driver.findElement(By.id("t_sl")));
    	language.selectByVisibleText("English");
		System.out.println(language);
		textArea.sendKeys("hello");
		Select translatedLanguage = new Select(driver.findElement(By.id("t_tl")));
		translatedLanguage.selectByVisibleText("Finnish");
		System.out.println(translatedLanguage);
		Assert.assertFalse(translateArea.getText().contains("Moi"));
    }
 
    @Test(priority=3)
    public void switchLanguage() {
    	textArea.clear();
    	textArea.sendKeys("hello"); 
    	driver.findElement(By.xpath("//*[@id=\"t_revIcon\"]")).click();
    	Assert.assertFalse(textArea.getText().contains("moi"));
    }
    
    
}
