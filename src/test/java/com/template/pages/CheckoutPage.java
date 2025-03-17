package com.template.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By fixedShippingMethod = By.cssSelector("input[name='ko_unique_1']");
    private By nextButton = By.cssSelector(".button.action.continue.primary");
    private By placeOrderButton = By.cssSelector(".action.primary.checkout");
    private By orderSuccessMessage = By.xpath("//span[contains(text(),'Thank you for your purchase!')]");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void escolherMetodoDeEnvio() {
        wait.until(ExpectedConditions.elementToBeClickable(fixedShippingMethod)).click();
    }

    public void clicarEmNext() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
    }

    public void clicarEmPlaceOrder() {
        WebElement botaoPlaceOrder = wait.until(ExpectedConditions.presenceOfElementLocated(placeOrderButton));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", botaoPlaceOrder);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(botaoPlaceOrder)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", botaoPlaceOrder);
        }
    }

    public boolean validarPedidoConcluido() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderSuccessMessage)).isDisplayed();
    }
}