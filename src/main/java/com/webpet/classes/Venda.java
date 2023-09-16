package com.webpet.classes;
import org.json.JSONObject;

public class Venda {
    int id;
    int id_venda;
    int id_usuario;
    String data_horario;
    String tempo_entrega;
    String cep;
    
    

    public Venda(int id, int id_venda, int id_usuario, String data_horario,String tempo_entrega,String cep) {
        this.id = id;
        this.id_venda = id_venda;
        this.id_usuario = id_usuario;
        this.data_horario = data_horario;
        this.tempo_entrega = tempo_entrega;
        this.cep = cep;
    }
    public String toJSON() {
        JSONObject resultado = new JSONObject();

        resultado.put("id", this.id );
        resultado.put("id_venda", this.id_venda);
        resultado.put("id_usuario", this.id_usuario);
        resultado.put("data_horario", this.data_horario);
        resultado.put("tempo_entrega", this.tempo_entrega);
        resultado.put("cep", this.cep);

        return resultado.toString();
    }

    
    
}
