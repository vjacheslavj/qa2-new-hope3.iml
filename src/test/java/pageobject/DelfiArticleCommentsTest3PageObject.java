package pageobject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pageobject.pages.ArticlePage;
import pageobject.pages.BaseFunc;
import pageobject.pages.HomePage;


public class DelfiArticleCommentsTest3PageObject {

    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private  final  int ARTICLE_ID = 5;
    private BaseFunc baseFunc;

    @Test
    public void titleAndCommentsCountCheck() {
        LOGGER.info("This test is checking titles and comments count on home/article/comments pages");

        baseFunc = new BaseFunc();
        baseFunc.openPage("http://delfi.lv");

        //---------------------------HOME PAGE-----------------------------------------------------------
        HomePage homePage = new HomePage(baseFunc);
        homePage.acceptCoocies();

        homePage.getTitle(ARTICLE_ID);
        homePage.getCommentsCount = homePage.GetCommentsCount(ARTICLE_ID);

        homePage.openArticle(ARTICLE_ID);

//        //-------------------------ARTICLE PAGE----------------------------------------------------------
        String articlePageTitle = articlePage.getTitle();
        int articlePageCommentsCount = articlePage.getCommentsCount()

        Assertions.assertEquals(homePageTitle, articlePageTitle, "Wrong title!");
        Assertions.assertEquals(homePageCommentsCount, articlePageCommentsCount, "Wrong comments count!");

        articlePage.openCommentsPage();
//        LOGGER.info("Opening comments page");
//        driver.findElement(ARTICLE_PAGE_COMMENTS_BTN).click();
//
//        //-------------------------COMMENTS PAGE---------------------------------------------------------
//        LOGGER.info("Getting article title and comments count");
//        String commentPageTitle = driver.findElement(COMMENT_PAGE_TITLE).getText();
//        int commentPageRegistratedUsersCommentCount = getCommentsCount(COMMENT_REGISTRATED_USERS_PAGE_COMMENTS);
//        int commentPageAnonimUsersCommentCount = getCommentsCount(COMMENT_ANONIM_USERS_PAGE_COMMENTS);
//        LOGGER.info("Title is: " + commentPageTitle + "and comments count is: " + commentPageRegistratedUsersCommentCount + commentPageAnonimUsersCommentCount);
//
//        Assertions.assertEquals(homePageTitle, commentPageTitle + " ", "Wrong title!");
        Assertions.assertEquals(homePageCommentsCount, commentPageRegistratedUsersCommentCount + commentPageAnonimUsersCommentCount, "Wrong comments count!");

    }


    @AfterEach
    public void closeBrowser() {
        baseFunc.closeBrowser();
    }
}