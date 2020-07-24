package squash.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author qtran - created on 22/07/2020
 */
public class TestCaseInfoPage extends AbstractPage {
    public TestCaseInfoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void fillActionWordInput(WebDriverWait wait, String input) {
        WebElement actionWordInput = getDriver().findElement(By.id("action-word-input"));
        wait.until(ExpectedConditions.elementToBeClickable(actionWordInput));
        actionWordInput.sendKeys(input);
    }


    public void addNewKeywordTestStep(WebDriverWait wait) {
        WebElement keywordTestStepAddButton = getDriver().findElement(By.id("add-keyword-test-step-btn"));
        wait.until(ExpectedConditions.elementToBeClickable(keywordTestStepAddButton));
        keywordTestStepAddButton.click();
    }

}
