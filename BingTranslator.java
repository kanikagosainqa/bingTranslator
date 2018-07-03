package Bing.BingTranslator;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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
		//System.out.println(language);
		textArea.sendKeys("hello");
		Select translatedLanguage = new Select(driver.findElement(By.id("t_tl")));
		translatedLanguage.selectByVisibleText("Finnish");
		//System.out.println(translatedLanguage);
		Assert.assertFalse(translateArea.getText().contains("Moi"));
    }
 
    @Test(priority=3)
    public void switchLanguage() {
    	textArea.clear();
    	textArea.sendKeys("hello"); 
    	driver.findElement(By.xpath("//*[@id=\"t_revIcon\"]")).click();
    	Assert.assertFalse(textArea.getText().contains("moi"));
    }
    
    @Test(priority=4)
    public void historyButtonTranslation() {
		driver.findElement(By.className("ttl_histbtn")).click();
    	textArea.clear();
    	textArea.sendKeys("hello");
    }

    @Test(priority=5)
    public void copy() {
    	je.executeScript("document.querySelector('#t_sv').clear()");
     	je.executeScript("document.querySelector('#t_sv').value='hello'");
    	je.executeScript("document.querySelector('#t_copyIcon').click()");
    	Assert.assertTrue(false, "document.getElementByName('t_copylink').value = 'Copied!'");
    }
    
    @Test(priority=6)
    public void share() {

//    	je.executeScript("document.getElementById('t_sv').clear()");
//     	je.executeScript("document.getElementById('t_sv').value='hello'");
//    	je.executeScript("document.getElementById('t_tv').clear()");
    	textArea.clear();
    	textArea.sendKeys("hello");
    	Select translatedLanguage = new Select(driver.findElement(By.cssSelector("#t_tl")));
		translatedLanguage.selectByVisibleText("Finnish");
		//System.out.print(je.executeScript("document.getElementById('t_tv')").toString());
		driver.findElement(By.className("t_txtblkout"));
    	WebDriverWait wait = new WebDriverWait(driver,10);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("actFacebookSvgIcon")));
    	
//    	je.executeScript("document.queryExecutor('#t_outoption > span.actionmenu > div.actionmenucontent.hiddenmenu.nonemenu > ol > li.actionitem.shareactionitem > div.sharemedia > a:nth-child(1) > div > svg')"
//    			+ ".click()");
    	driver.findElement(By.className("actFacebookSvgIcon")).click();
    	Assert.assertEquals("https://www.facebook.com/login.php?skip_api_login=1&api_key=570810223073062&signed_next=1&next=https%3A%2F%2Fwww.facebook.com%2Fdialog%2Ffeed%3Fapp_id%3D570810223073062%26display%3Dpopup%26link%3Dhttps%253A%252F%252Fwww.bing.com%252Ftranslator%253FtoWww%253D1%2526redig%253D9E65CF425CDD42E79E665F1DB50C26BC%2526shtp%253DFacebook%2526shid%253D552a3c65-0114-4b04-ab6e-f5cf9c09695c%2526shtk%253DQmluZyBUcmFuc2xhdG9y%2526shdk%253DSW5zdGFudGx5IHRyYW5zbGF0ZSB5b3VyIHRleHQgZnJvbSBvbmUgbGFuZ3VhZ2UgdG8gYW5vdGhlciB3aXRoIEJpbmcgVHJhbnNsYXRvci4gUG93ZXJlZCBieSBNaWNyb3NvZnQgVHJhbnNsYXRvciwgdGhlIHNpdGUgcHJvdmlkZXMgZnJlZSB0cmFuc2xhdGlvbiB0byBhbmQgZnJvbSA1MCsgbGFuZ3VhZ2VzLg%25253D%25253D%2526shhk%253DCxyUtW7hMltTBFs%25252FCVJ1Jt5%25252B8CGz92lQRd65kbr%25252FP8M%25253D%2526shtc%253D0%2526form%253DEX0023%2526shth%253DOSH.d4TfE0HZdXAHWp96azLmSQ%26redirect_uri%3Dhttps%253A%252F%252Fwww.bing.com%252Fshare%252Ffbre%26ref&cancel_url=https%3A%2F%2Fwww.bing.com%2Fshare%2Ffbre%3Ferror_code%3D4201%26error_message%3DUser%2Bcanceled%2Bthe%2BDialog%2Bflow%23_%3D_&display=popup&locale=en_GB" 
    			, driver.getCurrentUrl());
    }
    
    @Test//(priority=7)
    public void clearText() {
    	textArea.sendKeys("hello");
    	driver.findElement(By.xpath("//*[@id=\"t_edc\"]")).click();
    	Assert.assertEquals("",textArea.getText());
    }
    
    
}
