package com.webpet.rotas;

import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.webpet.classes.RespostaHttp;
import com.webpet.classes.Rota;

public class LogoutHandler extends Rota {
    @Override
    public RespostaHttp get(Map<String, String> query, HttpExchange pedido) {
        return new RespostaHttp("Des-logado com sucesso").setHeader("Set-Cookie", "Auth=none; Max-Age=0");
    }
}
