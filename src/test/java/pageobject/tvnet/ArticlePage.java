package pageobject.tvnet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class ArticlePage {
    private final By PAGE_TITLE = By.xpath(".//h1[@itemprop = 'headline name']");
    private final By PAGE_COMMENTS = By.xpath(".//span[contains(@class, 'article-share__item--count')]");
    private final By PAGE_COMMENTS_BTN = By.xpath(".//img[@src = '/v5/img/icons/comment-v2.svg']");

    private final Logger LOGGER = LogManager.getLogger(this.getClass());
}
