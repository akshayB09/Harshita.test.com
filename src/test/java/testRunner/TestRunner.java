package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
/*
This Automation suite has been designed with cucumber framework integrated with Junit. For best results
please run the suite in IntelliJ. Following are the pre-condition for the successful execution of this
Automation suite:
1. Suite has been downloaded as Maven/Cucumber Project
2. No disruption in accessing dependencies or website
3. Java Version 8 and above
4. cucumber-java and cucumber-junit version to be used as 6.10.2
5. Selenium version 3.14 and above
 */


@RunWith(Cucumber.class)
@CucumberOptions(features = "src\\test\\resources"
        ,glue={"stepDefinition"}
        )
public class TestRunner {
}
