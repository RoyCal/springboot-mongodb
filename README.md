# springboot-mongodb

API REST de rede social desenvolvida com Java, Spring Boot e MongoDB.  
O projeto permite gerenciamento de usuários, posts e comentários, simulando funcionalidades básicas de uma rede social.

---

## 📌 Sobre o projeto

Esta aplicação foi desenvolvida com o objetivo de praticar conceitos de desenvolvimento backend utilizando:

- APIs RESTful
- Spring Boot
- MongoDB
- Modelagem de dados NoSQL
- Relacionamentos entre documentos
- DTOs
- Tratamento de exceções

A API permite:

- Cadastro e gerenciamento de usuários
- Criação de posts
- Comentários em posts
- Busca de informações relacionadas entre usuários e posts

---

## 🚀 Tecnologias utilizadas

- Java
- Spring Boot
- Spring Data MongoDB
- MongoDB
- Maven

---

## 📂 Estrutura do projeto

```bash
src
 └── main
     ├── java
     │    └── com
     │         └── ...
     │              ├── config
     │              ├── controller
     │              ├── dto
     │              ├── domain
     │              ├── repository
     │              ├── service
     │              └── resources
     └── resources
          └── application.properties
```

---

## ⚙️ Funcionalidades

### 👤 Usuários

- Criar usuários
- Atualizar usuários
- Remover usuários
- Buscar usuários
- Listar usuários

### 📝 Posts

- Criar posts
- Buscar posts
- Relacionar posts a usuários

### 💬 Comentários

- Adicionar comentários em posts
- Relacionar comentários aos autores

---

## 🛠️ Como executar o projeto

### Pré-requisitos

Antes de começar, você precisa ter instalado:

- Java 17+
- Maven
- MongoDB

---

### 1. Clone o repositório

```bash
git clone https://github.com/RoyCal/springboot-mongodb.git
```

---

### 2. Entre na pasta do projeto

```bash
cd springboot-mongodb
```

---

### 3. Configure o MongoDB

No arquivo `application.properties`, configure a URI do banco:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/workshop_mongo
```

---

### 4. Execute a aplicação

```bash
mvn spring-boot:run
```

A API ficará disponível em:

```text
http://localhost:8080
```

---

## 📬 Principais endpoints

### Usuários

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/users` | Lista todos os usuários |
| GET | `/users/{id}` | Busca usuário por ID |
| POST | `/users` | Cria um usuário |
| PUT | `/users/{id}` | Atualiza um usuário |
| DELETE | `/users/{id}` | Remove um usuário |

---

### Posts e comentários

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/posts` | Busca todos os posts |
| POST | `/posts` | Insere um post |
| DELETE | `/posts/{id}` | Deleta um post |
| GET | `/posts/{id}` | Busca post por ID |
| GET | `/posts/titlesearch` | Busca posts por título |
| GET | `/posts/fullsearch` | Busca posts com filtros |
| GET | `/posts/{id}/comments` | Busca todos os comentários do post |
| POST | `/posts/{id}/comments` | Insere um comentário no post |
| DELETE | `/posts/{postId}/comments/{commentId}` | Deleta um comentário do post |

---

## 🧠 Conceitos abordados

- Arquitetura REST
- CRUD com MongoDB
- Relacionamentos entre documentos
- DTO Pattern
- Camadas Controller, Service e Repository
- Tratamento de exceções personalizado
- Consultas personalizadas no MongoDB
- Serialização JSON

---

## 📖 Referências

- Spring Boot
- Spring Data MongoDB
- MongoDB Documentation

---

## 👨‍💻 Autor

Desenvolvido por [RoyCal](https://github.com/RoyCal)

---

## 📄 Licença

Este projeto está sob a licença MIT.
