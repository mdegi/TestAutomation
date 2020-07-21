package las3007.task1.factory.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static  las3007.task1.factory.common.CommonFunctions.*;

public class HomePage {

    public static final String URL = "https://www.imdb.com/";

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void visitPage() {
        driver.get(URL);
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public void enterTextAndSearch(String searchPhrase) {
        WebElement searchFieldWebElement = driver.findElement(By.id("suggestion-search"));

        searchFieldWebElement.clear();
        searchFieldWebElement.click();
        searchFieldWebElement.sendKeys(searchPhrase);

        performClick(driver, By.cssSelector("svg.ipc-icon.ipc-icon--magnify"));
    }

    public String getSearchResultMsg() {
        String searchMsg = "";

        List<WebElement> elements = driver.findElements(By.className("findHeader"));
        if (elements.size() > 0) {
            searchMsg = elements.get(0).getText();
        }
        return searchMsg;
    }

    public boolean simpleSearchResultsDisplayed() {
        boolean displayedResults = false;

        List<WebElement> elements = driver.findElements(By.cssSelector("div.findSection:nth-child(4) > h3:nth-child(1)"));
        if (elements.size() > 0) {
            displayedResults = true;
        }
        return displayedResults;
    }

    public void visitAdvancedSearch() {
        performClick(driver, By.cssSelector("svg.ipc-icon.ipc-icon--arrow-drop-down.navbar__flyout__button-pointer"));
        performClick(driver, By.linkText("Advanced Search"));
    }

    public void visitAccountSettings() {
        performClick(driver, By.cssSelector("label.ipc-button.ipc-button--single-padding.ipc-button--default-height.ipc-button--core-baseAlt.ipc-button--theme-baseAlt.ipc-button--on-textPrimary.ipc-text-button.navbar__flyout__text-button-after-mobile.navbar__user-menu-toggle__button > div.ipc-button__text > svg.ipc-icon.ipc-icon--arrow-drop-down.navbar__flyout__button-pointer"));
        performClick(driver, By.linkText("Account settings"));
    }

    public void visitWatchList() {
        performClick(driver, By.xpath("//nav[@id='imdbHeader']/div[2]/div[4]/a/div"));
//        By link = By.xpath("//nav[@id='imdbHeader']/div[2]/div[4]/a/div");
//        ExpectedCondition<WebElement> linkClickable = ExpectedConditions.elementToBeClickable(link);
//
//        WebElement result = new WebDriverWait(driver, 10).until(linkClickable);
//        result.click();
    }
}
