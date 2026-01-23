package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage {

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //    elements
//Log in
    @FindBy(id = "signin2")
    private static WebElement signInBtn;
    @FindBy(id = "login2")
    private static WebElement logInBtn;
    @FindBy(id = "sign-username")
    private static WebElement RegUsername;
    @FindBy(id = "sign-password")
    private static WebElement RegPassword;
    @FindBy(xpath = "//button[normalize-space(text())='Sign up']")
    private static WebElement SignUpBtn;

    //Sign in
    @FindBy(id = "login2")
    private static WebElement LoginBtn;
    @FindBy(id = "loginusername")
    private static WebElement LoginUsername;
    @FindBy(id = "loginpassword")
    private static WebElement LoginPassword;
    @FindBy(xpath = "//button[normalize-space(text())='Log in']")
    private static WebElement LogInBtn;
    private final WebDriver driver;

    //home page
    @FindBy(xpath = "//a[@id='nameofuser' and contains(normalize-space(text()),'Welcome')]")
    public WebElement welcomeUserText;


    //    Actions
    public void register(String Username, String Password) {
        signInBtn.click();
        RegUsername.sendKeys(Username);
        RegPassword.sendKeys(Password);
        SignUpBtn.click();
    }

    public void Login(String Username, String Password) {
        LoginBtn.click();
        LoginUsername.sendKeys(Username);
        LoginPassword.sendKeys(Password);
        LogInBtn.click();

        String welcomeText = welcomeUserText.getText();
        Assert.assertTrue(welcomeText.contains("Welcome"), "Expected 'Welcome' text, but found: " + welcomeText);

    }


}
