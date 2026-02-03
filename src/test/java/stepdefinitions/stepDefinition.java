package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;
import utils.DriverUtils;

import java.util.Random;

public class stepDefinition {

    WebDriver driver = DriverUtils.getDriver();
    HomePage page = new HomePage(driver);
    ProductPage ProdPage = new ProductPage(driver);
    CartPage CartPage = new CartPage(driver);

    String userName = "";
    String password = "";

    public static String generateRandomName() {
        String[] firstNames = {
                "Alexam", "Sam", "Jordan", "Taylor", "Chris",
                "Jamie", "Morgan", "Riley", "Casey", "Avery"
        };

        String[] lastNames = {
                "Storm", "Walker", "Fox", "Blaze", "Knight",
                "Rivera", "Stone", "Hawk", "Shadow", "Nova"
        };

        Random random = new Random();
        return firstNames[random.nextInt(firstNames.length)] + " "
                + lastNames[random.nextInt(lastNames.length)];
    }

    public String getUserName() {
        if (userName == null || userName.isEmpty()) {
            userName = "user" + System.currentTimeMillis();
        }
        return userName;
    }

    public String getPassword() {
        if (password == null || password.isEmpty()) {
            password = "admin" + System.currentTimeMillis();
        }
        return password;
    }

    @Given("the user is a registered user")
    public void register() {

        userName = getUserName();
        password = getPassword();

        page.register(userName, password);

        String alertText = DriverUtils.handleAlertIfPresent(driver);
        Assert.assertTrue(alertText != null && alertText.toLowerCase().contains("successful"),
                "Expected alert to contain 'successful', but found: " + alertText);

        // ✅ persist
        DriverUtils.saveUser(userName, password);
    }




    @When("the user logs in")
    public void login() {

        String user = DriverUtils.getValue("username");
        String pass = DriverUtils.getValue("password");

        page.Login(user, pass);
    }


    @And("adds a product to the cart")
    public void addProduct() {
        ProdPage.selectProduct();
    }

    @Then("the user checks out successfully")
    public void checkoutFlow() {
        CartPage.validateAndPlaceOrder();

        String randomName = generateRandomName();
        CartPage.checkout(
                randomName,
                "India",
                "Delhi",
                "982739384726",
                "12",
                "2040"
        );
    }
}
