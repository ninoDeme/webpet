package com.wepet.rotas;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class HandlerPadrao implements HttpHandler {

    @Override
    public void handle(HttpExchange pedido) throws IOException {
        System.out.println(
                this.getClass().getSimpleName() + ": Requisição recebida - " + pedido.getRequestURI().toString());

        Path path;
        try {
            path = Paths.get(getClass().getClassLoader().getResource("404.html").toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            String response = "Erro 404";
            pedido.sendResponseHeaders(404, response.length());
            OutputStream os = pedido.getResponseBody();
            os.write(response.toString().getBytes());
            os.close();
            return;
        }

        Stream<String> lines = Files.lines(path);
        String data = lines.collect(Collectors.joining("\n"));
        lines.close();

        String response = data.trim();

        pedido.sendResponseHeaders(404, response.length());
        OutputStream os = pedido.getResponseBody();
        os.write(response.toString().getBytes());
        os.close();
    }

}
