
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

    CREATE TABLE etc...
*/
