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
        List<WebElement> produtos = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productLinks));

        if (!produtos.isEmpty()) {
            WebElement primeiroProduto = produtos.get(0);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", primeiroProduto);
            primeiroProduto.click();
        } else {
            throw new RuntimeException("❌ ERRO: Nenhum produto encontrado!");
        }
    }

    public void selecionarVariacoesDoProduto() {
        List<WebElement> tamanhos = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(tamanhoDisponivel));
        List<WebElement> cores = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(corDisponivel));

        if (!tamanhos.isEmpty()) {
            WebElement tamanhoSelecionado = tamanhos.get(0);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tamanhoSelecionado);
            tamanhoSelecionado.click();
        } else {
            throw new RuntimeException("❌ ERRO: Nenhuma opção de tamanho disponível!");
        }

        if (!cores.isEmpty()) {
            WebElement corSelecionada = cores.get(0);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", corSelecionada);
            corSelecionada.click();
        } else {
            throw new RuntimeException("❌ ERRO: Nenhuma opção de cor disponível!");
        }
    }

    public void adicionarAoCarrinho() {
        WebElement botaoCarrinho = wait.until(ExpectedConditions.elementToBeClickable(botaoAdicionarCarrinho));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", botaoCarrinho);
        botaoCarrinho.click();

    }
}