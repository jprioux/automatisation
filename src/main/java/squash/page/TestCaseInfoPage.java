package squash.page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.List;

/**
 * @author qtran - created on 22/07/2020
 */
public class TestCaseInfoPage extends AbstractPage {

    private static int lastRowNumber = 1;

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

    public boolean findExistingAction(WebDriverWait wait, String existingAction) {
        return true;
    }

    public void initActionInTestStep(WebDriverWait wait, String actionToModify) {
        // init test case by creating the test step
        fillActionWordInput(wait, actionToModify);
        removeSuggestionsAndAddNewKeywordTestStep(wait);
        // modify the created one, the last test step in the table
        lastRowNumber = getTableRowSize("//*[@id=\"keyword-test-step-table\"]/tbody/tr/td[1]");
        WebElement cellToModify = getDriver().findElement(By.xpath("//*[@id=\"keyword-test-step-table\"]/tbody/tr["+lastRowNumber+"]/td[3]"));
        wait.until(ExpectedConditions.elementToBeClickable(cellToModify));
        cellToModify.click();
    }

    public void modifyActionInKeywordTestStep(WebDriverWait wait, String existingAction) {
        WebElement cellToModifyInput = getDriver().findElement(By.xpath("//*[@id=\"keyword-test-step-table\"]/tbody/tr["+lastRowNumber+"]/td[3]/form/input"));
        wait.until(ExpectedConditions.elementToBeClickable(cellToModifyInput));
        cellToModifyInput.clear();
        cellToModifyInput.sendKeys(existingAction);
    }

    public void confirmKeywordTestStepModification(WebDriverWait wait) {
        WebElement keywordTestStepModifyButton = getDriver().findElement(By.xpath("//*[@id=\"keyword-test-step-table\"]/tbody/tr["+lastRowNumber+"]/td[3]/form/button[1]"));
        wait.until(ExpectedConditions.elementToBeClickable(keywordTestStepModifyButton));
        keywordTestStepModifyButton.click();
    }

    public void checkActionInKeywordTestStep(WebDriverWait wait, String updatedAction) {
        WebElement cellToModify = getDriver().findElement(By.xpath("//*[@id=\"keyword-test-step-table\"]/tbody/tr["+lastRowNumber+"]/td[3]"));
        Assert.assertEquals(cellToModify.getText(), updatedAction);
        // delete the test step in order to reset database
        WebElement deleteKeywordTestStepButton = getDriver().findElement(By.xpath("//*[@id=\"keyword-test-step-table\"]/tbody/tr["+lastRowNumber+"]/td[4]/a"));
        wait.until(ExpectedConditions.elementToBeClickable(deleteKeywordTestStepButton));
        deleteKeywordTestStepButton.click();
        // confirmation popup
        WebElement popupConfirmInupt = getDriver().findElements(By.xpath("//*[@id=\"delete-keyword-test-step-dialog\"]/following-sibling::div/div/input")).get(0);
        wait.until(ExpectedConditions.elementToBeClickable(popupConfirmInupt));
        popupConfirmInupt.click();
    }

    private int getTableRowSize(String tableXpath) {
        return getDriver().findElements(By.xpath(tableXpath)).size();
    }

    private void removeSuggestionsAndAddNewKeywordTestStep(WebDriverWait wait) {
        WebElement actionWordInput = getDriver().findElement(By.id("action-word-input"));
        wait.until(ExpectedConditions.elementToBeClickable(actionWordInput));
        actionWordInput.sendKeys(Keys.RETURN);
        wait.until(ExpectedConditions.elementToBeClickable(actionWordInput));
        actionWordInput.sendKeys(Keys.RETURN);
    }

}
