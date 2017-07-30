# elo7-developer

Requerimentos
- Java 8
- Maven
- Docker
- Docker Compose
- Git

Como rodar:

1) Clonar o projeto ;)

2) Gerar imagem docker customizada do elastic search.
Dentro do diretório /src/main/elasticsearch rodar: docker build -t elo7/elastic .

3) Build e imagem docker do projeto. Na raiz do projeto rodar: mvn clean package docker:build

4) Rodar os containers. Na raiz do projeto rodar: docker-compose up

5) Rodar setup do elasticsearch. Na raiz do projeto execute o script setup.sh
