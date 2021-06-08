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

import java.util.List;

public class DelfiArticleCommentsTest2 {
    private final By ACCEPT_COOKIE_BTN = By.xpath(".//button[@mode = 'primary']");
    private final By HOME_PAGE_TITLE = By.xpath(".//h1[contains(@class, 'headline__title')]");
    private final By HOME_PAGE_COMMENTS = By.xpath(".//a[contains(@class, 'comment-count')]");
    private final By HOME_PAGE_ARTICLE = By.tagName("article");

    private final By ARTICLE_PAGE_TITLE = By.xpath(".//h1[contains(@class, 'text-size-md-30')]");
    private final By ARTICLE_PAGE_COMMENTS = By.xpath(".//a[contains(@class, 'text-size-md-28')]");
    private final By ARTICLE_PAGE_COMMENTS_BTN = By.xpath(".//a[contains(@class, 'text-size-19')]");

    private final By COMMENT_PAGE_TITLE = By.xpath(".//h1[@class = 'article-title']");
    private final By COMMENT_REGISTRATED_USERS_PAGE_COMMENTS = By.xpath(".//li[@class = 'as-link show-reg']/span/span");
    private final By COMMENT_ANONIM_USERS_PAGE_COMMENTS = By.xpath(".//li[@class = 'as-link is-active show-anon']/span/span");

    private final Logger LOGGER = LogManager.getLogger(DelfiArticleCommentsTest2.class);

    private WebDriver driver;

    @Test
    public void titleAndCommentsCountCheck() {
        LOGGER.info("This test is checking titles and comments count on home/article/comments pages");

        LOGGER.info("Setting driver location");
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");

        LOGGER.info("Opening browser window");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        LOGGER.info("Opening Home Page");
        driver.get("http://delfi.lv");

        //---------------------------HOME PAGE-----------------------------------------------------------
        WebDriverWait wait = new WebDriverWait(driver, 10);
        LOGGER.info("Waiting for accept cookies button");
        wait.until(ExpectedConditions.elementToBeClickable(ACCEPT_COOKIE_BTN));

        LOGGER.info("Accept cookies");
        driver.findElement(ACCEPT_COOKIE_BTN).click();

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

        Assertions.assertEquals(homePageTitle, articlePageTitle, "Wrong title!");
        Assertions.assertEquals(homePageCommentsCount, articlePageCommentsCount, "Wrong comments count!");

        LOGGER.info("Opening comments page");
        driver.findElement(ARTICLE_PAGE_COMMENTS_BTN).click();

        //-------------------------COMMENTS PAGE---------------------------------------------------------
        LOGGER.info("Getting article title and comments count");
        String commentPageTitle = driver.findElement(COMMENT_PAGE_TITLE).getText();
        int commentPageRegistratedUsersCommentCount = getCommentsCount(COMMENT_REGISTRATED_USERS_PAGE_COMMENTS);
        int commentPageAnonimUsersCommentCount = getCommentsCount(COMMENT_ANONIM_USERS_PAGE_COMMENTS);
        LOGGER.info("Title is: " + commentPageTitle + "and comments count is: " + commentPageRegistratedUsersCommentCount + commentPageAnonimUsersCommentCount);

        Assertions.assertEquals(homePageTitle, commentPageTitle + " ", "Wrong title!");
        Assertions.assertEquals(homePageCommentsCount, commentPageRegistratedUsersCommentCount + commentPageAnonimUsersCommentCount, "Wrong comments count!");

    }

    private int getCommentsCount(By locator) {
        int commentsCount = 0;

        if (!driver.findElements(locator).isEmpty()) {
            commentsCount = removeBrackets(driver.findElement(locator));
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

    @AfterEach
    public void closeBrowser() {
        driver.close();
    }
}