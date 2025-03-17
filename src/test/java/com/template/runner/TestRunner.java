package com.template.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.template.step",
        plugin = {
                "pretty",
                "html:reports/cucumber-report.html",
                "summary"
        },
        publish = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}