package squash;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * @author qtran - created on 21/07/2020
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = { "classpath:squash/tc_10523.feature" })
public class RunTest {
}
