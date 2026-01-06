# 🗺️ Meus Mapas

Sistema simples para criação de mapas e cadastro de pontos geográficos.

Este projeto foi desenvolvido como parte de um teste técnico, com foco em
organização de código, funcionamento da solução e uso de banco de dados relacional.

---

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven

---

## 📌 Funcionalidades

- Criar mapas
- Listar mapas
- Buscar mapa por ID
- Excluir mapa
- Cadastrar pontos em um mapa
- Cada ponto pertence a um único mapa
- Exclusão em cascata de pontos ao remover um mapa

---

## 🔁 Relacionamento entre as Entidades

- Um **Mapa** pode possuir vários **Pontos**
- Um **Ponto** pertence a exatamente um **Mapa**

Este relacionamento é implementado usando JPA com as anotações:
`@OneToMany` e `@ManyToOne`.

---

## ▶️ Como Executar o Projeto

1. Clone o repositório
2. Configure o banco de dados no `application.properties`
3. Execute a aplicação
4. A API ficará disponível em `http://localhost:8080/mapas`

---
---

## 🗄️ Configuração do Banco de Dados

A aplicação utiliza **MySQL** como banco de dados e se conecta através do **driver JDBC do MySQL** (
`com.mysql.cj.jdbc.Driver`).

O driver é responsável por:

- Criar a conexão entre a aplicação Java e o banco de dados MySQL
- Traduzir comandos Java/Hibernate em SQL compreendido pelo MySQL
- Gerenciar tipos de dados e transações

### Configuração no `application.properties`

**properties**

- `spring.datasource.url=jdbc:mysql://localhost:3306/meusmapasDB`
- `spring.datasource.username=root`
- `spring.datasource.password=*****`
- `spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver`

O Hibernate está configurado no modo validate, o que garante que
as entidades Java correspondam exatamente às tabelas existentes no banco,
sem criar ou alterar estruturas automaticamente.

- `spring.jpa.hibernate.ddl-auto=validate`
- `spring.jpa.show-sql=true`

---

## 📡 Endpoints Principais

- `GET /mapas` – Lista todos os mapas
- `GET /mapas/{id}` – Busca um mapa específico
- `POST /mapas` – Cria um novo mapa
- `PUT /mapas/{id}` – Atualiza o nome de um mapa
- `DELETE /mapas/{id}` – Remove um mapa e seus pontos

---

## ✍️ Observações

Este projeto foi desenvolvido para aprendizado.
Todo o código foi escrito com foco em clareza e compreensão,
sendo possível explicar cada parte da implementação.


