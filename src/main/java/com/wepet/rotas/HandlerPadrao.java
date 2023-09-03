package com.wepet.rotas;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class HandlerPadrao implements HttpHandler {

    @Override
    public void handle(HttpExchange pedido) throws IOException {
        System.out.println(
                this.getClass().getSimpleName() + ": Requisição recebida - " + pedido.getRequestURI().toString());

        OutputStream os = pedido.getResponseBody();
        try {
            InputStream f = getFileFromResourceAsStream("404.html");
            byte[] data = f.readAllBytes();

            pedido.sendResponseHeaders(404, data.length);
            os.write(data);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
            String response = "Erro 404";
            pedido.sendResponseHeaders(404, response.length());
            os.write(response.toString().getBytes());
        } finally {
            os.close();
        }
    }

    // get a file from the resources folder
    // works everywhere, IDEA, unit test and JAR file.
    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }
}
