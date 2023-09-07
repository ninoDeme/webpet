
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
CREATE TABLE ANIMAL(
    id_animal INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL, 
    descricao TEXT, 
    imagem TEXT
    );
        CREATE TABLE categoria(
        id_categoria INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
        nome TEXT NOT NULL,
        descricao TEXT
    );
CREATE TABLE fabricante(
    id_fabricante INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    nome TEXT NOT NULL,
    descricao TEXT,
    logo TEXT NOT NULL
    );