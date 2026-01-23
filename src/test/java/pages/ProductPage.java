package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.DriverUtils;

public class ProductPage {

    private final WebDriver driver;

    // Product selection Elements
    @FindBy(xpath = "//a[normalize-space(text())='Samsung galaxy s6']")
    public WebElement samsungGalaxyS6;

    @FindBy(xpath = "//h2[normalize-space(text())='Samsung galaxy s6']")
    public WebElement productTitle;

    @FindBy(xpath = "//strong[text()='Product description']/following-sibling::p")
    public WebElement productDescription;

    @FindBy(xpath = "//a[normalize-space(text())='Add to cart']")
    public WebElement addToCartButton;

    @FindBy(xpath = "//a[normalize-space(text())='Cart']")
    public WebElement cartLink;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ===== Actions =====
    public void selectProduct() {

        // Click product
        DriverUtils.highlightElement(samsungGalaxyS6);
        samsungGalaxyS6.click();
        DriverUtils.takeScreenshot("10_click_samsung_product");

        // Validate product title
        DriverUtils.highlightElement(productTitle);
        String productTitleText = productTitle.getText().trim();
        System.out.println("Title is " + productTitleText);
        DriverUtils.takeScreenshot("11_validate_product_title");

        Assert.assertTrue(
                productTitleText.equalsIgnoreCase("Samsung galaxy s6"),
                "Expected 'Samsung galaxy s6' text, but found: " + productTitleText
        );

        // Validate product description
        DriverUtils.highlightElement(productDescription);
        String productDescriptionText = productDescription.getText().trim();
        System.out.println("Description is " + productDescriptionText);
        DriverUtils.takeScreenshot("12_validate_product_description");

        Assert.assertTrue(
                productDescriptionText.contains("The Samsung Galaxy S6 is powered by 1.5GHz octa-core Samsung Exynos"),
                "Product description mismatch. Found: " + productDescriptionText
        );

        // Add to cart
        DriverUtils.highlightElement(addToCartButton);
        addToCartButton.click();
        DriverUtils.takeScreenshot("13_click_add_to_cart");
        System.out.println("Clicked add to cart");

        // Handle alert
        String alertText = DriverUtils.handleAlertIfPresent(driver);
        System.out.println("Alert text: " + alertText);

        Assert.assertTrue(
                alertText != null && alertText.contains("Product added."),
                "Expected 'Product added.' alert but found: " + alertText
        );

        // Go to cart
        DriverUtils.highlightElement(cartLink);
        cartLink.click();
        DriverUtils.takeScreenshot("14_click_cart");
    }
}
