package practice.automationtesting.in.pages;

import org.openqa.selenium.WebDriver;
import practice.automationtesting.in.pages.blocks.Header;

public class ShopPage extends BasePage {
    private Header header = new Header(driver);

    public ShopPage(WebDriver driver) {
        super(driver);
    }

    public Header getHeader() {
        return header;
    }
}
