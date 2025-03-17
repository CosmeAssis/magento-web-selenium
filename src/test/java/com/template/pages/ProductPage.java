package com.template.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By productLinks = By.cssSelector(".product-item-link");
    private By tamanhoDisponivel = By.cssSelector(".swatch-attribute.size .swatch-option");
    private By corDisponivel = By.cssSelector(".swatch-attribute.color .swatch-option");
    private By botaoAdicionarCarrinho = By.id("product-addtocart-button");

    public void clicarNoProduto() {
        System.out.println("⌛ Aguardando produtos carregarem...");
        List<WebElement> produtos = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productLinks));

        if (!produtos.isEmpty()) {
            WebElement primeiroProduto = produtos.get(0);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", primeiroProduto);
            primeiroProduto.click();
            System.out.println("✅ Produto acessado.");
        } else {
            throw new RuntimeException("❌ ERRO: Nenhum produto encontrado!");
        }
    }

    public void selecionarVariacoesDoProduto() {
        System.out.println("⌛ Aguardando opções de tamanho e cor...");
        List<WebElement> tamanhos = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(tamanhoDisponivel));
        List<WebElement> cores = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(corDisponivel));

        if (!tamanhos.isEmpty()) {
            WebElement tamanhoSelecionado = tamanhos.get(0);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tamanhoSelecionado);
            tamanhoSelecionado.click();
            System.out.println("✅ Tamanho selecionado.");
        } else {
            throw new RuntimeException("❌ ERRO: Nenhuma opção de tamanho disponível!");
        }

        if (!cores.isEmpty()) {
            WebElement corSelecionada = cores.get(0);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", corSelecionada);
            corSelecionada.click();
            System.out.println("✅ Cor selecionada.");
        } else {
            throw new RuntimeException("❌ ERRO: Nenhuma opção de cor disponível!");
        }
    }

    public void adicionarAoCarrinho() {
        System.out.println("⌛ Aguardando botão 'Adicionar ao Carrinho'...");
        WebElement botaoCarrinho = wait.until(ExpectedConditions.elementToBeClickable(botaoAdicionarCarrinho));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", botaoCarrinho);
        botaoCarrinho.click();

        System.out.println("✅ Produto adicionado ao carrinho!");
    }
}