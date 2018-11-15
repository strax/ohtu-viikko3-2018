package ohtu;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;

import ohtu.domain.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("^login is selected$")
    public void login_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();          
    }

    @Given("^command new user is selected$")
    public void command_new_user_is_selected() throws Throwable {
        driver.get(baseUrl);
        driver.findElement(By.linkText("register new user")).click();
    }

    @Given("^user with username \"([^\"]*)\" with password \"([^\"]*)\" is successfully created$")
    public void user_with_username_with_password_is_successfully_created(String username, String password) throws Throwable {
        driver.get(baseUrl);
        driver.findElement(By.linkText("register new user")).click();
        signUpWith(username, password);
    }

    @Given("^user with username \"([^\"]*)\" and password \"([^\"]*)\" is tried to be created$")
    public void user_with_username_and_password_is_tried_to_be_created(String username, String password) throws Throwable {
        driver.get(baseUrl);
        driver.findElement(By.linkText("register new user")).click();
        signUpWith(username, password);
    }

    @When("^a new account is registered with username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void a_new_account_is_registered_with_username_and_password(String username, String password) throws Throwable {
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("passwordConfirmation")).sendKeys(password);
        driver.findElement(By.name("signup")).submit();
    }

    @When("^a valid username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation are entered$")
    public void a_valid_username_and_password_and_matching_password_confirmation_are_entered(String username, String password) throws Throwable {
        signUpWith(username, password);
    }

    @When("^username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation are entered$")
    public void username_and_password_and_matching_password_confirmation_are_entered(String username, String password) throws Throwable {
        signUpWith(username, password);
    }

    @When("^username \"([^\"]*)\" and password \"([^\"]*)\" and password confirmation \"([^\"]*)\" are entered$")
    public void username_and_password_and_password_confirmation_are_entered(String username, String password, String passwordConfirmation) throws Throwable {
        signUpWith(username, password, passwordConfirmation);
    }

    @Then("^the user \"([^\"]*)\" is not created$")
    public void the_user_is_not_created(String username) throws Throwable {
        var user = Main.dao.findByName(username);
        assertNull(user);
    }

    @Then("^error \"([^\"]*)\" is reported$")
    public void error_is_reported(String error) throws Throwable {
        assertTrue(driver.getPageSource().contains(error));
    }

    @When("^username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_and_password_are_given(String username, String password) throws Throwable {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    }

    @Then("^system will respond \"([^\"]*)\"$")
    public void system_will_respond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }

    @Then("^a new user is created with username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void a_new_user_is_created_with_username_and_password(String username, String password) throws Throwable {
        var user = Main.dao.findByName(username);
        assertNotNull(user);
        assertEquals(user.getPassword(), password);
    }
    
    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_correct_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^correct username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }
    
    @Then("^user is logged in$")
    public void user_is_logged_in() throws Throwable {
        pageHasContent("Ohtu Application main page");
    }
    
    @Then("^user is not logged in and error message is given$")
    public void user_is_not_logged_in_and_error_message_is_given() throws Throwable {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }     
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    }

    private void signUpWith(String username, String password) {
        signUpWith(username, password, password);
    }

    private void signUpWith(String username, String password, String passwordConfirmation) {
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("passwordConfirmation")).sendKeys(passwordConfirmation);
        driver.findElement(By.name("signup")).submit();
    }
}
