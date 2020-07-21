package las3007.task1.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import las3007.task1.factory.common.WebDriverFactory;
import las3007.task1.factory.home.RatingFilterEnum;
import las3007.task1.factory.home.RatingstPage;
import las3007.task1.factory.home.WatchListPage;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RatingsSteps {

    private WebDriver driver;
    private WatchListPage watchListPage;
    private RatingstPage ratingsPage;

    @Before
    public void initVars() {
        driver = WebDriverFactory.createWebDriver();
        watchListPage = new WatchListPage(driver);
        ratingsPage = new RatingstPage(driver);
    }

    @After
    public void closeBrowser() {
        WebDriverFactory.closeWebDriver();
    }

    @When("the user enters ([1-9]|10) star rating for (.+)$")
    public void user_sets_rating(int stars, String title) {
        watchListPage.setRating(title, stars);
    }

    @When("the user cancels rating for (.+)$")
    public void user_cancels_rating(String title) {
        watchListPage.setRating(title, 0);
    }

    @When("the user visits public rating for (.+)$")
    public void visit_public_rating(String title) {
        watchListPage.visitPublicRating(title);
    }

    @When("the user selects (All Ages|<18|18-29|30-44|45+) rating$")
    public void filter_rating(String ratingFilter) {
        RatingFilterEnum ratingEnum = RatingFilterEnum.getRatingFilter(ratingFilter);
        if (ratingEnum != null) {
            List<RatingFilterEnum> ratingEnumList = new ArrayList<>();
            ratingEnumList.add(ratingEnum);
            ratingsPage.filterRatings(ratingEnum);
        } else {
            fail("Invalid check box selected");
        }
    }

    @Then("the title has ([0-9]|10) star rating$")
    public void check_rating(int stars) {
        assertTrue(watchListPage.getRating() == stars);
    }

    @Then("(.+) is Displayed for (.+)$")
    public void user_rating_displayed(String text, String title) {
        assertTrue(watchListPage.isPageDisplayed(text, title));
    }

    @Then("total users should tally individual users")
    public void check_total_voters_filtered() {
        assertTrue(ratingsPage.isFilterTotalCorrect());
    }

}