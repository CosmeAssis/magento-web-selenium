# 🛒 Magento E2E Test Automation

Este projeto contém testes **automatizados End-to-End (E2E)** para o site Magento, simulando um fluxo completo de compra.  
Os testes foram desenvolvidos utilizando **Selenium WebDriver, Cucumber, TestNG e ExtentReports**.

---

## 🚀 Tecnologias Utilizadas
✅ **Java 21**  
✅ **Selenium WebDriver**  
✅ **Cucumber (BDD - Gherkin)**  
✅ **TestNG**  
✅ **ExtentReports (Geração de Relatórios)**  
✅ **Maven**  
✅ **WebDriverManager**

---

## 📝 Cenário de Teste (Fluxo de Compra E2E)
**Localização:** [`purchase.feature`](src/test/resources/features/purchase.feature)

```gherkin
# language: pt
Funcionalidade: Fluxo de Compra E2E no Magento

  Cenário: Comprar um produto com conta já cadastrada
    Dado que estou na página inicial do Magento
    Quando faço login com email "teste@automacao.com.br" e senha "teste@123"
    E escolho o produto "Radiant Tee"
    E clico no produto para visualizar detalhes
    E seleciono o tamanho e a cor e adiciono ao carrinho
    E sigo para checkout
    E finalizo a compra selecionando método de envio e confirmando pedido
    Então vejo a confirmação do pedido realizado com sucesso

# 1️⃣ Iniciar o Selenium Grid no Docker
docker-compose up -d

# 2️⃣ Executar os Testes no Container
docker-compose run --rm tests

# 3️⃣ Derrubar os Containers Após os Testes
docker-compose down