# Todo API

API REST para gerenciamento de tarefas, desenvolvida com **Java + Spring Boot + JdbcTemplate + PostgreSQL**.

---

## Descrição

O **Todo API** é um sistema backend que permite o gerenciamento de tarefas associadas a usuários.
Cada usuário pode criar, visualizar, atualizar e excluir suas tarefas, além de marcar seu status como concluída ou pendente.

---

## Tecnologias

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"/>
  <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white"/>
  <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"/>
  <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white"/>
</p>

---

## Funcionalidades

### Usuários

* Criar usuário
* Listar usuários
* Buscar usuário por ID
* Atualizar usuário
* Remover usuário

### Tarefas

* Criar tarefa
* Listar tarefas
* Filtrar tarefas por usuário
* Atualizar tarefa
* Marcar como concluída
* Deletar tarefa

---

## Modelagem

* Um usuário pode ter várias tarefas (1:N)
* Cada tarefa pertence a um único usuário
  ![Diagrama](/docs/todo-api.png)

---

##  Como rodar o projeto

### Pré-requisitos

* Docker
* Docker Compose
* Java 17+

---

### Subindo o banco de dados

```bash
docker-compose up -d
```

---

### Rodando a aplicação

```bash
./mvnw spring-boot:run
```

ou

```bash
mvn spring-boot:run
```

---

## Endpoints principais

### Usuários

* `POST /users`
* `GET /users`
* `GET /users/{id}`
* `PUT /users/{id}`
* `DELETE /users/{id}`

---

### Tarefas

* `POST /tasks`
* `GET /tasks`
* `GET /tasks?userId={id}`
* `PUT /tasks/{id}`
* `DELETE /tasks/{id}`

---

##  Tratamento de erros

A API possui tratamento global de exceções, retornando respostas padronizadas:

```json
{
  "status": "BAD_REQUEST",
  "message": "Mensagem de erro"
}
```
