# language: pt
Funcionalidade: Fluxo de Compra E2E no Magento

  Cenário: Comprar um produto com conta já cadastrada
    Dado que estou na página inicial do Magento
    Quando faço login com email "teste@automacao.com.br" e senha "teste@123"
    E escolho o produto "Radiant Tee"
    E seleciono o tamanho e a cor e adiciono ao carrinho
    E sigo para checkout
    E finalizo a compra selecionando método de envio e confirmando pedido
    Então vejo a confirmação do pedido realizado com sucesso