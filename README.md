# Pokedex


Neste Projeto é possível realizar o crud completo de mestres pokemons
além de adicionar pokemons a um mestre, enviar pokemons para o professor Carvalho, e recuperar,
respeitando o limite de carregar 6 pokemons.


## Desenvolvimento

### Construído com
Projeto construído com Java 11, SpringBoot 2.3.2, Spock, Dynamodb e Docker, Api Pokemon.

### Pre-requisitos
Todas as depencias já estão configuradas no projeto, mas é necessário ter instalado na sua máquina
Jdk11, maven 3, docker e docker-compose


### Configurando ambiente

Clone e baixe as dependencias do projeto com os comandos abaixo

```shell
git clone https://github.com/jerrymartins/pokedex.git
cd pokedex/
mvn install
```

#### application.properties
Para executar o back end apontando para o banco localhost, é necessário informar o endpoint do banco de dados de localhost, e deixar as demais propriedades em branco.

```shell
amazon.dynamodb.endpoint=http://localhost:8000
amazon.aws.accesskey=
amazon.aws.secretkey=
```

Para acessar o banco de dados na AWS, deve deixar em branco a propriedade amazon.dynamodb.endpoint e informar as suas credenciais da AWS nas demais propriedades.

```shell
amazon.dynamodb.endpoint=
amazon.aws.accesskey=SOMEACCESSkEY
amazon.aws.secretkey=SOMESECRETKEY
```


### Contruindo jar

Gere um jar do backend executando

```shell
mvn package
```

### Realizando deploy
Após clonar o repositório e baixar as dependencias, finalmente é hora de subir o banco de dados e o serviço executando

```shell
docker-compose up
```

## Testes

```shell
mvn test
```

## Potman
No Postman, clique em Import, e clique na aba Link
cole este link: https://www.getpostman.com/collections/058a543fc31607162149 ,
então cliquem em continuar

## Swagger
Com o serviço executando em localhost acesse a url:
http://localhost:5000/master-pokemon/swagger-ui.html para 

Para acessar o Swagger do serviço executando na Aws acesse:
http://pokemon.us-east-1.elasticbeanstalk.com/swagger-ui.html
# pokedex
