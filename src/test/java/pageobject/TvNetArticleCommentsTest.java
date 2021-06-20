package pageobject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.tvnet.BaseFunc2;
import pageobject.tvnet.HomePage;

import java.util.List;

public class TvNetArticleCommentsTest {

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    private WebDriver driver;

    @Test
    public void titleAndCommentsCountCheck() {
        LOGGER.info("This test is checking titles and comments count on home/article/comments pages");

//        LOGGER.info("Setting driver location");
//        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
//
//        LOGGER.info("Opening browser window");
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
        BaseFunc2 baseFunc2 = new BaseFunc2();

//        LOGGER.info("Opening Home Page");
//        driver.get("https://www.tvnet.lv/");
        baseFunc2.openPage("https://www.tvnet.lv/");

        //---------------------------HOME PAGE-----------------------------------------------------------
        HomePage homePage = new HomePage(baseFunc2);
        homePage.acceptCookies();

//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        LOGGER.info("Waiting for accept cookies button");
//        wait.until(ExpectedConditions.elementToBeClickable(ACCEPT_COOKIE_BTN));
//
//        LOGGER.info("Accept cookies");
//        driver.findElement(ACCEPT_COOKIE_BTN).click();

        List<WebElement> articles = driver.findElements(HOME_PAGE_ARTICLE);
        WebElement article = articles.get(1);

        LOGGER.info("Getting article title and comments count");
        String homePageTitle = article.findElement(HOME_PAGE_TITLE).getText();
        int homePageCommentsCount = getCommentsCount(article, HOME_PAGE_COMMENTS);
        LOGGER.info("Title is: " + homePageTitle + "and comments count is: " + homePageCommentsCount);

        LOGGER.info("Opening article");
        article.findElement(HOME_PAGE_TITLE).click();

        //-------------------------ARTICLE PAGE----------------------------------------------------------
        LOGGER.info("Getting article title and comments count");
        String articlePageTitle = driver.findElement(ARTICLE_PAGE_TITLE).getText();
        int articlePageCommentsCount = getCommentsCount(ARTICLE_PAGE_COMMENTS);
        LOGGER.info("Title is: " + articlePageTitle + "and comments count is: " + articlePageCommentsCount);

        Assertions.assertEquals(homePageTitle, articlePageTitle + " " + "(" + homePageCommentsCount + ")", "Wrong title!");
        Assertions.assertEquals(homePageCommentsCount, articlePageCommentsCount, "Wrong comments count!");

        LOGGER.info("Opening comments page");
        driver.findElement(ARTICLE_PAGE_COMMENTS_BTN).click();
        //-------------------------COMMENTS PAGE---------------------------------------------------------
        LOGGER.info("Getting article title and comments count");
        String commentPageTitle = driver.findElement(COMMENT_PAGE_TITLE).getText();
        int commentPageCommentCount = getCommentsCount(COMMENT_PAGE_COMMENTS);
        LOGGER.info("Title is: " + commentPageTitle + "and comments count is: " + commentPageCommentCount);

        Assertions.assertEquals(homePageTitle, commentPageTitle + " " + "(" + homePageCommentsCount + ")", "Wrong title!");
        Assertions.assertEquals(homePageCommentsCount, commentPageCommentCount, "Wrong comments count!");

    }

    private int getCommentsCount(By locator) {
        int commentsCount = 0;

        if (!driver.findElements(locator).isEmpty()) {
            commentsCount = parseInt(driver.findElement(locator));
        }

        return commentsCount;
    }

    private int getCommentsCount(WebElement we, By locator) {
        int commentsCount = 0;

        if (!we.findElements(locator).isEmpty()) {
            commentsCount = removeBrackets(we.findElement(locator));
        }

        return commentsCount;
    }

    private int removeBrackets(WebElement we) {
        String commentsCountText = we.getText();
        commentsCountText = commentsCountText.substring(1, commentsCountText.length() - 1); // (36) -> 36 (String)
        return Integer.parseInt(commentsCountText); // 36 (String) -> 36 (int)
    }

    private int parseInt(WebElement we) {
        String commentsCountText = we.getText();
        return Integer.parseInt(commentsCountText); // 36 (String) -> 36 (int)
    }

    @AfterEach
    public void closeBrowser() {
        driver.close();
    }
}