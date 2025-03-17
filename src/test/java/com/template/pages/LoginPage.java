package com.template.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By emailField = By.id("email");
    private By passwordField = By.id("pass");
    private By signInButton = By.id("send2");

    public void acessarPaginaDeLogin() {
        driver.get("https://magento.softwaretestingboard.com/customer/account/login/");
    }

    public void preencherCredenciais(String email, String senha) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(senha);
    }

    public void clicarBotaoLogin() {
        driver.findElement(signInButton).click();
    }
}