package las3007.task1.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import las3007.task1.factory.common.WebDriverFactory;
import las3007.task1.factory.home.HomePage;
import las3007.task1.factory.search.AdvancedTitleSearchPage;
import las3007.task1.factory.search.DisplayPerPageEnum;
import las3007.task1.factory.search.OrderByEnum;
import las3007.task1.factory.search.SearchGroupsEnum;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class SearchSteps {

    private WebDriver driver;
    private HomePage homePage;
    private AdvancedTitleSearchPage advancedTitleSearchPage;

    @Before
    public void openBrowser() {
        driver = WebDriverFactory.createWebDriver();
    }

    @After
    public void closeBrowser() {
        WebDriverFactory.closeWebDriver();
    }

    @Given("the user visits IMDB home page")
    public void the_user_visits_the_home_page() {
        homePage = new HomePage(driver);
        homePage.visitPage();
    }

    @When("the user searches for (\\S+)$")
    public void user_search(String searchPhrase) {
        homePage.enterTextAndSearch(searchPhrase);
    }

    @When("the user visits Advanced Search")
    public void user_visits_advanced_advanced() {
        homePage.visitAdvancedSearch();
    }

    @When("the user visits Advanced Search Movies, TV and Video Games")
    public void user_visits_advanced_advanced_title() {
        advancedTitleSearchPage = new AdvancedTitleSearchPage(driver);
        advancedTitleSearchPage.visitPage();
    }

    @When("the user clicks (.+)$")
    public void click_check_box(String checkBox) { //mmm may improve this to be a list or else do another one as a list
        SearchGroupsEnum groupsEnum = SearchGroupsEnum.getSearchGroup(checkBox);
        if (groupsEnum != null) {
            List<SearchGroupsEnum> groupsEnumList = new ArrayList<>();
            groupsEnumList.add(groupsEnum);
            advancedTitleSearchPage.clickCheckBox(groupsEnumList);
        } else {
            fail("Invalid check box selected");
        }
    }

    @When("the user selects display (\\d+) per page$")
    public void set_display_qty(int displayPerPage) {
        DisplayPerPageEnum displayEnum = DisplayPerPageEnum.getDisplayPerPage(displayPerPage);
        if (displayEnum != null) {
            advancedTitleSearchPage.setQtyPerPage(displayEnum);
        } else {
            fail("Invalid display per page selected");
        }
    }

    @When("the user selects sorted by (.+)$")
    public void set_order_by(String orderBy) {
        OrderByEnum orderByEnum = OrderByEnum.getOrderBy(orderBy);
        if (orderByEnum != null) {
            advancedTitleSearchPage.setOrderBy(orderByEnum);
        } else {
            fail("Invalid Order by selected");
        }
    }

    @When("the user presses the search button")
    public void press_search_button() {
        advancedTitleSearchPage.clickSearchButton();
    }

    @Then("the search message should be (.+)$")
    public void the_user_should_get_error_messages(String resultPhrase) {
        assertTrue(Objects.equals(homePage.getSearchResultMsg(), resultPhrase));
    }

    @Then("results are displayed")
    public void results_are_displayed() {
        assertTrue(homePage.simpleSearchResultsDisplayed());
    }

    @Then("results are not displayed")
    public void results_are_not_displayed() {
        assertFalse(homePage.simpleSearchResultsDisplayed());
    }

    @Then("a list should be displayed")
    public void list_should_be_displayed() {
        assertTrue(advancedTitleSearchPage.areResultsDisplayed());
    }

    @Then("a list should be displayed in (.+)$")
    public void check_if_valid_order(String orderBy) {
        OrderByEnum orderByEnum = OrderByEnum.getOrderBy(orderBy);
        if (orderByEnum != null) {
            assertTrue(advancedTitleSearchPage.isValidOrder(orderByEnum));
        } else {
            fail("Invalid Order by selected");
        }

    }
}