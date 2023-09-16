package com.webpet.classes;
import org.json.JSONObject;

public class Usuario {
    public int id;
    public String nome;
    public String email;
    public String senha;
    public String telefone;
    public int id_venda;
    public int id_usuario;
    public String cep;
    public String tempo_entrega;
    public String data_horario;

    
    public String toJSON() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("nome", nome);
        json.put("email", email);
        json.put("senha", senha);
        json.put("telefone", telefone);
        json.put("id_venda",id_venda);
        json.put("id_usuario",id_usuario);

        return json.toString();
    }
}
