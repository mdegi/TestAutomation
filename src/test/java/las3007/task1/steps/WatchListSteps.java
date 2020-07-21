package las3007.task1.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import las3007.task1.factory.common.WebDriverFactory;
import las3007.task1.factory.home.HomePage;
import las3007.task1.factory.home.WatchListPage;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

public class WatchListSteps {

    private WebDriver driver;
    private HomePage homePage;
    private WatchListPage watchListPage;

    @Before
    public void initVars() {
        driver = WebDriverFactory.createWebDriver();
        homePage = new HomePage(driver);
        watchListPage = new WatchListPage(driver);
    }

    @After
    public void closeBrowser() {
        WebDriverFactory.closeWebDriver();
    }

    @When("the user visits Watchlist")
    public void user_visits_watchList() {
        homePage.visitWatchList();
    }

    @When("the user selects edit")
    public void user_selects_edit() {
        watchListPage.editList();
    }

    @When("the user enters (.+) in search box$")
    public void user_entersText_in_search_box(String searchPhrase) {
        watchListPage.searchFor(searchPhrase);
    }

    @When("the user selects the first entry displayed")
    public void user_selects_first_entry() {
        watchListPage.selectFirstSearchItem();
    }

    @When("the user selects the DONE button")
    public void user_selects_done() {
        watchListPage.selectDone();
    }

    @When("the user selects the first entry checkbox")
    public void click_on_first_entry_checkbox() {
        watchListPage.clickFirstCheckBox();
    }

    @When("the user selects DELETE")
    public void user_selects_delete() {
        watchListPage.clickDelete();
    }

    @When("the user confirms DELETE")
    public void user_confirms_delete() {
        watchListPage.confirmDelete();
    }

    @When("the user switches view$")
    public void user_switches_view () {
        watchListPage.switchView();
    }

    @When("the user selects REFINE")
    public void user_selects_refine() {
        watchListPage.refineView();
    }

    @Then("(.+) shows in list view$")
    public void selection_shows_in_list_view(String title) {
        assertTrue(watchListPage.isEntryListedInListView(title));
    }

    @Then("(.+) does not show in list view$")
    public void selection_not_show_in_list_view(String title) {
        assertFalse(watchListPage.isEntryListedInListView(title));
    }

    @Then("total entries is (\\d)$")
    public void check_total_entries(int entries) {
        assertEquals(entries, watchListPage.getTotalEntriesListed());
    }

    @Then("refined list is showing")
    public void check_refined_list() {
        assertTrue(watchListPage.isRefinedModeEnabled());
    }

}