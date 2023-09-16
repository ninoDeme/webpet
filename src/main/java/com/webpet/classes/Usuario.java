package com.webpet.classes;

import org.json.JSONObject;

public class Usuario {
    public int id;
    public String nome;
    public String email;
    public String senha;
    public String telefone;

    public String toJSON() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("nome", nome);
        json.put("email", email);
        json.put("senha", senha);
        json.put("telefone", telefone);

        return json.toString();
    }
}
