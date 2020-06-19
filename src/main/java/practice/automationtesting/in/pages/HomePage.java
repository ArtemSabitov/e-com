package practice.automationtesting.in.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import practice.automationtesting.in.pages.blocks.Header;

import java.util.List;

public class HomePage extends BasePage {
    private Header header = new Header(driver);
    private static final By NEW_ARRIVALS_PRODUCTS = By.cssSelector(".product");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public Header getHeader() {
        return header;
    }

    public List<WebElement> getNewArrivalsProducts() {
        return wait.forAllElementsToBeDisplay(NEW_ARRIVALS_PRODUCTS);
    }
}
