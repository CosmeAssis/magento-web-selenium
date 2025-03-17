package com.template.step;

import com.template.pages.*;
import com.template.utils.WebDriverFactory;
import io.cucumber.java.pt.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PurchaseSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private OrderConfirmationPage confirmationPage;

    public PurchaseSteps() {
        this.driver = WebDriverFactory.getDriver(System.getProperty("browser", "chrome")); // 🔥 Solução para o GitHub Actions
        this.loginPage = new LoginPage(driver);
        this.homePage = new HomePage(driver);
        this.productPage = new ProductPage(driver);
        this.cartPage = new CartPage(driver);
        this.checkoutPage = new CheckoutPage(driver);
        this.confirmationPage = new OrderConfirmationPage(driver);
    }

    @Dado("que estou na página inicial do Magento")
    public void acessarPaginaInicial() {
        driver.get("https://magento.softwaretestingboard.com/");
        System.out.println("✅ Página inicial acessada.");
    }

    @Quando("faço login com email {string} e senha {string}")
    public void fazerLogin(String email, String senha) {
        loginPage.acessarPaginaDeLogin();
        loginPage.preencherCredenciais(email, senha);
        loginPage.clicarBotaoLogin();
        System.out.println("✅ Login efetuado com sucesso.");
    }

    @Quando("escolho o produto {string}")
    public void escolhoOProduto(String nomeProduto) {
        homePage.buscarProduto(nomeProduto);
        System.out.println("✅ Produto '" + nomeProduto + "' pesquisado.");
    }

    @Quando("clico no produto para visualizar detalhes")
    public void clicoNoProdutoParaVisualizarDetalhes() {
        productPage.clicarNoProduto();
        System.out.println("✅ Produto acessado na página de detalhes.");
    }

    @Quando("seleciono o tamanho e a cor e adiciono ao carrinho")
    public void selecionoOTamanhoEACorEAdicionoAoCarrinho() {
        productPage.selecionarVariacoesDoProduto();
        productPage.adicionarAoCarrinho();
        System.out.println("✅ Produto configurado e adicionado ao carrinho!");
    }

    @Quando("sigo para checkout")
    public void sigoParaCheckout() {
        cartPage.acessarCarrinho();
        cartPage.clicarEmProceedToCheckout();
        System.out.println("✅ Checkout iniciado com sucesso!");
    }

    @Quando("finalizo a compra selecionando método de envio e confirmando pedido")
    public void finalizoACompraSelecionandoMetodoDeEnvioEConfirmandoPedido() {
        checkoutPage.escolherMetodoDeEnvio();
        checkoutPage.clicarEmNext();
        checkoutPage.clicarEmPlaceOrder();
        System.out.println("✅ Pedido foi finalizado com sucesso!");
    }

    @Então("vejo a confirmação do pedido realizado com sucesso")
    public void validarPedido() {
        Assert.assertTrue(checkoutPage.validarPedidoConcluido(), "❌ ERRO: O pedido não foi concluído com sucesso.");
        System.out.println("✅ Pedido concluído com sucesso.");
        WebDriverFactory.quitDriver();
    }
}