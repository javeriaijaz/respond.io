package Tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {

    private WebDriver driver;
    private ExtentReports extent;
    private ExtentTest test;
    private ExtentSparkReporter spark;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        // Initialize ExtentReports and SparkReporter
        
        extent = new ExtentReports();
        spark = new ExtentSparkReporter("Report.html");
        extent.attachReporter(spark);

        // Setup WebDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://app.respond.io/login");
    }

    @BeforeMethod
    public void beforeMethod() {
        // Optional: navigate to the login page before each test method
        driver.get("https://app.respond.io/login");
    }

    // Test Case 1: Valid Login Scenario
    @Test
    public void testLogin() {
        test = extent.createTest("Valid Login Test").info("Test for valid login");

        // Locate and enter email using explicit wait
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("field_2")));
        emailField.sendKeys("javeriaijaz19@gmail.com");
        test.info("Entered valid email");

        // Locate and enter password using explicit wait
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("field_3")));
        passwordField.sendKeys("gp.!nL_BaGc2f67"); // Replace with actual password
        test.info("Entered valid password");

        // Locate and click on the Sign In button using explicit wait
        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        signInButton.click();
        test.info("Clicked sign in button");

        try {
            // Validate successful login by waiting for the post-login element (e.g., a dashboard or welcome message)
            WebElement dashboardElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Dashboard']")));
            assert dashboardElement.isDisplayed();
            test.pass("Login test passed successfully.");
        } catch (Exception e) {
            test.fail("Login test failed: " + e.getMessage());
        }
    }
    
    @Test
    public void testEmptyFields() {
        test = extent.createTest("Empty Fields Test").info("Test for empty email and password fields");

        // Leave email and password fields blank
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("field_2")));
        emailField.click();
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("field_3")));
        passwordField.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('body').click();");
     

        // Validate error messages
        try {
            WebElement emailError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='This field is required']")));
            WebElement passwordError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='This field is required']")));
            assert emailError.isDisplayed() && passwordError.isDisplayed();
            test.pass("Error messages displayed for empty email and password fields.");
        } catch (Exception e) {
            test.fail("Error message for empty fields not displayed: " + e.getMessage());
        }
    }

    // Test Case: Invalid Email Scenario
    @Test
    public void testInvalidEmail() {
        test = extent.createTest("Invalid Email Test").info("Test for invalid email input");

        // Locate and enter an invalid email
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("field_2")));
        emailField.sendKeys("invalidEmailFormat");
        test.info("Entered invalid email format");

        // Locate and enter a valid password
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("field_3")));
        passwordField.sendKeys("validPassword123");
        test.info("Entered valid password");

        // Click the Sign In button
        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        signInButton.click();
        test.info("Clicked sign in button");

        // Validate error message for invalid email
        try {
            WebElement emailError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='This field value must be a valid email']")));
            assert emailError.isDisplayed();
            test.pass("Error message displayed for invalid email format.");
        } catch (Exception e) {
            test.fail("Error message for invalid email not displayed: " + e.getMessage());
        }
    }

    // Additional test cases like invalid login, empty fields, etc. follow the same structure...

    @AfterMethod
    public void tearDownMethod() {
        // Optional: perform any cleanup or navigate to a specific page after each test
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }

        // Flush the ExtentReports
        extent.flush();
    }
}
