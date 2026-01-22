package stepdefinitions;

import io.cucumber.java.en.Given;
import net.bytebuddy.utility.RandomString;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import tech.grasshopper.excel.report.util.RandomStringGenerator;
import utils.DriverUtils;

import java.util.Random;

public class stepDefinition {
    WebDriver driver = DriverUtils.getDriver();
    LoginPage page = new LoginPage(driver);

    @Given("user is able to register in")
    public String Register() {
        String username = "user" + System.currentTimeMillis()+"kohli";
        page.Register("Raam", "Sithaa");
        String alertText = DriverUtils.handleAlertIfPresent(driver);
        System.out.println(alertText);

        return final String UserName = username ;

    }

    @Given("user is able to log in")
    public void login() {

        page.Login("Raam", "Sithaa");
        String alertText = DriverUtils.handleAlertIfPresent(driver);
        System.out.println(alertText);
    }

}
