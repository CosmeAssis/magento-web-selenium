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
        try {
            this.driver = WebDriverFactory.getDriver("chrome");
            this.loginPage = new LoginPage(driver);
            this.homePage = new HomePage(driver);
            this.productPage = new ProductPage(driver);
            this.cartPage = new CartPage(driver);
            this.checkoutPage = new CheckoutPage(driver);
            this.confirmationPage = new OrderConfirmationPage(driver);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inicializar PurchaseSteps: " + e.getMessage(), e);
        }
    }

    @Dado("que estou na página inicial do Magento")
    public void acessarPaginaInicial() {
        driver.get("https://magento.softwaretestingboard.com/");
    }

    @Quando("faço login com email {string} e senha {string}")
    public void fazerLogin(String email, String senha) {
        loginPage.acessarPaginaDeLogin();
        loginPage.preencherCredenciais(email, senha);
        loginPage.clicarBotaoLogin();
    }

    @Quando("escolho o produto {string}")
    public void escolhoOProduto(String nomeProduto) {
        homePage.buscarProduto(nomeProduto);
    }

    @Quando("clico no produto para visualizar detalhes")
    public void clicoNoProdutoParaVisualizarDetalhes() {
        productPage.clicarNoProduto();
    }

    @Quando("seleciono o tamanho e a cor e adiciono ao carrinho")
    public void selecionoOTamanhoEACorEAdicionoAoCarrinho() {
        productPage.selecionarVariacoesDoProduto();
        productPage.adicionarAoCarrinho();
    }

    @Quando("sigo para checkout")
    public void sigoParaCheckout() {
        cartPage.acessarCarrinho();
        cartPage.clicarEmProceedToCheckout();
    }

    @Quando("finalizo a compra selecionando método de envio e confirmando pedido")
    public void finalizoACompraSelecionandoMetodoDeEnvioEConfirmandoPedido() {
        checkoutPage.escolherMetodoDeEnvio();
        checkoutPage.clicarEmNext();
        checkoutPage.clicarEmPlaceOrder();
    }

    @Então("vejo a confirmação do pedido realizado com sucesso")
    public void validarPedido() {
        Assert.assertTrue(checkoutPage.validarPedidoConcluido(), "❌ ERRO: O pedido não foi concluído com sucesso.");
        WebDriverFactory.quitDriver();
    }
}