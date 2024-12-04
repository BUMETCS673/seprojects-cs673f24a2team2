package edu.bu.cs673.secondhand.service.Selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressServiceSeleniumTest {
    private WebDriver driver;

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        // Set the ChromeDriver version for Chrome 130
        WebDriverManager.chromedriver().driverVersion("130.0.6723.92").setup();

        // Initialize the WebDriver
        driver = new ChromeDriver();
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testGetAddressByUser() throws InterruptedException {
        // Access the address info page
        String url = "http://47.90.156.233:8080/address/info";
        driver.get(url);

        // Add the user ID cookie to simulate authentication
        org.openqa.selenium.Cookie userIdCookie = new org.openqa.selenium.Cookie("shUserId", "70");
        driver.manage().addCookie(userIdCookie);
        driver.navigate().refresh(); // Refresh the page to ensure the cookie is applied

        // Verify the cookie is added
        System.out.println("Cookies set in browser: " + driver.manage().getCookies());

        // Wait for the JSON response to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement resultContent;
        try {
            resultContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("pre")));
        } catch (TimeoutException e) {
            System.out.println("Timeout occurred while waiting for the JSON content.");
            System.out.println(driver.getPageSource()); // Print the page source for debugging
            throw e; // Re-throw the exception if necessary
        }

        // Parse the JSON content
        String jsonResponse = resultContent.getText();
        System.out.println("Actual JSON Response: " + jsonResponse); // Print the actual JSON response for debugging

        // Validate the JSON response structure
        assertTrue(jsonResponse.contains("70"), "Expected userId '70' not found in JSON response.");
        assertTrue(jsonResponse.contains("Srujana N"), "Expected consigneeName 'Srujana N' not found.");

        // Add a delay for observation
        Thread.sleep(5000); // Wait for 5 seconds
    }

}
