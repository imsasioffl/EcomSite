package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.DriverUtils;

import java.time.Duration;

public class CartPage {

    private final WebDriver driver;

    // Cart validation
    @FindBy(xpath = "(//td[text()='360']/preceding-sibling::td)[2]")
    public WebElement cartProductName;

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

    // ===== Actions =====

    public void validateAndPlaceOrder() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(CartText));
        DriverUtils.takeScreenshot("15_cart_page_loaded");

        DriverUtils.highlightElement(cartProductName);
        String productTitle = cartProductName.getText();
        DriverUtils.takeScreenshot("16_validate_cart_product");

        Assert.assertTrue(
                productTitle.contains("Samsung galaxy s6"),
                "Expected 'Samsung galaxy s6' text, but found: " + productTitle
        );

        DriverUtils.highlightElement(placeOrderButton);
        placeOrderButton.click();
        DriverUtils.takeScreenshot("17_click_place_order");
    }

    public void checkout(String name, String country, String city,
                         String cardNo, String month, String year) {

        // Fill order form
        DriverUtils.highlightElement(nameInput);
        nameInput.sendKeys(name);
        DriverUtils.takeScreenshot("18_enter_name");

        DriverUtils.highlightElement(countryInput);
        countryInput.sendKeys(country);
        DriverUtils.takeScreenshot("19_enter_country");

        DriverUtils.highlightElement(cityInput);
        cityInput.sendKeys(city);
        DriverUtils.takeScreenshot("20_enter_city");

        DriverUtils.highlightElement(cardInput);
        cardInput.sendKeys(cardNo);
        DriverUtils.takeScreenshot("21_enter_card");

        DriverUtils.highlightElement(monthInput);
        monthInput.sendKeys(month);
        DriverUtils.takeScreenshot("22_enter_month");

        DriverUtils.highlightElement(yearInput);
        yearInput.sendKeys(year);
        DriverUtils.takeScreenshot("23_enter_year");

        DriverUtils.highlightElement(purchaseBtn);
        purchaseBtn.click();
        DriverUtils.takeScreenshot("24_click_purchase");

        // Wait for confirmation
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(purchaseConfirmationMessage));

        DriverUtils.highlightElement(purchaseConfirmationMessage);
        String confirmationMessage = purchaseConfirmationMessage.getText();
        DriverUtils.takeScreenshot("25_validate_confirmation_message");

        Assert.assertTrue(
                confirmationMessage.contains("Thank you for your purchase!"),
                "Expected confirmation message but found: " + confirmationMessage
        );

        // Close confirmation
        DriverUtils.highlightElement(purchaseOk);
        purchaseOk.click();
        DriverUtils.takeScreenshot("26_click_ok");

        // Close modal
        DriverUtils.highlightElement(closeForm);
        closeForm.click();
        DriverUtils.takeScreenshot("27_close_order_modal");

        // Logout
        WebDriverWait logoutWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        logoutWait.until(ExpectedConditions.visibilityOf(logoutLink));

        DriverUtils.highlightElement(logoutLink);
        logoutLink.click();
        DriverUtils.takeScreenshot("28_click_logout");
    }
}
