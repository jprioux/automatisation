package squash.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

/**
 * @author qtran - created on 22/07/2020
 */
public class SlowWebDriver implements WebDriver, JavascriptExecutor {
    private final WebDriver delegate;
    private final Integer timeToWait;

    public SlowWebDriver(WebDriver driver, Integer timeToWait) {
        this.delegate = driver;
        this.timeToWait = timeToWait;
    }

    @Override
    public Object executeScript(String s, Object... objects) {
        return ((JavascriptExecutor)delegate).executeScript(s,objects);
    }

    @Override
    public Object executeAsyncScript(String s, Object... objects) {
        return ((JavascriptExecutor)delegate).executeAsyncScript(s,objects);
    }

    @Override
    public void get(String s) {
        slowThingsABit();
        delegate.get(s);
    }

    @Override
    public String getCurrentUrl() {
        slowThingsABit();
        return delegate.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        slowThingsABit();
        return delegate.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        slowThingsABit();
        return delegate.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        slowThingsABit();
        return delegate.findElement(by);
    }

    @Override
    public String getPageSource() {
        slowThingsABit();
        return delegate.getPageSource();
    }

    @Override
    public void close() {
        slowThingsABit();
        delegate.close();
    }

    @Override
    public void quit() {
        slowThingsABit();
        delegate.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return delegate.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return delegate.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        slowThingsABit();
        return delegate.switchTo();
    }

    @Override
    public Navigation navigate() {
        slowThingsABit();
        return delegate.navigate();
    }

    @Override
    public Options manage() {
        return delegate.manage();
    }

    private void slowThingsABit(){
        try {
            Thread.sleep(timeToWait);
        } catch (InterruptedException e) {
            System.out.println("Swallowing exception is nasty but ok for a demo..." + e.getMessage());
        }
    }

}
