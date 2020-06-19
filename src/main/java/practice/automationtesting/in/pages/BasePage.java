package practice.automationtesting.in.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import practice.automationtesting.in.pages.blocks.Header;
import practice.automationtesting.in.utils.Logger;
import practice.automationtesting.in.utils.Waiters;

import java.time.Duration;

public class BasePage {


        public Waiters wait;
    protected WebDriver driver;
    protected static final long EXPLICITLY_TIME_TO_WAIT_IN_SECONDS = Long.valueOf(System.getProperty("exp_wait", String.valueOf(30)));

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new Waiters(getDefaultFluentWait());
    }

    private FluentWait<WebDriver> getDefaultFluentWait() {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(EXPLICITLY_TIME_TO_WAIT_IN_SECONDS))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(StaleElementReferenceException.class, NoSuchElementException.class);
    }

    public void clickOnElement(By by) {
        Logger.info("Click on element " + by);
        wait.forElementToBeClickable(by).click();
    }

    public void clickOnElement(WebElement el) {
        Logger.info("Click on element " + el);
        wait.forElementToBeClickable(el).click();
    }

    public void sendKeysToElement(By by, CharSequence... keys) {
        Logger.info("Type text: '" + keys + "' into element " + by);
        wait.forElementToBeClickable(by).sendKeys(keys);
    }

    public String getTextFromElement(By by) {
        Logger.info("Getting text from element " + by);
        String text = wait.forElementToBeDisplay(by).getText().trim();
        Logger.info("Element text is: " + text);
        return text;
    }
}
