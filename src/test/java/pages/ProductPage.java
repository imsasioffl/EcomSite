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


    //Actions

    public void selectProduct() {

        samsungGalaxyS6.click();

        String productTitleText = productTitle.getText();
        System.out.println("Title is "+productTitleText);
        Assert.assertTrue(productTitleText.contains("Samsung galaxy s6"),
                "Expected 'Samsung galaxy s6' text, but found: " + productTitleText);


        String productDescriptionText = productDescription.getText();
        System.out.println("Description is "+productDescriptionText);
        Assert.assertTrue(productDescriptionText.contains("The Samsung Galaxy S6 is powered by 1.5GHz octa-core Samsung Exynos " +
                        "7420 processor and it comes with 3GB of RAM. " +
                        "The phone packs 32GB of internal storage cannot be expanded."),
                "Expected 'Welcome' text, but found: " + productDescriptionText);

        addToCartButton.click();
        System.out.println("Clicked addtocart");
        String alertText = DriverUtils.handleAlertIfPresent(driver);
        System.out.println("alert handled");
        Assert.assertTrue(alertText.contains("Product added."),
                "Expected 'Samsung galaxy s6' text, but found: " + productTitleText);
        System.out.println("Assertion");
        cartLink.click();

    }

}


