package las3007.task1.factory.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonFunctions {

    public static void performClick(WebDriver driver, By link) {
        ExpectedCondition<WebElement> linkClickable = ExpectedConditions.elementToBeClickable(link);
        WebElement result = new WebDriverWait(driver, 30).until(linkClickable);
        result.click();
    }
}
