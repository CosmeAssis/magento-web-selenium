package com.template.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class WebDriverFactory {
    private static WebDriver driver;

    public static WebDriver getDriver(String browser) {
        if (driver == null) {
            boolean isRunningInCiCd = System.getenv("CI") != null;
            boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));
            String seleniumServerUrl = System.getenv("SELENIUM_REMOTE_URL");

            try {
                switch (browser.toLowerCase()) {
                    case "firefox":
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        if (isHeadless) {
                            firefoxOptions.addArguments("--headless");
                        }
                        driver = isRunningInCiCd
                                ? new RemoteWebDriver(new URL(seleniumServerUrl), firefoxOptions) // CI/CD (Docker)
                                : new FirefoxDriver(firefoxOptions); // Local
                        break;

                    default: // Chrome (padrão)
                        ChromeOptions chromeOptions = new ChromeOptions();
                        if (isHeadless) {
                            chromeOptions.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080");
                        }
                        driver = isRunningInCiCd
                                ? new RemoteWebDriver(new URL(seleniumServerUrl), chromeOptions) // CI/CD (Docker)
                                : new ChromeDriver(chromeOptions); // Local
                        break;
                }

                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                System.out.println("✅ WebDriver inicializado com sucesso!");
            } catch (MalformedURLException e) {
                throw new RuntimeException("ERRO: URL do Selenium Server inválida para CI/CD: " + seleniumServerUrl, e);
            }
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            System.out.println("🛑 WebDriver fechado corretamente.");
        }
    }
}