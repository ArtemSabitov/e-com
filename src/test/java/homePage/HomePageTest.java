package homePage;

import init.BaseTest;
import io.qameta.allure.Description;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import practice.automationtesting.in.utils.Logger;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class HomePageTest extends BaseTest {
    @Test
    @Description("4. Home page - Arrivals-Images-Description")
    public void descriptionTest() {
        int expectedArrivals = 3;

        Logger.testLog("3) Click on Shop Menu");
        homePage.getHeader().openShopMenu();
        Logger.testLog("4) Now click on Home menu button");
        shopPage.getHeader().openHomePage();

        Logger.testLog("6) The Home page must contains only " + expectedArrivals + " Arrivals");
        List<WebElement> newArrivalsProducts = homePage.getNewArrivalsProducts();

        assertThat("Count of New Arrivals should be " + expectedArrivals
                , expectedArrivals == newArrivalsProducts.size());

        Logger.testLog("7) Now click the image in the Arrivals");
        homePage.clickOnElement(newArrivalsProducts.get(0).findElement(By.cssSelector("img")));

        Logger.testLog("9) Image should be clickable and should navigate to next page where user can add that book to his basket");
        productPage.verifyThatAddToCartButtonDisplayed();

        Logger.testLog("10) Click on Description tab for the book you clicked on");
        productPage.openProductDescription();

        Logger.testLog("11) There should be a description regarding that book the user clicked on");

        //It's hard to detect that description contains reference to the product.
        //After fast investigation I discovered that product Category always included into product description

        String description = productPage.getProductDescription();
        String productCategory = productPage.getProductCategory();
        assertThat(String.format("Description should contains product category. \nDescription: '%s'.\nCategory: '%s'", description, productCategory)
                , StringUtils.containsIgnoreCase(description, productCategory));
    }

    @Test
    @Description("5. Home page - Arrivals-Images-Reviews")
    public void reviewsTest() {
        int expectedArrivals = 3;

        Logger.testLog("3) Click on Shop Menu");
        homePage.getHeader().openShopMenu();
        Logger.testLog("4) Now click on Home menu button");
        shopPage.getHeader().openHomePage();

        //Points 6-9 looks useless because this functional already tested in descriptionTest()
        Logger.testLog("6) The Home page must contains only " + expectedArrivals + " Arrivals");
        List<WebElement> newArrivalsProducts = homePage.getNewArrivalsProducts();

        assertThat("Count of New Arrivals should be " + expectedArrivals
                , expectedArrivals == newArrivalsProducts.size());

        Logger.testLog("7) Now click the image in the Arrivals");
        homePage.clickOnElement(newArrivalsProducts.get(0).findElement(By.cssSelector("img")));

        Logger.testLog("9) Image should be clickable and should navigate to next page where user can add that book to his basket");
        productPage.verifyThatAddToCartButtonDisplayed();

        Logger.testLog("10) Click on Reviews tab for the book you clicked on");
        productPage.openProductReviews();

        Logger.testLog("11) There should be a Reviews regarding that book the user clicked on");
        String reviewTitle = productPage.getProductReviewTitle();
        String productName = productPage.getProductName();
        assertThat(String.format("Review Title should contains product name. \nReview Title: '%s'.\nProduct Name: '%s'", reviewTitle, productName)
                , StringUtils.containsIgnoreCase(reviewTitle, productName));
    }
}
