package pageobject.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class BaseFunc {
    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    WebDriver driver;
    WebDriverWait wait;

    public BaseFunc() {
        LOGGER.info("Starting web browser");
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 10);
    }

    public void openPage(String url) {
        LOGGER.info("Opening page by URL: " + url);

        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }

        driver.get(url);
    }

    public void click(By locator) {
        LOGGER.info("Clicking on element by: " + locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void  click(WebElement element) {
        LOGGER.info("Clicking on web element");
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public List<WebElement> findElements(WebElement parent, By child) {
        LOGGER.info("Getting all child elements");
        return  parent.findElements(child)
    }


    public List<WebElement> findElements(By locator) {
        LOGGER.info("Getting list of elements by: " + locator);
        return driver.findElements(locator);
    }
    public String getText (WebElement parent, By child) {
        LOGGER.info("Getting text for child element by locator");
        wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(parent, child));
        return parent.findElement(child).getText(ExpectedConditions.presenceOfNestedElementLocatedBy(parent, child));
    }

    public  String getText(By locator) {
        LOGGER.info("Getting text from web element");
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

    }

    public void closeBrowser() {
        LOGGER.info("Closing browser window");
        if (driver != null) {
            driver.close();
        }
    }
}
