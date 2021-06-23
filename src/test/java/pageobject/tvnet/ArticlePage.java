package pageobject.tvnet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class ArticlePage {
    private final By TITLE = By.xpath(".//h1[@itemprop = 'headline name']");
    private final By COMMENTS = By.xpath(".//span[contains(@class, 'article-share__item--count')]");
    private final By COMMENTS_BTN = By.xpath(".//img[@src = '/v5/img/icons/comment-v2.svg']");

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    private BaseFunc2 baseFunc2;

    public ArticlePage(BaseFunc2 baseFunc2) {
        this.baseFunc2 = baseFunc2;
    }

    public String getTitle() {
        LOGGER.info("Getting article title");
        return baseFunc2.getText(TITLE);
    }

    public int getCommentsCount() {
        LOGGER.info("Getting article comments count");

        if (baseFunc2.findElements(COMMENTS).isEmpty()) {
            return 0;
        }else {
            String commentsCountToParse = baseFunc2.getText(COMMENTS);
            commentsCountToParse = commentsCountToParse.substring(1, commentsCountToParse.length() - 1);
            return Integer.parseInt(commentsCountToParse);
        }
    }

    public void openCommentsPage() {
        LOGGER.info("Opening article comments page");
        baseFunc2.click(COMMENTS_BTN);
    }
}
