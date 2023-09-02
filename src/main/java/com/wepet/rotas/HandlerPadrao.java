package com.wepet.rotas;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class HandlerPadrao implements HttpHandler {

    @Override
    public void handle(HttpExchange pedido) throws IOException {
        System.out.println((this.getClass().getSimpleName()) +": Requisição recebida - " + pedido.getRequestURI().toString());

        String response = "Erro 404";
        
        pedido.sendResponseHeaders(404, response.length());
        OutputStream os = pedido.getResponseBody();
        os.write(response.toString().getBytes());
        os.close();
    }

}
