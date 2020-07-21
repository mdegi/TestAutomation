package las3007.task1.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import las3007.task1.factory.common.WebDriverFactory;
import las3007.task1.factory.login.LoginPage;
import mt.com.AppConfig;
import mt.com.Credentials;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LoginSteps {

    private final String WRONG_PASSWORD = "WRONG_PASSWORD";

    private WebDriver driver;
    private LoginPage login;

    private AnnotationConfigApplicationContext ctx;
    private Credentials credentials;

    @Before
    public void init() {
        if (ctx == null) {
            ctx = new AnnotationConfigApplicationContext();
            ctx.register(AppConfig.class);
            ctx.refresh();
            credentials = ctx.getBean(Credentials.class);
        }
        driver = WebDriverFactory.createWebDriver();
    }

    @After
    public void closeBrowser() {
        WebDriverFactory.closeWebDriver();
    }

    @Given("the user visits IMDB login page")
    public void the_user_visits_the_IMDB_page() {
        login = new LoginPage(driver);
        login.visitPage();
    }

    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        login = new LoginPage(driver);
        login.visitPage();
        login.enterCredentialsAndLogIn(credentials.getWebsiteUserName(), credentials.getWebsitePassword());
    }

    @When("the user enters valid credentials")
    public void the_user_enters_valid_credentials() {
        login.enterCredentialsAndLogIn(credentials.getWebsiteUserName(), credentials.getWebsitePassword());
    }

    @When("the user enters an invalid password")
    public void the_user_enters_invalid_password() {
        login.enterCredentialsAndLogIn(credentials.getWebsiteUserName(), WRONG_PASSWORD);
    }

    @When("the user enters empty credentials")
    public void the_user_enters_an_empty_credentials() {
        login.enterCredentialsAndLogIn("","");
    }

    @When("the user selects Sign Out")
    public void doLogOut() {
        login.logOut();
        assertFalse(login.isUserLoggedIn());
    }

    @Then("the user should be Logged in")
    public void the_user_should_be_logged_in_IMDB() {
        assertTrue(login.isUserLoggedIn());
    }

    @Then("the user should be not Logged in")
    public void the_user_should_not_be_logged_in_IMDB() {
        assertFalse(login.isUserLoggedIn());
    }

    @Then("the message should be {string}")
    public void the_user_should_get_an_error_message(String errorMessage) {
        assertEquals(errorMessage, login.getErrorMessage());
    }

    @Then("the messages should be")
    public void the_user_should_get_error_messages(DataTable dataTable) {
        List<String> messages = dataTable.asList();
        assertTrue(login.checkValidMessages(messages));
    }

}