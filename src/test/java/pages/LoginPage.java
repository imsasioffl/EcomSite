package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

//    elements

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

    @FindBy(id = "login2")
    private static WebElement LoginBtn;
    @FindBy(id = "loginusername")
    private static WebElement LoginUsername;
    @FindBy(id = "loginpassword")
    private static WebElement LoginPassword;
    @FindBy(xpath = "//button[normalize-space(text())='Log in']")
    private static WebElement LogInBtn;

//    Actions
    public void Register(String Username , String Password){
        signInBtn.click();
        RegUsername.sendKeys(Username);
        RegPassword.sendKeys(Password);
        SignUpBtn.click();
    }

    public void Login(String Username , String Password){
        LoginBtn.click();
        LoginUsername.sendKeys(Username);
        LoginPassword.sendKeys(Password);
        LogInBtn.click();
    }



}
