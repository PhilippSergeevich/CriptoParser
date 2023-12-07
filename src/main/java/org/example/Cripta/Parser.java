package org.example.Cripta;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Parser {
    public String doRequest(String urlConnect) throws URISyntaxException, IOException, InterruptedException {

        String url = urlConnect;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET().build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


        String apiResponse = response.body();
        return apiResponse;

    }

    public String parseJsonString(String jsonStr) {
        String eth = "";
        String[] strMassive = jsonStr.split("},");
        for (int i = 0; i < strMassive.length; i++) {
            //System.out.println(strMassive[i]);
            if (strMassive[i].startsWith("\"eth\"")) {
                eth = strMassive[i];
                break;
            }
        }

        String priceKey = "\"price\":\"";
        int startIndex = eth.indexOf(priceKey);
        if (startIndex != -1) {
            int endIndex = eth.indexOf("\",", startIndex + priceKey.length());
            if (endIndex != -1) {
                String price = eth.substring(startIndex + priceKey.length(), endIndex);
                return price;
            }
        }

        return "";


    }
}