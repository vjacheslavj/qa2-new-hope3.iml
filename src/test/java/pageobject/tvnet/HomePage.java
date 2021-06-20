package pageobject.tvnet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class HomePage {
    private final By ACCEPT_COOKIE_BTN = By.xpath(".//button[@mode = 'primary']");
    private final By PAGE_TITLE = By.xpath(".//span[contains(@class, 'list-article__headline')]");
    private final By PAGE_COMMENTS = By.xpath(".//span[contains(@class, 'list-article__comment section-font-color')]");
    private final By PAGE_ARTICLE = By.tagName("article");

    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private BaseFunc2 baseFunc2;

    public HomePage(BaseFunc2 baseFunc2) {
        this.baseFunc2 = baseFunc2;
    }

    public void acceptCookies() {
        LOGGER.info("Accepting cookies");
        baseFunc2.click(ACCEPT_COOKIE_BTN);
    }
}
