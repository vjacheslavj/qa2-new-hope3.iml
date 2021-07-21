package pageobject.delfi.pages.tvnet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class CommentPage {
    private final By TITLE = By.xpath(".//h1[@class = 'article-headline']");
    private final By COMMENTS = By.xpath(".//span[contains(@class, 'article-comments-heading__count')]");

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    private BaseFunc2 baseFunc2;

    public CommentPage(BaseFunc2 baseFunc2) {
        this.baseFunc2 = baseFunc2;
    }

    public String getTitle() {
        LOGGER.info("Getting comment page title");
        return baseFunc2.getText(TITLE);
    }

    public int getCommentsCount() {
        LOGGER.info("Getting comment page comments count");
        if (baseFunc2.findElements(COMMENTS).isEmpty()) {
            return 0;
        } else {

        }
        String commentsCountToParse = baseFunc2.getText(COMMENTS);
        return Integer.parseInt(commentsCountToParse);
    }

}
