/*
 Arquivo com as instrucoes para recriar o banco de dados
 Sempre ao rodar qualquer comando que cria ou modifica uma tabela salve ele aqui

 exemplo:

 -- criar tabela Usuario

 CREATE TABLE usuario (
 id_usuario INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, -- id do usuario, numero nao nulo chave primaria que vai ser usado como identificador unico
 nome TEXT NOT NULL UNIQUE, -- nome do usuario, string nao nula unica
 senha TEXT NOT NULL, -- senha do usuario, string nao nula
 email TEXT NOT NULL UNIQUE, -- email do usuario, string nao nula unica
 foto TEXT, -- foto de perfil do usuario, caminho da foto no servidor ex: C:/imagens/a23SDq2d2.png
 );

 CREATE TABLE favoritos_usuario (
 id_usuario   INTEGER PRIMARY KEY,
 id_produto TEXT    NOT NULL,
 tipo      INTEGER NOT NULL,
 FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario)
 FOREIGN KEY (id_produto) REFERENCES produto (id_produto)
 );
 */
create table usuario(
    id_usuario INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    senha NOT NULL,
    email TEXT NOT NULL UNIQUE,
    telefone TEXT NOT NULL
);

CREATE TABLE animal(
    id_animal INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    descricao TEXT,
    imagem TEXT
);

CREATE TABLE categoria(
    id_categoria INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    nome TEXT NOT NULL,
    imagem TEXT
);

CREATE table produto(
    nome TEXT NOT NULL,
    descricao TEXT,
    detalhes TEXT,
    peso DOUBLE,
    preco DOUBLE,
    quantidade INTEGER NOT NULL,
    id_animal INTEGER,
    id_categoria INTEGER,
    id_produto INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    FOREIGN KEY(id_animal) REFERENCES animal(id_animal),
    FOREIGN KEY(id_categoria) REFERENCES categoria(id_categoria)
);

CREATE TABLE imagens_produto(
    id_produto integer NOT NULL,
    caminho TEXT NOT NULL,
    descricao TEXT,
    FOREIGN KEY(id_produto)REFERENCES produto(id_produto)
);


CREATE TABLE  venda(
    id_venda INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    id_produto INTEGER NOT NULL,
    id_usuario INTEGER NOT NULL,
    data_horario TEXT NOT NULL,
    FOREIGN KEY(id_produto) REFERENCES produto(id_produto),
    FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario)
);


