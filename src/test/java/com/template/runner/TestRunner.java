package com.template.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.template.step",
        plugin = {
                "pretty",
                "html:target/reports/cucumber-report.html",
                "json:target/reports/cucumber-report.json",
                "junit:target/reports/cucumber-report.xml"
        },
        monochrome = true  // Melhor leitura dos logs no terminal
)
public class TestRunner extends AbstractTestNGCucumberTests {
}