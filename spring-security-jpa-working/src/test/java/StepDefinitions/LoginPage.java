package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginPage {

    WebDriver web =null;
    @Given("the user is on login page")
    public void the_user_is_on_login_page() {
        System.getProperty("webdriver.chrome.driver");
        web=new ChromeDriver();
        web.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
    @When("the user enters the credential")
    public void the_user_enters_the_credential() {
        web.navigate().to("http://localhost:5000/login");

    }
    @Then("Authenticate the user credentials")
    public void authenticate_the_user_credentials() {
        web.findElement(By.name("username")).sendKeys("Mani");
        web.findElement(By.name("password")).sendKeys("Manisree@98");



    }
    @Then("Upon click on login button")
    public void upon_click_on_login_button() {
        web.findElement(By.name("login")).sendKeys(Keys.ENTER);
    }
    @Then("User is navigated towards the search page")
    public void user_is_navigated_towards_the_page_page() {
        web.getPageSource().contains("login");
//            Assertions.fail();

            // web.getPageSource().contains("154");
            web.close();
            web.quit();
        }

    }