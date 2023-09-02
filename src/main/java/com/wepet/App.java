package com.wepet;

import java.io.IOException;
import java.net.InetSocketAddress;

import java.sql.*;

import com.sun.net.httpserver.*;
import com.wepet.rotas.InserirProdutoHandler;
import com.wepet.rotas.ProdutosHandler;
import com.wepet.rotas.HandlerPadrao;
import com.wepet.rotas.versaoHandler;

public class App {
    public static void main(String[] args) throws IOException, SQLException {

        int port = 9000;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        System.out.println("server started at " + port);
        Connection conexao = App.connect();

        server.createContext("/", new HandlerPadrao());
        server.createContext("/version", new versaoHandler());
        server.createContext("/adicionarproduto", new InserirProdutoHandler(conexao));
        server.createContext("/produtos", new ProdutosHandler(conexao));


        server.setExecutor(null);
        server.start();
    }

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
