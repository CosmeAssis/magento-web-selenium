package com.template.utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
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
    public void setup() {
        driver = WebDriverFactory.getDriver(GlobalParameters.get("browser"));
        System.out.println("🚀 Teste iniciado no navegador.");
    }

    @After
    public void teardown(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotPath = captureScreenshot(scenario.getName());

            try {
                ExtentManager.getTest().fail("❌ Falha no Teste",
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } catch (Exception e) {
                System.out.println("⚠ Erro ao anexar a screenshot: " + e.getMessage());
            }
        }

        WebDriverFactory.quitDriver();
    }

    @AfterAll
    public static void finalizarRelatorio() {
        System.out.println("📄 Finalizando ExtentReports e gerando relatório...");
        ExtentManager.flush();
    }

    private String captureScreenshot(String scenarioName) {
        File screenshotFile = ((TakesScreenshot) WebDriverFactory.getDriver(GlobalParameters.get("browser")))
                .getScreenshotAs(OutputType.FILE);
        String screenshotDir = GlobalParameters.get("screenshotsPath");
        new File(screenshotDir).mkdirs();

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String screenshotPath = screenshotDir + "/" + scenarioName.replace(" ", "_") + "_" + timestamp + ".png";

        File destino = new File(screenshotPath);
        if (screenshotFile.renameTo(destino)) {
            System.out.println("📸 Screenshot salva: " + screenshotPath);
        }
        return screenshotPath;
    }
}