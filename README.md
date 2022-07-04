# Redis Cache - SpringBoot

Projeto com o intuito em demonstrar utilização do Redis como cache de para apoiar processo de alto volume de informações
junto ao SpringBoot.
---
Project to demonstrate the use of Redis as a cache to support high volume information processing next to SpringBoot

## Sobre o projeto / About the project

O projeto foi concebido para tomar como base a geração de cache proveniente consulta a uma base dados que será 
gerenciada pelo Redis. Algumas abordagens da ferramenta será abordada para demonstrar conceitos bases do cache.
---
The project was conceived to be based on the generation of cache coming from a query to a database that will be
managed by Redis. Some approaches of the tool will be addressed to demonstrate basic concepts of the cache.


### Requisitos para executar o projeto / Requirements to run the project

- JDK 17
- Maven 3+

## Orientações / Guidelines

Faça um fork do projeto, clone o mesmo, abra na sua IDE de preferência e execute na sua máquina local.

<b>Observações:</b> Se faz necessário ter o <b>docker</b> instalado no ambiente, visto que, o redis juntamente com a sua 
ferramenta de visualização e banco de dados, atualmente estão alocados em containers.

---

Fork the project, clone it, open it in your IDE of choice and run it on your local machine.

<b>Observations:</b> It is necessary to have <b>docker</b> installed in the environment, since redis together with its
visualization tool and database, are currently allocated in containers.


### Executando / Execution

O docker-compose contém as seguintes aplicações:
  - Postgres
  - Redis
  - RedisInsight (GUI)

É importante ressaltar que o banco não irá possuir massa inicial, caso seja executado pela primeira vez.

<p>
A aplicação poderá ser acessada em: <b>http://localhost:8080/catalogo</b>

Caso haja interesse de inserção simulando um lote, basta acessar <b>/lote</b>. Ele irá inserir 50.000 registros na base 
de dados (Postgres).
</p>

Em relação ao Redis, ele poderá ser acessado em: <b>http://localhost:8001</b>

O acesso a base de dados do Redis é feita pelo nome definido no docker-compose juntamente com informações que achar 
necessária.

---

The docker-compose contains the following applications:
- Postgres
- Redis
- RedisInsight (GUI)

It is important to note that the bank will not have initial mass, if it is executed for the first time.

<p>
The application can be accessed at: <b>http://localhost:8080/catalogo</b>

If you are interested in inserting simulating a batch, just access <b>/lote</b>. It will insert 50,000 records into the base
of data (Postgres).
</p>

<p>
Regarding Redis, it can be accessed at: <b>http://localhost:8001</b>

Access to the Redis database is done by the name defined in docker-compose along with information you find
necessary.
</p>

**Atenção** : Não inclua nenhuma lib ou framework


## Alterações no projeto / Project changes

O conceito usado foi **Responsabilidade por Camada**. Desta forma, a implementação será baseada em sua finalidade técnica. 
Segue:

- **Model** - Pacote de modelagem de negócio
- **DTO** - Objeto de Transferência de Dados
  - **Mapper** - Conversão DTO <--> Model
- **Repository** - Comunicação com banco de dados
- **Service** - Serviços para atender às especificações do pacote: spring-security-core
- **Controller** - Interceptadores de requisição HTTP
- **Config** - Componentes de configurações da aplicação

The concept used was **Layer Responsibility**. In this way, the implementation will be based on its technical purpose.
He follows:

- **Model** - Business Modeling Package
- **DTO** - Data Transfer Object
  - **Mapper** - Conversion DTO <--> Model
- **Repository** - Communication with database
- **Service** - Services to meet package specifications: spring-security-core
- **Controller** - HTTP request interceptors
- **Config** - Application settings components

### Testes integrados / Integrated Tests

Para este projeto, os testes integrados via Postman disponibilizados através de um arquivo que contém requisições, 
conteúdo e outros. Os testes estão disponíveis em uma pasta chamada collections_integrated_tests no projeto.
<br /> <br />
For this project, the integrated tests via Postman made available through a file that contains requests,
content and others. Tests are available in a folder called collections_integrated_tests in the project.