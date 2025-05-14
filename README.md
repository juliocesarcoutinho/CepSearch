# Apresentação do Desafio Técnico Nava
## Julio Cesar Coutinho – Consulta de CEP com Logs

---

## Objetivo
- Consultar CEPs via API externa (mockada)
- Gravar logs de requisição no banco
- Utilizar princípios SOLID
- Documentação e apresentação do projeto

---

## Tecnologias Utilizadas
- Java 21 + Spring Boot 3.4.5
- Mysql
- Docker + Docker Compose
- Wiremock
- Maven

---

## Arquitetura da Solução

```
[Client HTTP]
     |
     V
[Controller] --> [Service] --> [CepClient (Wiremock)]
     |
     +------------------> [Mysql DB (Log de CEPs)]
```

---

## Entidades e Fluxo

**Endpoint:** `/zips/{cep}`  
**Fluxo:**  
1. Controller chama Service  
2. Service consulta Wiremock (Client)  
3. Log persiste no banco (Repository)  

---

## Boas Práticas

- Princípios SOLID
- Separação clara de camadas
- Código limpo e documentado
- README com instruções de uso

---
## Executando a aplicação
- Executar o container para o Wiremock
  
```
docker run -d --name wiremock -p 8081:8080 wiremock/wiremock
```
- Fazer inserção dos stud mappings via postman
```
http://localhost:8081/__admin/mappings
```
- Exemplo de Json (stub mappings) a ser inserido

```
{
  "request": {
    "method": "GET",
    "url": "/cep/18950302"
  },
  "response": {
    "status": 200,
    "jsonBody": {
      "zipCode": "18950302",
      "street": "Rua Teste 2, 500",
      "district": "Jd dos Brilhantes",
      "city": "Ipaussu",
      "state": "SP"
    },
    "headers": {
      "Content-Type": "application/json"
    }
  }
}

```
## Exemplo de retorno do stubs
```
{
    "id": "e7eb7dde-90fb-4ad3-bc2f-67102c479ffd",
    "request": {
        "url": "/cep/18950302",
        "method": "GET"
    },
    "response": {
        "status": 200,
        "jsonBody": {
            "zipCode": "18950302",
            "street": "Rua Teste 2, 500",
            "district": "Jd dos Brilhantes",
            "city": "Ipaussu",
            "state": "SP"
        },
        "headers": {
            "Content-Type": "application/json"
        }
    },
    "uuid": "e7eb7dde-90fb-4ad3-bc2f-67102c479ffd"
}
```
## Testando a aplicação via postman
```
http://localhost:8080/api/zips/:zipCode
```
## Testando a aplicação via Swagger
```
http://localhost:8080/swagger-ui/index.html
```

## Response
```
{
    "zipCode": "18950302",
    "street": "Rua Teste 2, 500",
    "district": "Jd dos Brilhantes",
    "city": "Ipaussu",
    "state": "SP"
}

```
## Execução

```bash
http://localhost:8080/api/zips/{zipcode}
```

Resposta mockada (Json) com Record + Log gravado.

---


## Repositório

[Acesse o projeto no GitHub](https://github.com/juliocesarcoutinho/CepSearch)
