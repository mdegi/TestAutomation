package las3007.task1.factory.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static  las3007.task1.factory.common.CommonFunctions.*;

public class PersonalDetailsPage {

    private WebDriver driver;

    @FindBy(id="genderSel")
    private WebElement genderSelWebElement;

    public PersonalDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void visitPersonalDetails() {
        performClick(driver, By.cssSelector("div.article:nth-child(1) > ul:nth-child(2) > li:nth-child(3) > a:nth-child(1)"));
    }

    public void setGender(GenderEnum gender) {
        genderSelWebElement.click();
        new Select(genderSelWebElement).selectByVisibleText(gender.getDescription());
        performClick(driver, By.xpath("//option[@value='m']"));
    }

    public void submit() {
        performClick(driver, By.cssSelector(".pretty_btn"));
    }

    public boolean isGender(GenderEnum genderEnum) {
        boolean isSelected = false;

        List<WebElement> elements = new Select(genderSelWebElement).getAllSelectedOptions();
        if (elements.size() > 0) {
            String selectedGender = elements.get(0).getText();
            if (selectedGender.equals(genderEnum.getDescription())) {
                isSelected = true;
            }
        }
        return isSelected;
    }

}
