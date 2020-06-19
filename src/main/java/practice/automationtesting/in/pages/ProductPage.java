package practice.automationtesting.in.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {
    private static final By ADD_TO_CART_BUTTON = By.cssSelector(".single_add_to_cart_button");

    private static final By PRODUCT_NAME = By.cssSelector("h1[itemprop='name']");

    private static final By PRODUCT_DESCRIPTION_TAB = By.cssSelector(".description_tab");
    private static final By PRODUCT_DESCRIPTION = By.id("tab-description");
    private static final By PRODUCT_CATEGORY = By.cssSelector(".posted_in > a[rel='tag']");

    private static final By PRODUCT_REVIEWS_TAB = By.cssSelector(".reviews_tab");
    private static final By BE_FIRST_REVIEWER_TITLE = By.cssSelector("#review_form #reply-title");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void verifyThatAddToCartButtonDisplayed() {
        try {
            wait.forElementToBeDisplay(ADD_TO_CART_BUTTON);
        } catch (TimeoutException e) {
            throw new IllegalStateException("Add To Cart Button should be displayed!", e);
        }
    }

    @Step("Open Product Description")
    public void openProductDescription() {
        clickOnElement(PRODUCT_DESCRIPTION_TAB);
    }

    public String getProductDescription() {
        return getTextFromElement(PRODUCT_DESCRIPTION);
    }

    public String getProductCategory() {
        return getTextFromElement(PRODUCT_CATEGORY);
    }


    @Step("Open Product Reviews")
    public void openProductReviews() {
        clickOnElement(PRODUCT_REVIEWS_TAB);
    }

    public String getProductReviewTitle() {
        return getTextFromElement(BE_FIRST_REVIEWER_TITLE);
    }

    public String getProductName() {
        return getTextFromElement(PRODUCT_NAME);
    }
}
