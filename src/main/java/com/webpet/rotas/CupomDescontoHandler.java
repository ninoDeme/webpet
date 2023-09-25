package com.webpet.rotas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.webpet.classes.Animal;
import com.webpet.classes.RespostaHttp;
import com.webpet.classes.Rota;
public class CupomDescontoHandler extends Rota {
    public CupomDescontoHandler(Connection conexao) {
        super(conexao);
    }
    @Override
    public RespostaHttp get(Map<String, String> query, HttpExchange pedido) {
        String response;
        try {
            String sql= "SELECT * FROM cupom_desconto WHERE codigo = ?";
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            
            ps.setString(1, query.get("codigo"));
            ResultSet resultado = ps.executeQuery();
            
          return new RespostaHttp("C com sucesso");
        } catch (Exception e) {
            return new RespostaHttp("C com erro");

        }
}
}
