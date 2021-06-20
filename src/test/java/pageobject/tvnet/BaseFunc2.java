package pageobject.tvnet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseFunc2 {
    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    WebDriver driver;
    WebDriver wait;

    public BaseFunc2(){
        LOGGER.info("Starting web browser");
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
    }

    public void openPage(String url) {
        LOGGER.info("Opening page by URL: " + url);

        if (!url.startsWith("http://") && !url.startsWith("https://")){
            url = "http://" + url;
        }
        driver.get(url);
    }

    public void click(By locator) {
        LOGGER.info("Clicking on element by:" + locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
}