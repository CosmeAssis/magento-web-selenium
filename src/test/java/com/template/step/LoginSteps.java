package com.template.step;

import com.template.pages.LoginPage;
import com.template.utils.WebDriverFactory;
import io.cucumber.java.pt.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginSteps {
    WebDriver driver = WebDriverFactory.getDriver("chrome");
    LoginPage loginPage = new LoginPage(driver);

    @Dado("que estou na pagina de login do Magento")
    public void acessarPaginaDeLogin() {
        loginPage.acessarPaginaDeLogin();
    }

    @Quando("preencho o email e senha e clico em entrar")
    public void preencherEmailSenhaEClicarEmEntrar() {
        loginPage.preencherCredenciais("teste@automacao.com.br", "teste@123");
        loginPage.clicarBotaoLogin();
    }

    @Então("sou redirecionado para minha conta")
    public void validarRedirecionamentoParaMinhaConta() {
        String urlEsperada = "https://magento.softwaretestingboard.com/customer/account/";
        Assert.assertEquals(driver.getCurrentUrl(), urlEsperada, "Erro: Redirecionamento incorreto.");

        WebDriverFactory.quitDriver();
    }
}