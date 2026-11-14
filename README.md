# 📋 TaskManager

> API REST para gerenciamento de tarefas, desenvolvida com Java e Spring Boot.

![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15+-4169E1?logo=postgresql)
![Maven](https://img.shields.io/badge/Maven-3.9+-C71A36?logo=apachemaven)
![Status](https://img.shields.io/badge/Status-Em%20desenvolvimento-yellow)

---

## 📖 Sobre o projeto

O **TaskManager** é uma API REST desenvolvida para facilitar o gerenciamento e a organização de tarefas.

O projeto tem como objetivo oferecer uma estrutura para criação, consulta, atualização e exclusão de tarefas, utilizando tecnologias do ecossistema Java e Spring.

A aplicação utiliza uma arquitetura organizada em camadas, integração com banco de dados relacional e boas práticas de desenvolvimento backend, proporcionando uma base escalável e de fácil manutenção.

## ✨ Funcionalidades

- [X] Estrutura inicial da API REST
- [X] Operações CRUD de tarefas
- [X] Consulta de tarefas por identificador
- [X] Filtro de tarefas por status
- [X] Filtro por prioridade
- [X] Filtro por período
- [X] Validação de dados com Bean Validation
- [X] Tratamento global de exceções
- [X] Documentação da API com Swagger/OpenAPI
- [X] Testes automatizados

> Atualize os indicadores conforme as funcionalidades realmente implementadas.

## 🛠️ Tecnologias utilizadas

| Tecnologia | Versão | Finalidade |
|---|---|---|
| Java | 21 (LTS) | Linguagem principal |
| Spring Boot | 3.x | Framework backend |
| Spring Web | — | Construção da API REST |
| Spring Data JPA | — | Persistência de dados |
| Hibernate | — | Mapeamento objeto-relacional |
| PostgreSQL | 15+ | Banco de dados |
| Maven | 3.9+ | Gerenciamento de dependências |
| Lombok | — | Redução de código repetitivo |
| JUnit 5 | — | Testes automatizados |

## 🏗️ Arquitetura do projeto

O projeto segue uma arquitetura em camadas, separando as responsabilidades para facilitar a manutenção e evolução da aplicação.

```text
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── taskmanager/
│   │           ├── controller/
│   │           ├── service/
│   │           ├── repository/
│   │           ├── model/
│   │           ├── dto/
│   │           ├── exception/
│   │           └── TaskManagerApplication.java
│   │
│   └── resources/
│       └── application.properties
│
└── test/
    └── java/
```

**Responsabilidades das camadas:**

- **Controller:** recebe as requisições HTTP e disponibiliza os endpoints.
- **Service:** concentra as regras de negócio da aplicação.
- **Repository:** realiza as operações de persistência utilizando Spring Data JPA.
- **Model:** representa as entidades persistidas no banco de dados.
- **DTO:** define os objetos utilizados na comunicação entre cliente e API.
- **Exception:** centraliza o tratamento de exceções.

## 🚀 Como executar o projeto

### Pré-requisitos

Antes de executar a aplicação, certifique-se de possuir:

- Java JDK 21 ou superior.
- Maven 3.9 ou superior.
- PostgreSQL 15 ou superior.
- Git.

### 1. Clone o repositório

```bash
git clone https://github.com/SEU-USUARIO/taskmanager.git
```

### 2. Acesse o diretório

```bash
cd taskmanager
```

### 3. Configure o banco de dados

Crie um banco de dados PostgreSQL:

```sql
CREATE DATABASE taskmanager;
```

Configure as credenciais no arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/taskmanager
spring.datasource.username=${DB_USERNAME:postgres}
spring.datasource.password=${DB_PASSWORD:postgres}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 4. Execute a aplicação

Utilizando Maven:

```bash
mvn spring-boot:run
```

Ou compile o projeto:

```bash
mvn clean package
```

Execute o JAR gerado:

```bash
java -jar target/taskmanager-*.jar
```

A aplicação estará disponível em:

```text
http://localhost:8080
```

## 📡 Endpoints da API

Os endpoints abaixo representam uma proposta de organização para o CRUD de tarefas. Ajuste os caminhos conforme a implementação real.

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/tasks` | Lista todas as tarefas |
| GET | `/tasks/{id}` | Busca uma tarefa por ID |
| POST | `/tasks` | Cadastra uma nova tarefa |
| PUT | `/tasks/{id}` | Atualiza uma tarefa |
| DELETE | `/tasks/{id}` | Remove uma tarefa |

### Exemplo de requisição

**POST /tasks**

```json
{
  "title": "Estudar Spring Boot",
  "description": "Revisar conceitos de Spring Data JPA",
  "status": "PENDING"
}
```

**Exemplo de resposta:**

```json
{
  "id": 1,
  "title": "Estudar Spring Boot",
  "description": "Revisar conceitos de Spring Data JPA",
  "status": "PENDING"
}
```

*Os campos e formatos são ilustrativos e devem refletir os DTOs reais da aplicação.*

## 🧪 Testes

Para executar os testes automatizados:

```bash
mvn test
```

Para executar os testes e gerar o pacote:

```bash
mvn clean verify
```

## 📌 Próximas melhorias

- Implementação de filtros dinâmicos.
- Inclusão de paginação e ordenação.
- Ampliação da cobertura de testes.
- Containerização da aplicação com Docker.
- Implementação de autenticação e autorização.

## 📚 Conceitos aplicados

Durante o desenvolvimento, são explorados conceitos importantes do desenvolvimento backend:

- Desenvolvimento de APIs REST.
- Arquitetura em camadas.
- Injeção de dependências.
- Programação orientada a objetos.
- Persistência de dados com JPA/Hibernate.
- Mapeamento objeto-relacional (ORM).
- Tratamento de exceções.
- Validação de dados.
- Boas práticas de organização e manutenção de código.

## 👨‍💻 Autor

**WEVERTON MARTINS**

- GitHub: [@weverton.msilva](https://github.com/weverton.msilva)

---

<p align="center">
  Desenvolvido com Java e Spring Boot.
</p>
