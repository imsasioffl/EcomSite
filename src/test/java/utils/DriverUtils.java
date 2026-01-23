package utils;

import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

public class DriverUtils {

    @Getter
    private static WebDriver driver;

    public static void initDriver() {
//        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.demoblaze.com/");

    }

    public static void quitDriver() {
        driver.quit();
        driver = null;
    }

    public static String handleAlertIfPresent(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
            Alert alert = wait.until(d -> d.switchTo().alert());
            String text = alert.getText();
            alert.accept();
            return text;
        } catch (Exception e) {
            return null; // No alert
        }
    }

    public static void highlightElement(WebElement element) {
        if (driver == null) return;
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String originalStyle = element.getAttribute("style");
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
                    element, "border: 3px solid green;");
            // Optional pause so highlight is visible
            Thread.sleep(300);
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, originalStyle);
        } catch (Exception e) {
            System.out.println("Could not highlight element: " + e.getMessage());
        }
    }

    public static void takeScreenshot(String fileName) {
        if (driver == null) return;
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.createDirectories(Paths.get("screenshots")); // create folder if not exists
            Files.copy(srcFile.toPath(), Paths.get("screenshots/" + fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isElementPresent(WebDriver driver, WebElement element) {
        try {
            element.isDisplayed();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
