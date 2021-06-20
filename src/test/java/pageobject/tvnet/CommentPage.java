package pageobject.tvnet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class CommentPage {
    private final By PAGE_TITLE = By.xpath(".//h1[@class = 'article-headline']");
    private final By PAGE_COMMENTS = By.xpath(".//span[contains(@class, 'article-comments-heading__count')]");

    private final Logger LOGGER = LogManager.getLogger(this.getClass());
}
