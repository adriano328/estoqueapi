Estoque API — Java Spring + PostgreSQL + Swagger

API REST para gestão de estoque, com entidades Produto, Categoria, Lote, Usuário e Papel (Role). Construída com Spring Boot, Spring Data JPA, Spring Validation, Spring Security (roles), PostgreSQL e documentação via Swagger/OpenAPI.
Este projeto não usa Flyway, não usa Docker e não inclui suíte de testes.

Sumário
- Arquitetura & Stack
- Funcionalidades
- Modelo de Dados
- Requisitos
- Configuração
- Executando localmente
- Documentação (Swagger)
- Segurança & Perfis de Acesso
- Endpoints principais
- Seeds opcionais (data.sql)
- Padrões de código & commits
- Licença

Arquitetura & Stack
- Linguagem: Java 17+
- Framework: Spring Boot (Web, Data JPA, Validation, Security)
- Banco: PostgreSQL 14+
- Documentação: Springdoc OpenAPI (Swagger UI)
- Build: Maven (ou Gradle)

Funcionalidades
- CRUD de Categorias e Produtos
- Lotes para controle de entradas com quantidade, validade e rastreio por SKU
- Consulta de saldo em estoque por produto e por lote
- Registro de movimentações (entrada/saída) com validação de saldo (opcional)
- Autenticação e autorização por Usuário e Roles (ROLE_ADMIN, ROLE_USER)
- Paginação, ordenação e filtros básicos
- Documentação interativa via Swagger UI
