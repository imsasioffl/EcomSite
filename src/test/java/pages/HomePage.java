package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.DriverUtils;

public class HomePage {

    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ===== Elements =====

    // Sign up
    @FindBy(id = "signin2")
    private WebElement signInBtn;

    @FindBy(id = "sign-username")
    private WebElement RegUsername;

    @FindBy(id = "sign-password")
    private WebElement RegPassword;

    @FindBy(xpath = "//button[normalize-space(text())='Sign up']")
    private WebElement SignUpBtn;

    // Login
    @FindBy(id = "login2")
    private WebElement LoginBtn;

    @FindBy(id = "loginusername")
    private WebElement LoginUsername;

    @FindBy(id = "loginpassword")
    private WebElement LoginPassword;

    @FindBy(xpath = "//button[normalize-space(text())='Log in']")
    private WebElement LogInBtn;

    // Home page
    @FindBy(xpath = "//a[@id='nameofuser' and contains(normalize-space(text()),'Welcome')]")
    public WebElement welcomeUserText;

    // ===== Actions =====

    public void register(String Username, String Password) {

        DriverUtils.highlightElement(signInBtn);
        signInBtn.click();
        DriverUtils.takeScreenshot("01_click_signUp");

        DriverUtils.highlightElement(RegUsername);
        RegUsername.sendKeys(Username);
        DriverUtils.takeScreenshot("02_enter_register_username");

        DriverUtils.highlightElement(RegPassword);
        RegPassword.sendKeys(Password);
        DriverUtils.takeScreenshot("03_enter_register_password");

        DriverUtils.highlightElement(SignUpBtn);
        SignUpBtn.click();
        DriverUtils.takeScreenshot("04_click_register_submit");
    }

    public void Login(String Username, String Password) {

        DriverUtils.highlightElement(LoginBtn);
        LoginBtn.click();
        DriverUtils.takeScreenshot("05_click_login");

        DriverUtils.highlightElement(LoginUsername);
        LoginUsername.sendKeys(Username);
        DriverUtils.takeScreenshot("06_enter_login_username");

        DriverUtils.highlightElement(LoginPassword);
        LoginPassword.sendKeys(Password);
        DriverUtils.takeScreenshot("07_enter_login_password");

        DriverUtils.highlightElement(LogInBtn);
        LogInBtn.click();
        DriverUtils.takeScreenshot("08_click_login_submit");

        // Validate welcome text
        DriverUtils.highlightElement(welcomeUserText);
        String welcomeText = welcomeUserText.getText();
        DriverUtils.takeScreenshot("09_validate_welcome_text");

        Assert.assertTrue(
                welcomeText.contains("Welcome"),
                "Expected 'Welcome' text, but found: " + welcomeText
        );
    }
}
