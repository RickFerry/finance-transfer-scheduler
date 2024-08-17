
---

# Financial Transfer Scheduler API

## Descrição

Esta aplicação é uma API para agendamento de transferências financeiras. O sistema permite que os usuários agendem transferências financeiras entre contas, calcula as taxas aplicáveis com base na data da transferência e fornece um extrato de todos os agendamentos cadastrados. A API foi desenvolvida utilizando **Java** e **Spring Boot**, com persistência em um banco de dados em memória **H2**.

## Tecnologias Utilizadas

- **Java 11**
- **Spring Boot 2.6.0**
- **Spring Data JPA**
- **Spring Validation**
- **H2 Database**
- **Lombok**

## Estrutura do Projeto

```plaintext
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           └── financescheduler/
│   │               ├── controller/
│   │               │   └── TransferController.java
│   │               ├── model/
│   │               │   └── Transfer.java
│   │               ├── repository/
│   │               │   └── TransferRepository.java
│   │               └── service/
│   │                   └── TransferService.java
│   └── resources/
│       ├── application.yml
│       └── data.sql
└── test/
    └── java/
        └── com/
            └── example/
                └── financescheduler/
                    ├── TransferControllerTests.java
                    └── TransferServiceTests.java
```

## Configuração do Projeto

### Dependências do `pom.xml`

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

### Arquivo `application.yml`

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:finance-db
    driverClassName: org.h2.Driver
    username: sa
    password: 
  h2:
    console:
      enabled: true
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    database-platform: org.hibernate.dialect.H2Dialect
```

## Executando a Aplicação

### Pré-requisitos

- **Java 11**
- **Maven** instalado

### Passos para Executar

1. **Clone o repositório**:
   ```bash
   git clone <URL-do-repositorio>
   cd financescheduler
   ```

2. **Compile o projeto**:
   ```bash
   mvn clean install
   ```

3. **Execute a aplicação**:
   ```bash
   mvn spring-boot:run
   ```

4. **Acesse o console do H2**:
    - URL: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
    - **JDBC URL**: `jdbc:h2:mem:finance-db`
    - **Usuário**: `sa`
    - **Senha**: 

## Endpoints da API

### 1. Agendar Transferência

- **URL**: `POST /api/transfers`
- **Descrição**: Agenda uma transferência financeira.
- **Request Body**:
  ```json
  {
    "originAccount": "1234567890",
    "destinationAccount": "0987654321",
    "amount": 100.00,
    "transferDate": "2024-08-20",
    "schedulingDate": "2024-08-15"
  }
  ```
- **Respostas**:
    - **201 Created**: Transferência agendada com sucesso.
    - **400 Bad Request**: Dados inválidos ou taxa não aplicável.

### 2. Listar Todas as Transferências

- **URL**: `GET /api/transfers`
- **Descrição**: Recupera a lista de todas as transferências agendadas.
- **Resposta**:
  ```json
  [
    {
      "id": 1,
      "originAccount": "1234567890",
      "destinationAccount": "0987654321",
      "amount": 100.00,
      "fee": 8.2,
      "transferDate": "2024-08-20",
      "schedulingDate": "2024-08-15"
    }
  ]
  ```

## Testes

Para rodar os testes da aplicação, utilize o comando:

```bash
mvn test
```

## Diagrama UML

Abaixo está o diagrama UML representando os principais componentes do sistema:

```plaintext
+-----------------------+
|     Transfer          |
|-----------------------|
| - Long id             |
| - String originAccount|
| - String destAccount  |
| - Double amount       |
| - Double fee          |
| - LocalDate transferDate|
| - LocalDate schedDate |
+-----------------------+
          |
          v
+-----------------------+
|     TransferService   |
|-----------------------|
| + scheduleTransfer()  |
| + listAllTransfers()  |
+-----------------------+
          |
          v
+-----------------------+
|     TransferController|
|-----------------------|
| + scheduleTransfer()  |
| + listTransfers()     |
+-----------------------+
```

## Melhorias Futuras

- **Tratamento de Exceções**: Implementar um tratamento de exceções mais robusto com `@ControllerAdvice`.
- **Validação**: Adicionar mais validações para garantir a integridade dos dados.
- **Logging**: Integrar logging para capturar e monitorar o comportamento da aplicação.

## Licença

Este projeto é licenciado sob os termos da licença MIT. Consulte o arquivo [LICENSE](LICENSE) para mais detalhes.

---
