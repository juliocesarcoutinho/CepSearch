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

## Execução

```bash
http://localhost:8080/api/zips/{zipcode}
```

Resposta mockada (Json) com Record + Log gravado.

---

## Boas Práticas

- Princípios SOLID
- Separação clara de camadas
- Código limpo e documentado
- README com instruções de uso

---

## Repositório

[Acesse o projeto no GitHub](https://github.com/juliocesarcoutinho/CepSearch)
