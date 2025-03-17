# 🔹 Usa a imagem oficial do Maven + Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build

# 🔹 Define o diretório de trabalho dentro do container
WORKDIR /app

# 🔹 Copia os arquivos do projeto para dentro do container
COPY . .

# 🔹 Instala as dependências do Maven
RUN mvn clean install

# 🔹 Comando padrão ao rodar o container (executar os testes)
CMD ["mvn", "test"]