package practice.automationtesting.in.pages.blocks;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import practice.automationtesting.in.pages.BasePage;
import practice.automationtesting.in.utils.Logger;

public class Header extends BasePage {
    private static final By siteLogo = By.cssSelector("header #site-logo > a");
    private static final String navigationMenuXpathTemplate = "//header//ul[@id='main-nav']//a[text()='%MENU_NAME%']";

    public Header(WebDriver driver) {
        super(driver);
    }

    @Step("Open menu {menuName}")
    private void openMenuByName(String menuName) {
        Logger.info("Open menu " + menuName);
        clickOnElement(By.xpath(navigationMenuXpathTemplate.replaceFirst("%MENU_NAME%", menuName)));
    }

    public void openShopMenu() {
        openMenuByName("Shop");
    }

    public void openMyAccountMenu() {
        openMenuByName("My Account");
    }

    @Step("Open HomePage")
    public void openHomePage() {
        clickOnElement(siteLogo);
    }
}
