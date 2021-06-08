import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class MyFirstHomework {

    private final By FIRST_ARTICLE_NAME = By.xpath(".//span[@class = 'list-article__headline']");
    private final By ACCEPT_COOKIES_BTN = By.xpath(".//button[@mode = 'primary']");
    private final By FIRST_ARTICLE_COMMENT_NAME = By.xpath(".//img[@src='/v5/img/icons/comment-v2.svg']");
    private final By ALL_ARTICLES_NAMES = By.xpath(".//span[@itemprop = 'headline name']");
    private final By RUS_LANGUAGE_BUTTON = By.xpath(".//div[@class = 'menu-items menu-items--top']/div[5]");
    private final By ALL_COMMENTS_NAMES = By.xpath(".//span[@itemprop='url']");
    private final By TVNET_LOGO = By.xpath(".//img[@src='https://f.pmo.ee/logos/4133/7b1236dab95abca45083322781760e97.svg']");

    @Test
    public void firstHomeworkExerciseOne() {
        //WebDriver - browser window
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        WebDriver browserWindow = new ChromeDriver();
        browserWindow.manage().window().maximize();
        browserWindow.get("http://tvnet.lv");

        browserWindow.findElement(ACCEPT_COOKIES_BTN).click();
        browserWindow.findElement(FIRST_ARTICLE_NAME).click();
        browserWindow.findElement(FIRST_ARTICLE_COMMENT_NAME).click();

    }

    @Test
    public void firstHomeworkExerciseTwo() {
        //WebDriver - browser window
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        WebDriver browserWindow = new ChromeDriver();
        browserWindow.manage().window().maximize();
        browserWindow.get("http://tvnet.lv");

        browserWindow.findElement(ACCEPT_COOKIES_BTN).click();
        String firstArticleName = browserWindow.findElement(FIRST_ARTICLE_NAME).getText();
        String firstArticleNameWithoutNumbers = firstArticleName
                .replaceAll("[0-9]", "" ).replaceAll("[()]", "");
        System.out.println(firstArticleNameWithoutNumbers);
        browserWindow.quit();
    }

    @Test
    public void allArticlesNamesPrintWithoutComments() {
        //WebDriver - browser window
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        WebDriver browserWindow = new ChromeDriver();
        browserWindow.manage().window().maximize();
        browserWindow.get("http://tvnet.lv");

        browserWindow.findElement(ACCEPT_COOKIES_BTN).click();
        List<WebElement> titles = browserWindow.findElements(ALL_ARTICLES_NAMES);

        for (int i = 0; i < titles.size(); i++) {
            if (!titles.get(i).getText().isEmpty()) {
                System.out.println(i + ": " + titles.get(i).getText()
                        .replaceAll("[0-9]", "" ).replaceAll("[()]", ""));
            }
        }
    }

    @Test
    public void allArticlesNamesPrint() {
        //WebDriver - browser window
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        WebDriver browserWindow = new ChromeDriver();
        browserWindow.manage().window().maximize();
        browserWindow.get("http://tvnet.lv");

        browserWindow.findElement(ACCEPT_COOKIES_BTN).click();
        List<WebElement> titles = browserWindow.findElements(ALL_ARTICLES_NAMES);

        for (int i = 0; i < titles.size(); i++) {
            if (!titles.get(i).getText().isEmpty()) {
                System.out.println(i + ": " + titles.get(i).getText());
            }
        }
    }
}



