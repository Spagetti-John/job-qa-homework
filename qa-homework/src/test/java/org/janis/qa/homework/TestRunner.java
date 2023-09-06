package org.janis.qa.homework;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "html:target/cucumber-reports/CucumberReport.html",
                "junit:target/junit-reports/report.xml"},
        features = "src/test/resources/features",
        glue = {"org/janis/qa/homework/stepdefinitions"}
)
public class TestRunner {
}
