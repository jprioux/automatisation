package squash.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author qtran - created on 22/07/2020
 */
public class AbstractPage {
    private final WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        // Ca ne sert à rien mais on ne sait jamais si un jour cette classe se mets à avoir des éléments ...
        PageFactory.initElements(driver, this);
    }

    public final WebDriver getDriver() {
        return driver;
    }

    public void waitUntilDocumentReadyStateComplete(WebDriverWait wait) {
        final ExpectedCondition<Boolean> complete = wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete");
        wait.until(complete);
    }

    public boolean checkElementWithId(String id){
        return getDriver().findElements(By.id(id)).size() != 0;
    }
}
