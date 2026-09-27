# Ford Retain

Sistema para fortalecer a retenção de clientes no pós-venda da Ford, reduzindo a evasão para oficinas fora da rede autorizada e protegendo o **VIN Share** da marca (percentual de vezes que um cliente retorna à própria marca para manutenção).

## O problema

Hoje, muitos clientes esquecem revisões, buscam serviços fora da concessionária ou simplesmente param de frequentar a rede Ford — o que impacta diretamente o VIN Share da marca.

## A solução

A partir de dados básicos de clientes e veículos (quilometragem, tempo desde a última revisão, idade do carro, garantia), o sistema identifica o comportamento de cada cliente e o classifica em níveis de risco de evasão — **baixo, médio ou alto**. Com base nesse risco, o sistema sugere ações concretas para a concessionária, como contato proativo, oferta de desconto ou lembrete de manutenção.

O projeto completo é composto por três frentes:
- **API** (este repositório) — organiza e expõe os dados de clientes e veículos
- **Aplicativo mobile** — visualização das informações pela concessionária
- **Análise preditiva** — previsão de comportamento e classificação de risco

## Este repositório

Esta API REST cobre o cadastro de **clientes** e **veículos**, com autenticação via JWT, servindo de base para as demais camadas do Ford Retain.

## Integrantes

- Lorenzzo Vendruscolo Dias - RM558305
- Gabriel Martins Vannucci - RM556883
- Miguel Marques Lourenço - RM555426
- Pedro Henrique Ferronato - RM554757
- Athos Rodrigues Alves - RM555515

## Tecnologias
- Java 17+
- Spring Boot 4.0.6 (Web, Data JPA, Security, Validation)
- JWT (io.jsonwebtoken)
- H2 (banco em memória) / MySQL
- SpringDoc OpenAPI (Swagger)
- JUnit 5 + MockMvc

## Como rodar

1. Clone o repositório
2. Rode o projeto:

./mvnw spring-boot:run

3. A API sobe em `http://localhost:8080`

## Documentação (Swagger)

Com a aplicação rodando, acesse:

http://localhost:8080/swagger-ui/index.html


## Autenticação

1. Registre um usuário: `POST /auth/register`
```json
   { "username": "teste", "password": "123456" }
```
2. Faça login: `POST /auth/login` (mesmo corpo) — retorna um token JWT
3. Use o token nos endpoints protegidos:

Authorization: Bearer <token>


## Endpoints

| Método | Rota | Autenticado |
|---|---|---|
| POST | /auth/register | Não |
| POST | /auth/login | Não |
| GET/POST/PUT/DELETE | /veiculos | Sim |
| GET/POST/PUT/DELETE | /clientes | Sim |

## Tratamento de erros

Respostas de erro seguem um formato padronizado:
```json
{
  "status": 404,
  "erro": "Não encontrado",
  "mensagem": "Veículo não encontrado",
  "timestamp": "2026-09-27T18:30:00"
}
```

## Testes

./mvnw test
