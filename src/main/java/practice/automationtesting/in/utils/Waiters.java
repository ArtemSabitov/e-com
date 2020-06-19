package practice.automationtesting.in.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.List;

public class Waiters {
    private FluentWait<WebDriver> fluentWait;

    public Waiters(FluentWait<WebDriver> fluentWait) {
        this.fluentWait = fluentWait;
    }

    public WebElement forElementToBeClickable(By by) {
        return fluentWait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public WebElement forElementToBeClickable(WebElement el) {
        return fluentWait.until(ExpectedConditions.elementToBeClickable(el));
    }

    public WebElement forElementToBeDisplay(By by) {
        return fluentWait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public List<WebElement> forAllElementsToBeDisplay(By by) {
        return fluentWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }
}
