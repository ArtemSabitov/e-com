package init;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import practice.automationtesting.in.pages.HomePage;
import practice.automationtesting.in.pages.ProductPage;
import practice.automationtesting.in.pages.ShopPage;
import practice.automationtesting.in.utils.Logger;
import utils.CustomTestListener;

@Listeners(CustomTestListener.class)
public class BaseTest {
    private WebDriver driver;
    protected static final String WELLCOME_URL = "http://practice.automationtesting.in/";

    protected HomePage homePage;
    protected ShopPage shopPage;
    protected ProductPage productPage;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeSuite
    public void init() {
        System.setProperty("webdriver.chrome.driver", "/Users/my/Documents/Work/Automation/driver/chrome_driver/chromedriver");
        Logger.info("Init START.");
        driver = new ChromeDriver();
        driver.get(WELLCOME_URL);
        createPages();
        Logger.info("Init FINISH.");
    }

    @AfterSuite
    public void finish() {
        driver.quit();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult testResult) {
        resetBrowser();
    }

    public void resetBrowser() {
        Logger.info("Clear cookies");
        if (!driver.getCurrentUrl().contains(WELLCOME_URL)) {
            driver.manage().deleteAllCookies();
            driver.get(WELLCOME_URL);
        }
        driver.manage().deleteAllCookies();
        driver.get(WELLCOME_URL);
    }

    private void createPages() {
        homePage = new HomePage(driver);
        shopPage = new ShopPage(driver);
        productPage = new ProductPage(driver);
    }
}
