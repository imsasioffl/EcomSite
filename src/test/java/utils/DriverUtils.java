package utils;

import lombok.Getter;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

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

}
