package com.webpet.classes;

import java.nio.charset.StandardCharsets;

public class RespostaHttp {
    int codigo;
    byte[] mensagem;
    String tipo = null;
    
    public RespostaHttp(int codigo, String resposta) {
        this.codigo = codigo;
        this.mensagem = resposta.getBytes(StandardCharsets.UTF_8);
    }

    public RespostaHttp(int codigo, byte[] resposta) {
        this.codigo = codigo;
        this.mensagem = resposta;
    }
    public RespostaHttp(int codigo, String resposta, String tipo) {
        this.codigo = codigo;
        this.mensagem = resposta.getBytes(StandardCharsets.UTF_8);
        this.tipo = tipo;
    }

    public RespostaHttp(int codigo, byte[] resposta, String tipo) {
        this.codigo = codigo;
        this.mensagem = resposta;
        this.tipo = tipo;
    }
}
