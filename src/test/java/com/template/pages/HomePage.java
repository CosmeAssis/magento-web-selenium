package com.template.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By searchBox = By.id("search");
    private By searchButton = By.cssSelector("button[title='Search']");
    private By productResults = By.cssSelector(".product-item-link");

    public void buscarProduto(String nomeProduto) {
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        searchField.clear();
        searchField.sendKeys(nomeProduto);
        driver.findElement(searchButton).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(productResults));

        List<WebElement> produtosEncontrados = driver.findElements(productResults);
        for (WebElement produto : produtosEncontrados) {
            if (produto.getText().trim().equalsIgnoreCase(nomeProduto)) {
                produto.click();
                return;
            }
        }

        throw new RuntimeException("Produto não encontrado na página de resultados!");
    }
}