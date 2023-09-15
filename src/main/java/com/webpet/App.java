package com.webpet;

import java.io.IOException;
import java.net.InetSocketAddress;

import java.sql.*;

import com.sun.net.httpserver.*;
import com.webpet.rotas.UnicoProdutoHandler;
import com.webpet.rotas.AnimaisHandler;
import com.webpet.rotas.CadastrarHandler;
import com.webpet.rotas.CategoriasHandler;
import com.webpet.rotas.HandlerPadrao;
import com.webpet.rotas.LoginHandler;
import com.webpet.rotas.ProdutosHandler;
import com.webpet.rotas.VersaoHandler;

public class App {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

        Class.forName("org.sqlite.JDBC");

        // Inicializando servidor local na porta 9000
        // Para conectar ao servidor (local) e so acessar http://localhost:9000/
        int port = 9000;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        System.out.println("server started at " + port);

        // Cria a conexao com o banco de dados e salva na variavel conexao
        Connection conexao = App.connect();


        // Declarando as rotas do servidor
        // exemplo:
        // -> server.createContext("/version", new versaoHandler());
        // declara uma rota "/version" que pode ser acessada atravez de http://localhost:9000/version
        // ao acessar essa rota o metodo chamado handle da classe "versaoHandler" vai ser executado
        server.createContext("/", new HandlerPadrao());
        server.createContext("/version", new VersaoHandler());
        server.createContext("/produto", new UnicoProdutoHandler(conexao));
        server.createContext("/produtos", new ProdutosHandler(conexao));
        server.createContext("/categorias", new CategoriasHandler(conexao));
        server.createContext("/animal", new AnimaisHandler(conexao));
        server.createContext("/cadastrar", new CadastrarHandler(conexao));
        server.createContext("/login", new LoginHandler(conexao));



        server.setExecutor(null);
        server.start();
    }

    // Cria e retorna a conexao com o banco de dados
    private static Connection connect() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:banco.db");
            if (!connection.isValid(5)) {
                throw new Error();
            }
            ;
            System.out.println("Conexão realizada !!!!");
            return connection;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

}
