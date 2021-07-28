package pageobject.tickets.pages;

import org.openqa.selenium.By;
import pageobject.BaseFunc;

public class SuccesPage {
    private final By SUCCESS_TXT = By.xpath(".//div[@class = 'finalTxt']");
    private BaseFunc baseFunc;

    public SuccesPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public String getMessage() {
        return baseFunc.getText(SUCCESS_TXT);
    }
}
