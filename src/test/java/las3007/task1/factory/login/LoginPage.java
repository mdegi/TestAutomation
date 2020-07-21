package las3007.task1.factory.login;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class LoginPage {

    private int counter;
    private boolean assertIndicator;

    public static final String URL = "https://www.imdb.com/ap/signin?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.imdb.com%2Fregistration%2Fap-signin-handler%2Fimdb_us&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=imdb_us&openid.mode=checkid_setup&siteState=eyJvcGVuaWQuYXNzb2NfaGFuZGxlIjoiaW1kYl91cyIsInJlZGlyZWN0VG8iOiJodHRwczovL3d3dy5pbWRiLmNvbS8_cmVmXz1sb2dpbiJ9&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&tag=imdbtag_reg-20";

    private WebDriver driver;

    @FindBy(id="signInSubmit")
    private WebElement signInSubmitWebElement;

    @FindBy(id="ap_email")
    private WebElement emailWebElement;

    @FindBy(id="ap_password")
    private WebElement passwordWebElement;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void visitPage() {
        driver.get(URL);
    }

    public void enterCredentialsAndLogIn(String userName, String pwd) {
        emailWebElement.clear();
        passwordWebElement.clear();

        emailWebElement.click();
        emailWebElement.sendKeys(userName);

        passwordWebElement.click();
        passwordWebElement.sendKeys(pwd);

        signInSubmitWebElement.click();
    }

    public boolean isUserLoggedIn() {
        List<WebElement> elements = driver.findElements(By.cssSelector("span.imdb-header__account-toggle--logged-in"));
        if (elements.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public String getErrorMessage() {
        String errorMsg = null;
        List<WebElement> elements = driver.findElements(By.cssSelector(".a-list-item"));
        if (!elements.isEmpty()) {
            errorMsg =  elements.get(0).getText();
        }
        return errorMsg;
    }

    public boolean checkValidMessages(List<String> messages) {
        counter = 0;
        assertIndicator = true;

        List<WebElement> elements = driver.findElements(By.cssSelector(".a-definition-list"));
        if (!elements.isEmpty()) {
            messages.forEach(
                    message -> {
                        counter++;
                        List<WebElement> listItem = driver.findElements(By.cssSelector(".a-definition-list > li:nth-child(" + counter + ") > span:nth-child(1)"));
                        if (listItem.isEmpty() || !listItem.get(0).getText().equals(message)) {
                            assertIndicator = false;
                        }
                    }
            );
        }
        return assertIndicator;
    }

    public void logOut() {
        String cssSelector = ".swiper-slide:nth-child(2) .ipc-lockup-overlay__gradient";
        List<WebElement> elements = driver.findElements(By.cssSelector(cssSelector));

        {
            if (elements.size() > 0) {
                WebElement element = driver.findElement(By.cssSelector(cssSelector));
                Actions builder = new Actions(driver);
                builder.moveToElement(element).perform();
            } else {
                fail("Element not found swipe-slide cssSelector: " + cssSelector);
            }
        }

        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }


        cssSelector = ".ipc-icon:nth-child(2)";
        try {
            elements = driver.findElements(By.cssSelector(cssSelector));
            if (elements.size() > 0) {
                driver.findElement(By.cssSelector(cssSelector)).click();

                String linkText = "Sign out";
                if (driver.findElement(By.linkText(linkText)).isDisplayed()) {
                    driver.findElement(By.linkText(linkText)).click();
                }
            } else {
                fail("");
                System.out.println("Element cssSelector not found: " + cssSelector);
            }
        } catch (NoSuchElementException e) {
            fail("Element not found Exception: " + e.getMessage());
        }
    }
}
