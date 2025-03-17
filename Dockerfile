# 🔹 Usando imagem base do Maven com OpenJDK
FROM maven:3.9.6-eclipse-temurin-21 AS build

# 🔹 Definir diretório de trabalho
WORKDIR /app

# 🔹 Copiar os arquivos do código para o container
COPY . .

# 🔹 Instalar dependências do Maven
RUN mvn clean install

# 🔹 Instalar o Allure Commandline dentro do container
RUN apt-get update && apt-get install -y curl unzip && \
    curl -o allure-2.21.0.zip -L https://github.com/allure-framework/allure2/releases/download/2.21.0/allure-2.21.0.zip && \
    unzip allure-2.21.0.zip && \
    mv allure-2.21.0 /opt/allure && \
    ln -s /opt/allure/bin/allure /usr/bin/allure

# 🔹 Comando padrão ao rodar o container
CMD ["mvn", "test"]