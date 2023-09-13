package com.wepet.classes;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Rota
 */
public abstract class Rota implements HttpHandler {
    
    public String nome;
    public Connection conexao;
    public String secret = "ChaveSecreta";
    
    public Rota(String nome, Connection conexao) {
        this.nome = nome;
        this.conexao = conexao;
    }

    public void handle(HttpExchange exchange) throws IOException {
        System.out.println(this.getClass().getSimpleName() + ": Requisição recebida - " + exchange.getRequestURI().toString());
        String metodo = exchange.getRequestMethod();
        RespostaHttp response;

        OutputStream os = exchange.getResponseBody();
        try {
            if (metodo.equalsIgnoreCase("POST")) {
                response = post(exchange.getRequestBody().readAllBytes().toString(), exchange);
            } else {
                Map<String, String> query = queryToMap(exchange.getRequestURI().getQuery());
                response = get(query, exchange);
            }

            exchange.getResponseHeaders().set("content-type", "application/json");
            exchange.sendResponseHeaders(response.codigo, response.mensagem.length);
            try {
                os.write(response.mensagem);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            String r = "Erro Interno";
            exchange.sendResponseHeaders(500, r.length());
            os.write(r.toString().getBytes());
        } finally {
            os.close();
        }
    }

    public RespostaHttp get(Map<String, String> query, HttpExchange exchange) {
        return new RespostaHttp(404, "Rota Invalida ou Não Implementada");
    }

    public RespostaHttp post(String body, HttpExchange exchange) {
        return new RespostaHttp(404, "Rota Invalida ou Não Implementada");
    }
    
    public Usuario Auth(HttpExchange exchange) {
        String token = exchange.getRequestHeaders().getFirst("Auth");
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            JWTVerifier verifier = JWT.require(algorithm)
                // specify an specific claim validations
                .withIssuer("auth0")
                // reusable verifier instance
                .build();
                
            decodedJWT = verifier.verify(token);
            String id = decodedJWT.getPayload();
            System.out.println(id);
            throw new Error();
        } catch (JWTVerificationException exception){
            throw new Error("Não Autorizado");
        }
    }

    private Map<String, String> queryToMap(String query) {
        if (query == null) {
            return new HashMap<>();
        }
        Map<String, String> result = new HashMap<>();
        for (String param : query.split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                result.put(entry[0], entry[1]);
            } else {
                result.put(entry[0], "");
            }
        }
        return result;
    }

}