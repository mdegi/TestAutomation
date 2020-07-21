package las3007.task1.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import las3007.task1.factory.account.GenderEnum;
import las3007.task1.factory.account.PersonalDetailsPage;
import las3007.task1.factory.home.HomePage;
import las3007.task1.factory.common.WebDriverFactory;
import org.openqa.selenium.WebDriver;


import static org.junit.jupiter.api.Assertions.*;

public class AccountSteps {

    private WebDriver driver;
    private PersonalDetailsPage personalDetailsPage;
    private HomePage homePage;

    @Before
    public void openBrowser() {
        driver = WebDriverFactory.createWebDriver();
        personalDetailsPage = new PersonalDetailsPage(driver);
        homePage = new HomePage(driver);
    }

    @After
    public void closeBrowser() {
        WebDriverFactory.closeWebDriver();
    }

    @When("the user visits Account settings")
    public void visit_account_settings() {
        homePage.visitAccountSettings();
    }

    @When("the user visits Personal details")
    public void the_user_visits_personal_details() {
        personalDetailsPage.visitPersonalDetails();
    }

    @When("the user changes gender to (.+)$")
    public void user_changes_gender(String gender) {
        GenderEnum genderEnum = GenderEnum.getGender(gender);
        if (genderEnum != null) {
            personalDetailsPage.setGender(genderEnum);
        } else {
            fail("Invalid Gender selected");
        }
    }

    @When("the user presses the submit button")
    public void press_submit() {
        personalDetailsPage.submit();
    }

    @Then("the gender is (.+)$")
    public void the_gender_is(String gender) {
        GenderEnum genderEnum = GenderEnum.getGender(gender);
        if (genderEnum != null) {
            assertTrue(personalDetailsPage.isGender(genderEnum));
        } else {
            fail("Invalid Gender recorder");
        }
    }


}