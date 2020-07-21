package las3007.task1.factory.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import static las3007.task1.factory.common.CommonFunctions.*;

public class WatchListPage {

    private WebDriver driver;

    public WatchListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void editList() {
        performClick(driver, By.xpath("//div[@id='center-1-react']/div/div/div/div/a/span[2]"));
    }

    public void searchFor(String searchPhrase) {
        ExpectedCondition<WebElement> linkClickable = ExpectedConditions.elementToBeClickable(By.id("add-to-list-search"));
        WebElement result = new WebDriverWait(driver, 10).until(linkClickable);
        result.click();

        result.sendKeys(searchPhrase);
    }

    public void selectFirstSearchItem(){
        performClick(driver, By.xpath("//div[@id='add-to-list-search-results']/a/div/span"));
    }

    public void selectDone() {
        performClick(driver, By.xpath("//button[@type='button']"));
    }

    public boolean isEntryListedInListView(String title) {
        boolean listed = false;

        List<WebElement> elements = driver.findElements(By.linkText(title));
        if (elements.size() > 0) {
            listed = true;
        }
        return listed;
    }

    public int getTotalEntriesListed() {
        int totalEntries = 0;

        List<WebElement> elements;
        boolean validRow = true;
        while (validRow) {
            elements = driver.findElements(By.cssSelector("div.lister-item:nth-child(" + (totalEntries + 1) + ") > div:nth-child(1) > div:nth-child(2) > h3:nth-child(1) > a:nth-child(1)"));
            if (elements.size() > 0) {
                totalEntries++;
            } else {
                validRow = false;
            }
        }
        return totalEntries;
    }

    public void clickFirstCheckBox(){
        //Check Box not being clicked here .....
        //driver.get("https://www.imdb.com/list/ls080000439/edit?ref_=wl_edt_pwr");
//        driver.findElement(By.xpath("//div[@id='main']/div[2]/div[3]/div/span/div/label")).click();
        performClick(driver, By.cssSelector(".lister-item-edit > div:nth-child(1) > label:nth-child(2)"));

        //performClick(By.xpath("//div[@id='main']/div[2]/div[3]/div/span/div/label"));
        //driver.findElement(By.xpath("//div[@id='main']/div[2]/div[3]/div/span/div/label")).click();
        //driver.findElement(By.xpath("//div[@id='main']/div[2]/div[3]/div[5]/div/div/div/label")).click();
        //performClick(By.xpath("//div[@id='main']/div[2]/div[3]/div[5]/div/div/div/label"));
        //performClick(By.cssSelector(".lister-edit-total-selection > div:nth-child(1) > label:nth-child(2)"));
    }

    public void clickDelete() {
        performClick(driver, By.id("delete_items"));
    }

    public void confirmDelete() {
        performClick(driver, By.xpath("//input[@value='DELETE']"));
    }

    public void switchView(){
        performClick(driver, By.xpath("//div[@id='center-1-react']/div/div[2]/div/div/div[2]/button/span"));
    }

    public void refineView() {
        performClick(driver, By.xpath("//div[@id='center-1-react']/div/div[2]/div/div/div[3]/button"));
    }

    public boolean isRefinedModeEnabled() {
        boolean refinedListShowing = false;
        List<WebElement> elements = driver.findElements(By.cssSelector("li.faceter-category:nth-child(1) > span:nth-child(1)"));
        if (elements.size() > 0) {
            refinedListShowing = true;
        }
        return refinedListShowing;
    }

    public void setRating(String title, int stars) {
        performClick(driver, By.linkText(title));
        performClick(driver, By.xpath("//div[@id='star-rating-widget']/div/button/span"));

        if (stars == 0) {
            performClick(driver, By.cssSelector(".star-rating-delete"));
        } else {
            performClick(driver, By.cssSelector(".star-rating-stars > a:nth-child(" + stars + ")"));
        }
    }

    public int getRating() {
        int starsRating = 0;

        List<WebElement> elements = driver.findElements(By.cssSelector(".star-rating-button > button:nth-child(1) > span:nth-child(2)"));
        if (elements.size() > 0) {
            try {
                starsRating = Integer.parseInt(elements.get(0).getText());
            } catch (NumberFormatException e) {
                // value remains 0
            }
        }
        return starsRating;
    }

    public void visitPublicRating(String title){
        performClick(driver, By.linkText(title));
        performClick(driver, By.cssSelector("span.small"));
    }

    public boolean isPageDisplayed(String text, String title) {

        boolean pageDisplayed = false;
        List<WebElement> elements = driver.findElements(By.cssSelector(".parent > h3:nth-child(1) > a:nth-child(1)"));
        if (elements.size() > 0) {
            if (elements.get(0).getText().equals(title)) {
                elements = driver.findElements(By.cssSelector("h1.header"));
                if (elements.size() > 0) {
                    if (elements.get(0).getText().equals(text)) {
                        pageDisplayed = true;
                    }
                }
            }
        }

        return pageDisplayed;
    }

}
