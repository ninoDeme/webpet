package com.webpet.rotas;

import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.sun.net.httpserver.HttpExchange;
import com.webpet.classes.RespostaHttp;
import com.webpet.classes.Rota;

public class BuscaCepHandler extends Rota {

    public BuscaCepHandler() {
        super("BuscaCepHandler");
    }

    @Override
    public RespostaHttp get(Map<String, String> query, HttpExchange pedido) {
        // Replace <cep> with the actual CEP value in the URL
        StringBuilder sb = new StringBuilder(query.get("cep"));
        sb.insert(5, '-');

        String apiUrl = "https://cdn.apicep.com/file/apicep/" + sb.toString() + ".json";

        // Create a URL object with the API URL
        try {
            // Create an HttpClient instance
            CloseableHttpClient httpClient = HttpClients.createDefault();

            // Create an HTTP GET request
            HttpGet httpGet = new HttpGet(apiUrl);

            // Execute the request and get the response
            HttpResponse response = httpClient.execute(httpGet);

            // Check if the response status code is 200 (HTTP OK)
            if (response.getStatusLine().getStatusCode() == 200) {
                // Extract the response entity
                HttpEntity entity = response.getEntity();

                // Read the response content as a string using UTF-8 encoding
                String responseString = EntityUtils.toString(entity);

                // Close the HttpClient
                httpClient.close();

                // Return the response as a string
                return new RespostaHttp(responseString.getBytes());
            } else {
                // If the response code is not 200, handle the error here (e.g., throw an
                // exception)
                return new RespostaHttp("Failed to fetch data from the API").code(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new RespostaHttp("Ocorreu um erro").code(500);
        }
    }

}
