# Desafio Técnico

[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

Projeto criado usando [Spring Boot](http://projects.spring.io/spring-boot/) framework

## Requisitor

For building and running the application you need:

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven 3](https://maven.apache.org)

## Executando a aplicação localmente

Existem várias maneiras de executar o aplicativo Spring Boot em sua máquina local. Uma maneira é executar o método `main` na classe `br.com.heitor.nuclea.NucleaApplication` do seu IDE.


Alternativamente, você pode usar o  [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html):

```shell
./mvnw spring-boot:run
```

## Executando os testes

```shell
./mvnw test
```

## Database

O banco de dados usado para este desafio é o Postgres

## Postman

Existe uma coleção Postman para fins de teste

src/main/resources/collections/Nuclea.postman_collection.json

## Especificação do desafio

Oportunidade: Desenvolvedor Java Sênior- Nuclea
Desafio Técnico
-------------------------------------------------------------------------------------------------------------------------
Sistema para gerenciar os dados do portfólio de projetos de uma empresa. Entenda como portfólio de projetos o conjunto de projetos da empresa, tanto em andamento como em análise de viabilidade
Regras de implementação:

- Persistir as informações no BD enviado;
- O candidato deve definir qual versão do Java utilizar;
- O candidato deve definir quais frameworks utilizar;
- Utilizar testes unitários para avaliar as regras;
- O Código deve estar funcional atendendo aos requisitos de negócio, com Front-end, Back-end, persistência em banco de dados e testes unitários.

Regras de negócio:
- O sistema deve permitir o cadastro de PROJETOS (inserção, exclusão, alteração e consulta).
- O PROJETO deve conter as seguintes informações:
- Nome;
- Data de início;
- Gerente responsável;
- Previsão de término;
- Data real de término;
- Orçamento total;
- Descrição;
- Status;
- Risco.
- O risco deve ser classificado em: baixo, médio e alto.
- O projeto deve estar em um status específico e único. Os status possíveis são:
- em análise;
- análise realizada;
- análise aprovada;
- iniciado;
- planejado;
- em andamento;
- encerrado;
- cancelado.
- O Projeto NÃO pode ser excluído quando estiver com status:
- Iniciado
- Em andamento
- Encerrado
- Associar membros ao projeto, esta funcionalidade deve ser provida via web service;
- O Projeto só deve permitir associar membros que sejam funcionários;

Entrega:
- O teste deverá ser enviado em um e-mail contendo o link para o repositório deste projeto em sua conta pessoal no GitHub.

Script do banco de dados:
● Este script criará as tabelas necessárias.
● A estrutura criada não deve ser alterada!

```
-- ----------------------------------------------------- 
-- Table Pessoa
-- ----------------------------------------------------- 
CREATE TABLE pessoa (
id bigserial NOT NULL,
nome character varying(100) NOT NULL,
datanascimento date,
cpf character varying(14),
funcionario boolean,
CONSTRAINT pk_pessoa PRIMARY KEY (id));
-- ----------------------------------------------------- 
-- Table Projeto
-- -----------------------------------------------------
CREATE TABLE projeto (
id bigserial NOT NULL,
nome VARCHAR(200) NOT NULL,
data_inicio DATE ,
data_previsao_fim DATE ,
data_fim DATE ,
descricao VARCHAR(5000) ,
status VARCHAR(45) ,
orcamento FLOAT ,
risco VARCHAR(45) ,
idgerente bigserial NOT NULL,
CONSTRAINT pk_projeto PRIMARY KEY (id),
CONSTRAINT fk_gerente FOREIGN KEY (idgerente)
REFERENCES pessoa (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION);
-- ----------------------------------------------------- 
-- Table Membros
-- ----------------------------------------------------- 
CREATE TABLE membros (
idprojeto bigserial NOT NULL,
idpessoa bigserial NOT NULL,
CONSTRAINT pk_projeto_pessoa PRIMARY KEY (idprojeto,idpessoa),
CONSTRAINT fk_membros_projeto FOREIGN KEY (idprojeto) REFERENCES projeto (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
CONSTRAINT fk_membros_pessoa FOREIGN KEY (idpessoa) REFERENCES pessoa (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION);
```


## Copyright

Released under the Apache License 2.0.