package com.wepet;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.*;
import com.wepet.rotas.HandlerPadrao;
import com.wepet.rotas.versaoHandler;

public class App {
    public static void main(String[] args) throws IOException {

        int port = 9000;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        System.out.println("server started at " + port);
        server.createContext("/", new HandlerPadrao());
        server.createContext("/version", new versaoHandler());

        server.setExecutor(null);
        server.start();
    }
}
