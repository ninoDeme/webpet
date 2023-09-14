package com.wepet.rotas;

import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.wepet.classes.RespostaHttp;
import com.wepet.classes.Rota;

public class VersaoHandler extends Rota {
    
    public VersaoHandler() {
        super("VersaoHandler");
    }

    @Override
    public RespostaHttp get(Map<String, String> query, HttpExchange pedido) {
        String response = "0.0.1";
        return new RespostaHttp(response);
    }
    
}
