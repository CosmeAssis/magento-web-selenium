package com.template.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By shoppingCartLink = By.cssSelector(".message-success a[href*='checkout/cart']");
    private By proceedToCheckoutButton = By.cssSelector(".checkout-methods-items button");
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void acessarCarrinho() {
        WebElement linkCarrinho = wait.until(ExpectedConditions.elementToBeClickable(shoppingCartLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", linkCarrinho);
        linkCarrinho.click();
    }

    public void clicarEmProceedToCheckout() {
        WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkoutButton);
        checkoutButton.click();
    }
}