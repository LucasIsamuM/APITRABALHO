# Projeto Gerenciamento de Reservas de Hotel - SyncStay - Backend (trabalho-api)

## Descrição

O Sistema de Gerenciamento de Hotel SyncStay é uma aplicação web com frontend em React e backend em Java com Spring Boot, utilizando PostgreSQL para armazenamento de dados. O backend permite gerenciar quartos, reservas e hóspedes, fornecendo funcionalidades de cadastro, atualização, consulta e gerenciamento de disponibilidade de quartos.

No momento, a aplicação possui o módulo de Quartos, com endpoints para criar, atualizar, listar, deletar e verificar a disponibilidade de quartos, além de testes unitários já implementados.

## Tecnologias Utilizadas

- Java 25
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Mockito (para testes unitários)

## Pré-requisitos

- JDK 25
- Maven 4.x ou superior
- PostgreSQL configurado

## Instalação

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/LucasIsamuM/APITRABALHO.git
   ```

2. **Navegue até o diretório do projeto:**

   ```bash
   cd diretorio-do-projeto
   ```

3. **Configure o banco de dados:**

   Edite o arquivo [application.yaml](src/main/resources/application.yaml) com as configurações do seu banco de dados.

4. **Compile e execute o projeto:**

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

   A API estará disponível em `http://localhost:8080`.

## Documentação da API (Swagger)

A documentação da API pode ser acessada por meio do Swagger. Após iniciar o backend, você pode acessar a documentação por meio da seguinte URL:

[/swagger-ui/index.html](http://localhost:8080//swagger-ui/index.html)

## Endpoints

Abaixo está a descrição dos principais endpoints da API:

### **1. GET /Quarto**

- **Descrição:** Retorna uma lista de todos os quartos cadastrados.
  - **Resposta:**
    - **200 OK**
  ```json
    [
      {
        "id": 1,
        "numero": 101,
        "disponivel": true 
      },
      {
        "id": 2,
        "numero": 102,
        "disponivel": false
      }
    ]
    ```

### **2. /Quarto/{id}**

- **Descrição:** 
- **Parâmetros de Caminho:** 
  - id: ID do quarto
  - **Corpo da Requisição:**
    ```json
    {
      "id": 1,
      "numero": 101,
      "disponivel": true
    }
    ```
- **Resposta** 404 Not Found se o quarto não for encontrado.

### **3. POST /Quarto**

- **Descrição:** Cria um novo quarto.
- **Corpo da Requisição:**
  ```json
    {
      "numero": 103,
      "disponivel": true
    }
   ```
- **Resposta:**
  ```json
    {
      "id": 3,
      "numero": 103,
      "disponivel": true
    }
   ```

### **4. PUT /Quarto/{id}/atualizar/**

- **Descrição:** Atualiza um quarto existente.
- **Parâmetros de Caminho:**
  - **id:** ID do quarto
- **Corpo da Requisição:**
```json
    {
        "numero": 101,
        "disponivel": false
    }
```
- **Resposta:**
  - 200 OK
```json
    {
        "id": 1,
        "numero": 101,
        "disponivel": false
    }
```

### **5. DELETE /Quarto/{id}/deletar/**

- **Descrição:** Remove um quarto pelo ID.
  - **Parâmetros de Caminho:**
    - **id:** ID do quarto

- **Resposta:**
  - 204 No Content
  - 404 Not Found se o quarto não for encontrado.

### **6. GET /Quarto/{id}/disponibilidade**
- **Descrição:** Verifica se um quarto está disponível.
- **Parâmetros de Caminho:**
  - *id:* ID do quarto

- **Resposta:**
  - 200 OK
```json
"O quarto está disponível"
```
ou
```json
"O quarto está ocupado"
```

### **7. PUT /Quarto/{id}/disponibilidade/{status}**
- **Descrição:** Atualiza a disponibilidade de um quarto.
- **Parâmetros de Caminho:**
  - **id:** ID do quarto
  - **status:** *true* (disponível) ou *false* (ocupado)

- **Resposta:**
  - 200 OK
```json
    {
        "id": 1,
        "numero": 101,
        "disponivel": true
    }
```

