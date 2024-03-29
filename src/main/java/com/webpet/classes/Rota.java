package com.webpet.classes;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

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
    
    public Connection conexao;
    static public String secret = "ChaveSecreta";
    
    public Rota(Connection conexao) {
        this.conexao = conexao;
    }

    public Rota() {};


    public void handle(HttpExchange exchange) throws IOException {
        System.out.println(this.getClass().getSimpleName() + ": Requisição recebida - " + exchange.getRequestURI().toString());
        String metodo = exchange.getRequestMethod();
        RespostaHttp response;

        OutputStream os = exchange.getResponseBody();
        try {
            if (metodo.equalsIgnoreCase("POST")) {
                response = post(new String(exchange.getRequestBody().readAllBytes()), exchange);
            } else {
                Map<String, String> query = queryToMap(exchange.getRequestURI().getQuery());
                response = get(query, exchange);
            }

            exchange.getResponseHeaders().putAll(response.headersMap);;
            if (response.tipo != null) {
                exchange.getResponseHeaders().set("content-type", response.tipo);
            }
            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
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
        return new RespostaHttp().code(404).send("Rota Invalida ou Não Implementada");
    }

    public RespostaHttp post(String body, HttpExchange exchange) {
        return new RespostaHttp().code(404).send("Rota Invalida ou Não Implementada");
    }
    
    public Usuario Auth(HttpExchange exchange) throws AuthException {
        List<String> cookies = exchange.getRequestHeaders().get("Cookie");

        Pattern pattern = Pattern.compile("Auth=(.+?)(;|$)");
        
        String jwt = null;
        for (String cookie : cookies) {
            Matcher matcher = pattern.matcher(cookie);
            if (matcher.find()) {
                jwt = matcher.group(1);
                break;
            }
        }
        if (jwt == null) {
            throw new AuthException();
        }
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC256(Rota.secret);
            JWTVerifier verifier = JWT.require(algorithm)
                // specify an specific claim validations
                .withIssuer("WebPet")
                // reusable verifier instance
                .build();
                
            decodedJWT = verifier.verify(jwt);

            String codificado = decodedJWT.getPayload();
            String jsonString = new String(Base64.getDecoder().decode(codificado));
            JSONObject json = new JSONObject(jsonString);

            Usuario user = new Usuario();
            
            user.id = json.getInt("id");
            user.email = json.getString("email");
            user.nome = json.getString("nome");
//            user.senha = json.getString("senha");
            user.telefone = json.getString("telefone");
            
            return user;
        } catch (JWTVerificationException exception) {
            throw new AuthException();
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
