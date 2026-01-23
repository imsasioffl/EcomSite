package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CartPage {

    private final WebDriver driver;
    // Cart validation
    @FindBy(xpath = "(//td[text()='360']/preceding-sibling::td)[2]")
    public WebElement cartProductName; // Samsung galaxy s6
    @FindBy(xpath = "//button[normalize-space(text())='Place Order']")
    public WebElement placeOrderButton;
    // Order form
    @FindBy(xpath = "//label[@for='name']/following-sibling::input[1]")
    public WebElement nameInput;
    @FindBy(xpath = "//label[@for='country']/following-sibling::input[1]")
    public WebElement countryInput;
    @FindBy(xpath = "//label[@for='city']/following-sibling::input[1]")
    public WebElement cityInput;
    @FindBy(xpath = "//label[@for='card']/following-sibling::input[1]")
    public WebElement cardInput;
    @FindBy(xpath = "//label[@for='month']/following-sibling::input[1]")
    public WebElement monthInput;
    @FindBy(xpath = "//label[@for='year']/following-sibling::input[1]")
    public WebElement yearInput;

    @FindBy(xpath = "//button[normalize-space(text())='Purchase']")
    public WebElement purchaseBtn;

    // Confirmation
    @FindBy(xpath = "//h2[normalize-space(text())='Thank you for your purchase!']")
    public WebElement purchaseConfirmationMessage;

    @FindBy(xpath = "//h2[normalize-space(text())='Total']")
    public WebElement CartText;

    @FindBy(xpath = "//button[normalize-space(text())='OK']")
    public WebElement purchaseOk;

    @FindBy(xpath = "//h5[@id='orderModalLabel']/following-sibling::button[@aria-label='Close']")
    public WebElement closeForm;

    // Logout
    @FindBy(xpath = "//a[normalize-space(text())='Log out']")
    public WebElement logoutLink;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


//    Actions

    public void validateAndPlaceOrder() {
        System.out.println("checking text is visible");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(CartText));
        System.out.println("checking productname is visible");
        String productTitle = cartProductName.getText();
        Assert.assertTrue(productTitle.contains("Samsung galaxy s6"),
                "Expected 'Samsung galaxy s6' text, but found: " + productTitle);
        System.out.println("clicking place order");
        placeOrderButton.click();
        System.out.println("clicked place order ");
    }

    public void checkout(String name, String country, String city,
                         String cardNo, String month, String year) {
        System.out.println("filling address ...");
        nameInput.sendKeys(name);
        countryInput.sendKeys(country);
        cityInput.sendKeys(city);
        cardInput.sendKeys(cardNo);
        monthInput.sendKeys(month);
        yearInput.sendKeys(year);
        purchaseBtn.click();
        System.out.println("submitted form. ...");

        // Wait for confirmation message
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(purchaseConfirmationMessage));

        String confirmationMessage = purchaseConfirmationMessage.getText();
        System.out.println("validating confirm msg ...");

        Assert.assertTrue(confirmationMessage.contains("Thank you for your purchase!"),
                "Expected confirmation message but found: " + confirmationMessage);
        System.out.println("validated confirm msg ...");

        purchaseOk.click();
        System.out.println("clicked OK...");
        closeForm.click();
        System.out.println("closed form...");
        WebDriverWait waits = new WebDriverWait(driver, Duration.ofSeconds(30));
        waits.until(ExpectedConditions.visibilityOf(logoutLink));
        logoutLink.click();
        System.out.println("clicked logout ...");
    }

}
