package com.wepet.classes;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RespostaHttp {
    public int codigo = 200;
    public byte[] mensagem;
    public String tipo = null;
    public Map<String, ArrayList<String>> headersMap = new HashMap<String, ArrayList<String>>();
    
    public RespostaHttp() {}
    
    public RespostaHttp(String mensagem) {
        this.mensagem = mensagem.getBytes(StandardCharsets.UTF_8);
    }

    public RespostaHttp(byte[] mensagem) {
        this.mensagem = mensagem;
    }

    public RespostaHttp code(int codigo) {
        this.codigo = codigo;
        return this;
    }
    
    public RespostaHttp tipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public RespostaHttp setHeader(String key, String value) {
        if (this.headersMap.containsKey(key)) {
            this.headersMap.get(key).addAll(Arrays.asList(value));
        } else {
            ArrayList<String> lista = new ArrayList<>();
            lista.add(value);
            this.headersMap.put(key, lista);
        }
        return this;
    }
    
    public RespostaHttp setHeader(String key, String[] value) {
        if (this.headersMap.containsKey(key)) {
            this.headersMap.get(key).addAll(Arrays.asList(value));
        } else {
            this.headersMap.put(key, new ArrayList<String>(Arrays.asList(value)));
        }
        return this;
    }

    public RespostaHttp send(String resposta) {
        this.mensagem = resposta.getBytes(StandardCharsets.UTF_8);
        return this;
    }
    public RespostaHttp send(byte[] resposta) {
        this.mensagem = resposta;
        return this;
    }
}
