package Tests;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import PageObject.CareersPage;

public class NavigateToCareersPageTestCases {

    private static WebDriver driver = null;
    private static ExtentReports extent;
    private static ExtentSparkReporter spark;
    private static ExtentTest test;
    private CareersPage careersPage;  // Single instance for the CareersPage object
    private final String baseUrl = "https://www.moneylion.com";

    @BeforeSuite
    public void setUpSuite() {
        // Initialize ExtentReports
        extent = new ExtentReports();
        spark = new ExtentSparkReporter("Report.html");
        extent.attachReporter(spark);
    }

    @BeforeMethod
    public void setUpTest(Method method) {
        // Initialize WebDriver and WebDriverWait
        driver = new ChromeDriver();

        // Create a test entry for ExtentReports
        test = extent.createTest(method.getName());
        
        driver.manage().window().maximize();
        // Navigate to the MoneyLion homepage
        driver.get(baseUrl);

        // Initialize CareersPage object
        careersPage = new CareersPage(driver);
    }

    @Test
    public void verifyCareersPageFlow() throws InterruptedException {
        // Step 1: Navigate to homepage
        test.info("Navigated to MoneyLion homepage.");
        
        // Step 2: Click on the Careers link
        careersPage.clickCareersLink();
        test.info("Clicked on Careers link.");
        
        // Step 3: Verify redirection to Careers page
        String currentUrl = careersPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("careers"), "Redirection to Careers page failed.");
        test.pass("Redirection to Careers page successful.");
        
        // Step 4: Verify the "WHERE WE WORK" heading
        Assert.assertEquals(careersPage.getWhereWeWorkHeading(), "Where we work");
        test.pass("\"WHERE WE WORK\" heading is displayed.");
        
        // Step 5: Verify the locations text
        ArrayList<String> locations_array = careersPage.getLocationsText();
        String[] citiesToCheck = {"NEW YORK CITY", "SIOUX FALLS", "KUALA LUMPUR", "AROUND THE GLOBE"};

        // Assert that each city is found in the locations_array
        for (String city : citiesToCheck) {
            Assert.assertTrue(locations_array.contains(city));
        }
        test.pass("Locations Block is found");
    }

    @AfterMethod
    public void tearDownTest() {
        // Close WebDriver after each test
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }

    @AfterSuite
    public void tearDownSuite() {
        // Finalize the ExtentReports
        extent.flush();
    }
}
