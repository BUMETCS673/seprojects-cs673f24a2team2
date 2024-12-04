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
public class FileServiceSeleniumTest {
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
    public void testGetImageByName() throws InterruptedException {
        // Access the image endpoint with the query parameter
        String url = "http://47.90.156.233:8080/image?imageName=file16952194621441015alabaster-co-UtRyYXcbK6A-unsplash.jpg";
        driver.get(url);

        // Add the user ID cookie to simulate authentication
        org.openqa.selenium.Cookie userIdCookie = new org.openqa.selenium.Cookie("shUserId", "70");
        driver.manage().addCookie(userIdCookie);
        driver.navigate().refresh(); // Refresh the page to ensure the cookie is applied

        // Verify the cookie is added
        System.out.println("Cookies set in browser: " + driver.manage().getCookies());

        // Wait for the image to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement imageElement;
        try {
            imageElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("img")));
        } catch (TimeoutException e) {
            System.out.println("Timeout occurred while waiting for the image to load.");
            System.out.println(driver.getPageSource()); // Print the page source for debugging
            throw e; // Re-throw the exception if necessary
        }

        // Verify the image's source attribute matches the requested file name
        String imageUrl = imageElement.getAttribute("src");
        System.out.println("Image Source: " + imageUrl); // Optional: Print the image source for verification

        // Assertions to verify the image is loaded correctly
        assertTrue(imageUrl.contains("file16952194621441015alabaster-co-UtRyYXcbK6A-unsplash.jpg"),
                "Image URL does not contain the expected file name.");

        // Add a delay for observation
        Thread.sleep(5000); // Wait for 5 seconds
    }

}
