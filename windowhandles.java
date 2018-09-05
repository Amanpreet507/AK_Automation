import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class child {

	public static int i;
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Amanpreet Singh\\Downloads\\chromedriver_win32\\chromedriver.exe");
		
		WebDriver driver= new ChromeDriver();
		driver.get("https://round.glass/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
    
    //moving to Samsara sub menu from mousehover menu tray, into new tab
		Actions mh= new Actions(driver);
		mh.moveToElement(driver.findElement(By.xpath("//ul[@class='navigation']/li[2]/a"))).click().build().perform();
		driver.findElement(By.linkText("SAMSARA")).click();
    
    //waiting for overlay to disappear 
		WebDriverWait hid= new WebDriverWait(driver, 30);
		hid.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='preloader']")));
		driver.findElement(By.xpath("//div[@class='copyright']/a")).click();
    
    //getting windowhandles and moving to child window
		String parent= driver.getWindowHandle();
		Set<String> whndl= driver.getWindowHandles();
		int count= whndl.size();
		System.out.println(count);
		Iterator<String> itr= whndl.iterator();
		
			
	   while(itr.hasNext() && count==2){
			String w2= itr.next();
			driver.switchTo().window(w2);
			System.out.println(driver.getTitle());
		}
		
    //moving back to parent window
		Actions mh2= new Actions(driver);
		mh2.moveToElement(driver.findElement(By.xpath("//ul[@class='navigation']/li[2]/a"))).click().build().perform();
		driver.findElement(By.linkText("SAMSARA")).click();
		driver.switchTo().window(parent);
		driver.navigate().back();
		System.out.println(driver.getCurrentUrl());
		
	}
}

