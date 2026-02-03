package utils;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DriverUtils {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String FILE = "src/test/resources/user.json";
    @Getter
    private static WebDriver driver;

    public static void saveUser(String username, String password) {
        try {
            Map<String, String> data = new HashMap<>();
            data.put("username", username);
            data.put("password", password);

            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(FILE), data);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save user data", e);
        }
    }

    public static String getValue(String key) {
        try {
            Map<?, ?> data = mapper.readValue(new File(FILE), Map.class);
            return data.get(key).toString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to read user data", e);
        }
    }

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

    public static boolean isElementPresent(WebDriver driver, WebElement element) {
        try {
            element.isDisplayed();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
