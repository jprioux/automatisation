package squash.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author qtran - created on 22/07/2020
 */
public class LoginPage extends AbstractPage {
    @FindBy(how = How.XPATH, using = "//input[@id='username'][@type='text']")
    private WebElement userNameInput;
    @FindBy(how = How.XPATH, using = "//input[@id='password'][@type='password']")
    private WebElement passwordInput;
    @FindBy(how = How.XPATH, using = "//input[@class='sq-btn'][@type='submit'][ancestor::div[@id='login-form-button-set']]")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void fillUserNameAndPasswordInput(WebDriverWait wait, String username, String password){
        waitUntilDocumentReadyStateComplete(wait);
        wait.until(ExpectedConditions.elementToBeClickable(userNameInput));
        wait.until(ExpectedConditions.elementToBeClickable(passwordInput));
        userNameInput.sendKeys(username);
        passwordInput.sendKeys(password);
    }

    public void loginToHomePage(WebDriverWait wait){
        waitUntilDocumentReadyStateComplete(wait);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

}
