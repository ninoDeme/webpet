package com.webpet.rotas;

import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.webpet.classes.RespostaHttp;
import com.webpet.classes.Rota;

public class VersaoHandler extends Rota {
    
    public VersaoHandler() {
        super("VersaoHandler");
    }

    @Override
    public RespostaHttp get(Map<String, String> _, HttpExchange pedido) {
        String response = "0.0.1";
        return new RespostaHttp(200, response);
    }
    
}
