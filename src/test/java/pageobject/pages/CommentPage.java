package pageobject.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class CommentPage {
    private final By TITLE = By.xpath(".//h1[@class = 'article-title']");
    private final By REGISTRATED_USERS_PAGE_COMMENTS = By.xpath(".//li[@class = 'as-link show-reg']/span/span");
    private final By ANONIM_USERS_PAGE_COMMENTS = By.xpath(".//li[@class = 'as-link is-active show-anon']/span/span");

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

}
