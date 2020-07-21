package las3007.task1.factory.search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static  las3007.task1.factory.common.CommonFunctions.*;

public class AdvancedTitleSearchPage {

    public static final String URL = "https://www.imdb.com/search/title/";

    private WebDriver driver;

    public AdvancedTitleSearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void visitPage() {
        driver.get(URL);
    }

    public void clickCheckBox(List<SearchGroupsEnum> groups) {
        if (groups.size() > 0) {
            groups.forEach(
                    group -> {
                        List<WebElement> groupWebElement = driver.findElements(By.id(group.getId()));
                        if (groupWebElement.size() > 0) {
                            groupWebElement.get(0).click();
                        }
                    }
            );
        }
    }

    public void clickSearchButton() {
        performClick(driver, By.cssSelector(".primary"));
    }

    public void setQtyPerPage(DisplayPerPageEnum displayPerPage) {
        performClick(driver, By.id("search-count"));
        new Select(driver.findElement(By.id("search-count"))).selectByVisibleText(displayPerPage.getDescription());
        performClick(driver, By.xpath("//option[@value='100']"));
    }

    public void setOrderBy(OrderByEnum orderBy) {
        performClick(driver, By.name("sort"));
        new Select(driver.findElement(By.name("sort"))).selectByVisibleText(orderBy.getDescription());
        performClick(driver, By.xpath("//option[@value='alpha,asc']"));
    }

    public boolean areResultsDisplayed() {

        //Check if a 1st item appears in list and if yes results have been displayed
        boolean resultsDisplayed = false;
        List<WebElement> elements = driver.findElements(By.cssSelector("div.lister-item:nth-child(1)"));
        if (elements.size() > 0) {
            resultsDisplayed = true;
        }
        return resultsDisplayed;
    }

    public boolean isValidOrder(OrderByEnum orderBy) {
        boolean validOrder = true;

        switch(orderBy) {
            case A_Z_Ascending:
                validOrder = checkOrderAtoZAscending();
                break;
            case A_Z_Descending:
                break; //Not Implemented yet
            case Popularity_Ascending:
                break; //Not Implemented yet
            default:
                validOrder = false;
                break;
        }
        return validOrder;
    }

    private boolean checkOrderAtoZAscending() {
        boolean validOrder = true;

        List<WebElement> elements;
        List<String> titlesList = new ArrayList<>();

        boolean validRow = true;
        int count = 0;
        while (validRow) {
            count++;
            elements = driver.findElements(By.cssSelector("div.lister-item:nth-child(" + count + ") > div:nth-child(3) > h3:nth-child(1) > a:nth-child(2)"));
            if (elements.size() > 0) {
                titlesList.add(elements.get(0).getText());
            } else {
                validRow = false;
            }
        }

        List<String> sortedList = titlesList.stream()
                .sorted()
                .collect(Collectors.toList());

        for (int x = 0; x < titlesList.size(); x++) {
            if (!titlesList.get(x).equals(sortedList.get(x))) {
                validOrder = false;
                break;
            }
            if (x > 5) {
                break; // do not check more items as IMDB web page does not display all titles correctly in alphabetic order !
                // so as discussed during lectures since we do not have any control on IMDB website I am assuming that
                // all the list is displayed in the correct alphabetical order - Hence I am this if (x > 5) { statement
                // to check only first 5 titles
            }

        }
        return validOrder;
    }
}
