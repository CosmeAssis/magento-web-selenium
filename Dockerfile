# 🔥 Imagem base com Java e Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build

# 🔹 Diretório de trabalho no container
WORKDIR /app

# 🔹 Copia os arquivos do projeto para dentro do container
COPY . .

# 🔹 Instala dependências do Maven
RUN mvn clean install -DskipTests

# 🔹 Comando padrão para executar testes automaticamente
CMD ["mvn", "test"]