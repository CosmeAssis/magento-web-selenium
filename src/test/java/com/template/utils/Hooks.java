package com.template.utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Hooks {
    private WebDriver driver;

    @Before
    public void setup(Scenario scenario) {
        driver = WebDriverFactory.getDriver("chrome");
        ExtentManager.createTest(scenario.getName()); // 🔥 Adiciona cenário ao relatório
        System.out.println("🚀 Teste iniciado: " + scenario.getName());
    }

    @After
    public void teardown(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotPath = captureScreenshot(scenario.getName());
            try {
                ExtentManager.getTest().fail("❌ Falha no Teste",
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } catch (Exception e) {
                System.out.println("⚠ Erro ao anexar screenshot: " + e.getMessage());
            }
        }

        WebDriverFactory.quitDriver();
    }

    @AfterAll
    public static void finalizarRelatorio() {
        System.out.println("📄 Finalizando ExtentReports...");
        ExtentManager.flush();
    }

    private String captureScreenshot(String scenarioName) {
        File screenshotFile = ((TakesScreenshot) WebDriverFactory.getDriver("chrome"))
                .getScreenshotAs(OutputType.FILE);
        String basePath = System.getProperty("user.dir");
        String screenshotDir = basePath + "/target/reports/screenshots";
        new File(screenshotDir).mkdirs();

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String screenshotPath = screenshotDir + "/" + scenarioName.replace(" ", "_") + "_" + timestamp + ".png";

        File destino = new File(screenshotPath);
        screenshotFile.renameTo(destino);
        System.out.println("📸 Screenshot salva: " + screenshotPath);
        return screenshotPath;
    }
}