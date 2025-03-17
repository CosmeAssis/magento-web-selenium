package com.template.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class WebDriverFactory {
    private static WebDriver driver;

    public static WebDriver getDriver(String browser) {
        if (driver == null) {
            boolean isHeadless = Boolean.parseBoolean(GlobalParameters.get("headless"));

            switch (browser.toLowerCase()) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (isHeadless) {
                        firefoxOptions.addArguments("--headless");
                    }
                    driver = new FirefoxDriver(firefoxOptions);
                    break;

                default: // Chrome (padrão)
                    WebDriverManager.chromedriver().clearDriverCache().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (isHeadless) {
                        chromeOptions.addArguments("--headless");
                        chromeOptions.addArguments("--disable-gpu");
                        chromeOptions.addArguments("--window-size=1920,1080");
                    }
                    driver = new ChromeDriver(chromeOptions);
                    break;
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            System.out.println("✅ Navegador iniciado: " + browser);
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            System.out.println("🛑 WebDriver fechado com sucesso.");
        }
    }
}