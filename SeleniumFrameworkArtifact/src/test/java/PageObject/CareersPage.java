package PageObject;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CareersPage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	private By careersLink = By.xpath("//a[text()='Careers']");
    private By locationsText = By.xpath("//p[contains(text(), 'New York City')]");
    private By whereWeWorkHeading = By.xpath("//h2[text()='Where we work']");
	
 // Constructor
    public CareersPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40)); // 40-second timeout
    }

    // Methods to interact with the page
    public void clickCareersLink() {
    	
    	WebElement careersElement = wait.until(ExpectedConditions.elementToBeClickable(careersLink));
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("arguments[0].click();", careersElement);
        wait.until(ExpectedConditions.urlContains("careers"));

    }

 // Wait for the URL to contain "careers"
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // Wait for the "WHERE WE WORK" heading to be visible and return its WebElement
    public String getWhereWeWorkHeading() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(whereWeWorkHeading)).getText();
    }

    // Wait for the locations text to be visible and return its content
    public ArrayList<String> getLocationsText() {
        
    	List<WebElement> locations = driver.findElements(By.xpath("//figcaption[@class='wp-element-caption']"));
    	ArrayList<String> locations_array = new ArrayList<String>();
    	
    	for(WebElement element : locations)
    	{
    		locations_array.add(element.getText());
        
    	}
    	return locations_array;
    }
}

