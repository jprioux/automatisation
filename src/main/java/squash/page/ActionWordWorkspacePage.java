package squash.page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ActionWordWorkspacePage extends AbstractPage {

    public ActionWordWorkspacePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void findExistingAction(WebDriverWait wait, String existingAction, int actionWordId, int libraryId) {
        WebElement actionWordLibrary = getDriver().findElement(By.xpath("//*[@id=\"ActionWordLibrary-"+libraryId+"\"]/ins"));
        wait.until(ExpectedConditions.elementToBeClickable(actionWordLibrary));
        actionWordLibrary.click();
        List<WebElement> actionWord = getDriver().findElements(By.xpath("//*[@id=\"ActionWord-"+actionWordId+"\"]/a"));
        Assert.assertFalse(actionWord.isEmpty());
        System.out.println("L'action \"" + existingAction + "\" existe bien dans la librairie " + libraryId);
        WebElement actionWordElement = actionWord.get(0);
        wait.until(ExpectedConditions.elementToBeClickable(actionWordElement));
        actionWordElement.click();
    }

}
