package com.template.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderConfirmationPage {
    private WebDriver driver;

    public OrderConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    private By mensagemConfirmacao = By.cssSelector(".checkout-success .page-title");

    public boolean pedidoFoiRealizadoComSucesso() {
        return driver.findElement(mensagemConfirmacao).isDisplayed();
    }
}