create schema if not exists users;

CREATE TABLE users.user (
      id SERIAL primary key,
      nome VARCHAR(100) not null,
      cpf VARCHAR(100) not null,
      endereco VARCHAR(100) not null,
      email VARCHAR(100) not null,
      telefone VARCHAR(20) not null,
      data_cadastro TIMESTAMP not null
);