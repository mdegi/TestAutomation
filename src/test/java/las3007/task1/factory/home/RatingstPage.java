package las3007.task1.factory.home;

import static las3007.task1.factory.common.CommonFunctions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RatingstPage {

    private WebDriver driver;

    public RatingstPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void filterRatings(RatingFilterEnum ratingFilter) {
        switch (ratingFilter) {
            case YEARS_ALL:
                //Not Implemented yet
                break;
            case YEARS_18_LESS:
                performClick(driver, By.cssSelector(".title-ratings-sub-page > table:nth-child(14) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(3) > div:nth-child(2) > a:nth-child(1)"));
                break;
            case YEARS_18_TO_29:
                //Not Implemented yet
                break;
            case YEARS_30_TO_44:
                //Not Implemented yet
                break;
            case YEARS_45_PLUS:
                //Not Implemented yet
                break;
        }
    }

    public boolean isFilterTotalCorrect() {
        int totalUsers = 0;

        List<WebElement> elements = driver.findElements(By.cssSelector("div.allText:nth-child(4)"));
        if (elements.size() > 0) {
            try {
                totalUsers = Integer.parseInt(elements.get(0).getText().substring(0, elements.get(0).getText().indexOf(" ")));
            } catch (NumberFormatException e) {}
        }

        int votes = 0;
        for (int x = 1; x < 12; x++){
            elements = driver.findElements(
                    By.cssSelector(".title-ratings-sub-page > table:nth-child(7) > tbody:nth-child(1) > tr:nth-child(" + x + ") > td:nth-child(3) > div:nth-child(1) > div:nth-child(1)"));
            if (elements.size() > 0) {
                try {
                    votes += Integer.parseInt(elements.get(0).getText());
                } catch (NumberFormatException e) {
                }
            }
        }
        return totalUsers == votes;
    }

}
