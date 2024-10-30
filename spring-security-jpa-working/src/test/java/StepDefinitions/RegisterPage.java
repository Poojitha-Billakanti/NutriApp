package StepDefinitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class RegisterPage {

    WebDriver web =null;
    @Given("the user is on register page")
    public void the_user_is_on_register_page() {
        System.getProperty("webdriver.chrome.driver");
        web=new ChromeDriver();
        web.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
    @When("the user enters the register detail")
    public void the_user_enters_the_register_detail() {
        web.navigate().to("http://localhost:5000/register");
    }
    @Then("Validate register details enter by user")
    public void validate_register_details_enter_by_user() {
        web.findElement(By.name("username")).sendKeys("Mahesh");
        web.findElement(By.name("username")).sendKeys("Mahesh");
        web.findElement(By.name("email")).sendKeys("mahesh@gmail.com");
        web.findElement(By.name("password")).sendKeys("Mahesh@98");
    }
    @Then("click on register button")
    public void click_on_register_button() {
        web.findElement(By.name("register")).sendKeys(Keys.ENTER);
    }
    @Then("User is registered and navigated to login")
    public void user_is_registered_and_navigated_to_login() {
        web.getPageSource().contains("login");
        // web.getPageSource().contains("154");
        web.close();
        web.quit();
    }
}
