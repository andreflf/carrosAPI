# 🚗 carrosAPI

API RESTful desenvolvida em **Java com Spring Boot**, responsável por gerenciar o backend da aplicação de cadastro e controle de carros, marcas e acessórios.  
O projeto implementa autenticação via **JWT**, controle de permissões, e CRUD completo com **Spring Data JPA**.

## 🧰 Tecnologias e Dependências Principais

- **Java 17+**
- **Spring Boot**
  - Spring Web
  - Spring Data JPA
  - Spring Security + JWT
  - Validation (Jakarta Validation)
- **Banco de Dados:** MySQL
- **Maven**
- **Lombok**
- **ModelMapper**


## 🔐 Segurança

O sistema implementa autenticação e autorização baseadas em **JWT (JSON Web Token)**.  
- Usuários possuem **roles**: `ADMIN` ou `USER`.
- Apenas usuários com **`ADMIN`** podem acessar alguns endpoints (via `@PreAuthorize("hasAuthority('ADMIN')")`).
- Senhas devem ser criptografadas com BCrypt antes de inserção no banco (por exemplo, via [bcrypt-generator.com](https://bcrypt-generator.com/)).


## 🧩 Modelagem e Relacionamentos
- **Marca** 1️⃣ ➜ 🔁 **Carro**  
  Uma marca pode ter vários carros (relacionamento `@OneToMany`).

- **Carro** 🔁 **Acessório**  
  Um carro pode ter vários acessórios e vice-versa (`@ManyToMany`).

## ⚙️ Endpoints Principais

### 🔸 Autenticação
| Método | Endpoint | Descrição |
|--------|-----------|-----------|
| `POST` | `/api/login` | Autentica o usuário e gera o token JWT |

### 🔸 Carros, Marcas e Acessórios (possuem a mesma estrutura nas controllers)
| Método | Endpoint | Descrição |
|--------|-----------|-----------|
| `GET` | `/api/carros/findAll` | Lista todos os carros |
| `GET` | `/api/carros/findById/{id}` | Busca carro específico |
| `POST` | `/api/carro/save` | Adiciona novo carro (`ADMIN` apenas) |
| `PUT` | `/api/carros/update/{id}` | Atualiza um carro (`ADMIN` apenas)|
| `DELETE` | `/api/carros/deleteById{id}` | Remove um carro (`ADMIN` apenas) |

## 🚀 Como Executar
1. Clone o repositório:
   ```bash
   git clone https://github.com/andreflf/carrosAPI.git
   
Acesse a pasta:
cd carrosAPI

Compile e rode:
mvn spring-boot:run

API disponível em:
http://localhost:8080

🧠 Futuras Implementações:
 Tela de registro de usuários (no frontend),
 Refresh token JWT,
 Migração para banco externo (PostgreSQL ou MySQL) e
 Testes unitários e de integração.

👨‍💻 Autor: André Fialho -
Desenvolvedor Java | Spring Boot | Angular
🔗 LinkedIn:[in/andrefialho22](https://www.linkedin.com/in/andrefialho22/) 
