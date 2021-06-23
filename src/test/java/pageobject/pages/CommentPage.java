package pageobject.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class CommentPage {
    private final By TITLE = By.xpath(".//h1[@class = 'article-title']");
    private final By REGISTRATED_USERS_PAGE_COMMENTS = By.xpath(".//li[@class = 'as-link show-reg']/span/span");
    private final By ANONIM_USERS_PAGE_COMMENTS = By.xpath(".//li[@class = 'as-link is-active show-anon']/span/span");

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    private BaseFunc baseFunc;

    public CommentPage(BaseFunc baseFunc){
        this.baseFunc = baseFunc;
    }
    public String getTitle() {
       LOGGER.info("Getting comment page title");
       return baseFunc.getText(TITLE);
    }

    public int getRegistratedUsersCommentsCount() {
        LOGGER.info("Getting comment page registrated users comments count");
        if (baseFunc.findElements(REGISTRATED_USERS_PAGE_COMMENTS).isEmpty()) {
            return 0;
        } else {

            String commentsCountToParse = baseFunc.getText(REGISTRATED_USERS_PAGE_COMMENTS);
            commentsCountToParse = commentsCountToParse.substring(1, commentsCountToParse.length() - 1);
            return Integer.parseInt(commentsCountToParse);
        }
    }
    public int getAnonimUsersCommentsCount() {
            if (baseFunc.findElements(ANONIM_USERS_PAGE_COMMENTS).isEmpty()) {
                return 0;
            } else {

                LOGGER.info("Getting comment page anonim users comments count");
                String commentsCountToParse2 = baseFunc.getText(ANONIM_USERS_PAGE_COMMENTS);
                commentsCountToParse2 = commentsCountToParse2.substring(1, commentsCountToParse2.length() - 1);
                return Integer.parseInt(commentsCountToParse2);
            }
    }

}
