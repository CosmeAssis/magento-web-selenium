package com.template.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverFactory {
    private static WebDriver driver;

    public static WebDriver getDriver(String browser) {
        if (driver == null) {
            boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "true"));
            String seleniumServerUrl = System.getenv("SELENIUM_REMOTE_URL");

            try {
                switch (browser.toLowerCase()) {
                    case "firefox":
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        if (isHeadless) {
                            firefoxOptions.addArguments("--headless");
                        }
                        driver = new RemoteWebDriver(new URL(seleniumServerUrl), firefoxOptions);
                        break;

                    default:
                        ChromeOptions chromeOptions = new ChromeOptions();
                        if (isHeadless) {
                            chromeOptions.addArguments("--headless");
                            chromeOptions.addArguments("--disable-gpu");
                            chromeOptions.addArguments("--window-size=1920,1080");
                        }
                        driver = new RemoteWebDriver(new URL(seleniumServerUrl), chromeOptions);
                        break;
                }
            } catch (MalformedURLException e) {
                throw new RuntimeException("URL do Selenium Server está incorreta: " + seleniumServerUrl, e);
            }
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