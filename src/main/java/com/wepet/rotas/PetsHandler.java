package com.wepet.rotas;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class PetsHandler implements HttpHandler {

    Connection conexao = null;

    public PetsHandler(Connection conexao) {
        this.conexao = conexao;
    }
    public void handle(HttpExchange pedido) throws IOException {
        String response = null;
        int codigo = 500;
        try {
            ArrayList<Pets> pets = new ArrayList<Pets>();
            String sql = "select * from pets";
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ResultSet resultados = ps.executeQuery();

            boolean condicao = resultados.next();
;
            while (condicao) {
                String nome = resultados.getString("nome");
                int id = resultados.getInt("id");
                
                pets.add(new Pets(nome,id));
                condicao = resultados.next();

            }
            response = "{\"resultado\": [";

            for (int i = 0; i < pets.size(); i++) {
                response += pets.get(i).toJSON();
                if (i < pets.size() - 1) {
                    response += ",";
                }
            }
            response += "]}";
            codigo = 200;

        }  catch (Exception e) {
            e.printStackTrace();
        } 
        byte[] bytes = response.getBytes();
        pedido.getResponseHeaders().set("content-type", "application/json");
        pedido.sendResponseHeaders(codigo, bytes.length);
        OutputStream os = pedido.getResponseBody();
        
        try {
            os.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        os.close();
    }
}