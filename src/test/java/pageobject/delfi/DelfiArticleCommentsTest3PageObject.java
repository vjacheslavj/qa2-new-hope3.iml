package pageobject.delfi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pageobject.BaseFunc;
import pageobject.delfi.pages.ArticlePage;
import pageobject.delfi.pages.CommentPage;
import pageobject.delfi.pages.HomePage;


public class DelfiArticleCommentsTest3PageObject {

    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private  final  int ARTICLE_ID = 4;
    private BaseFunc baseFunc;

    @Test
    public void titleAndCommentsCountCheck() {
        LOGGER.info("This test is checking titles and comments count on home/article/comments pages");

        baseFunc = new BaseFunc();
        baseFunc.openPage("http://delfi.lv");

        //---------------------------HOME PAGE-----------------------------------------------------------
        HomePage homePage = new HomePage(baseFunc);
        homePage.acceptCoocies();

       String homePageTitle = homePage.getTitle(ARTICLE_ID);
       int homePageCommentsCount = homePage.GetCommentsCount(ARTICLE_ID);


       ArticlePage articlePage = homePage.openArticle(ARTICLE_ID);

//        //-------------------------ARTICLE PAGE----------------------------------------------------------
        String articlePageTitle = articlePage.getTitle();
        int articlePageCommentsCount = articlePage.getCommentsCount();

        Assertions.assertEquals(homePageTitle, articlePageTitle, "Wrong title!");
        Assertions.assertEquals(homePageCommentsCount, articlePageCommentsCount, "Wrong comments count!");

        articlePage.openCommentsPage();
//        LOGGER.info("Opening comments page");
//        driver.findElement(ARTICLE_PAGE_COMMENTS_BTN).click();
//
//        //-------------------------COMMENTS PAGE---------------------------------------------------------
        CommentPage commentPage = new CommentPage(baseFunc);

        String commentPageTitle = commentPage.getTitle();
        int commentPageAnonimUsersCommentsCount = commentPage.getAnonimUsersCommentsCount();
        int commentPageRegistratedCommentsCount = commentPage.getRegistratedUsersCommentsCount();

//        LOGGER.info("Getting article title and comments count");
//        String commentPageTitle = driver.findElement(COMMENT_PAGE_TITLE).getText();
//        int commentPageRegistratedUsersCommentCount = getCommentsCount(COMMENT_REGISTRATED_USERS_PAGE_COMMENTS);
//        int commentPageAnonimUsersCommentCount = getCommentsCount(COMMENT_ANONIM_USERS_PAGE_COMMENTS);
        LOGGER.info("Title is: " + commentPageTitle + "and comments count is: " + commentPageAnonimUsersCommentsCount + commentPageRegistratedCommentsCount);

        Assertions.assertEquals(homePageTitle, commentPageTitle + " ", "Wrong title!");
        Assertions.assertEquals(homePageCommentsCount, commentPageAnonimUsersCommentsCount + commentPageRegistratedCommentsCount, "Wrong comments count!");

    }


    @AfterEach
    public void closeBrowser() {

        baseFunc.closeBrowser();
    }
}