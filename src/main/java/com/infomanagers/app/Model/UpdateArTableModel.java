package com.infomanagers.app.Model;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;

public class UpdateArTableModel {
    private AuthorizedRecipient ar;
    private Party party;
    public AuthorizedRecipient getAr() {
        return ar;
    }
    public void setAr(AuthorizedRecipient ar) {
        this.ar = ar;
    }
    public Party getParty() {
        return party;
    }
    public void setParty(Party party) {
        this.party = party;
    }
    public void performPutRequest(int requestType) throws IOException, InterruptedException, URISyntaxException {
        Gson gson = new Gson();
        String jsonCredentials = gson.toJson(this);
        if (requestType == 0) {
            HttpRequest postRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://osrohfg9l3.execute-api.ap-southeast-1.amazonaws.com/Production/Data/ArTable"))
                    .header("x-api-key", "rKTEP4fd8Sanbul28ILOl5CKRPtgrZZy5NfNXYUZ")
                    .POST(BodyPublishers.ofString(jsonCredentials))
                    .build();
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
            ResponseModel response = gson.fromJson(postResponse.body(), ResponseModel.class);
            System.out.println(response.getStatusCode());
        }
        if (requestType == 1) {
            HttpRequest putRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://osrohfg9l3.execute-api.ap-southeast-1.amazonaws.com/Production/Data/ArTable"))
                    .header("x-api-key", "rKTEP4fd8Sanbul28ILOl5CKRPtgrZZy5NfNXYUZ")
                    .PUT(BodyPublishers.ofString(jsonCredentials))
                    .build();
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> putResponse = httpClient.send(putRequest, HttpResponse.BodyHandlers.ofString());
            ResponseModel response = gson.fromJson(putResponse.body(), ResponseModel.class);
            System.out.println(response.getMessage());
        }
    }
}
