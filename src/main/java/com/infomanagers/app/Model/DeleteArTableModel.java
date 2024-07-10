package com.infomanagers.app.Model;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DeleteArTableModel {
    int partyID;
    public int getPartyID() {
        return partyID;
    }

    public void setPartyID(int partyID) {
        this.partyID = partyID;
    }
    public void performDeleteRequest() throws URISyntaxException, IOException, InterruptedException {
        Gson gson = new Gson();
        String jsonCredentials = gson.toJson(this);
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI("https://osrohfg9l3.execute-api.ap-southeast-1.amazonaws.com/Production/Data/delete-Ar"))
                .header("x-api-key", "rKTEP4fd8Sanbul28ILOl5CKRPtgrZZy5NfNXYUZ")
                .POST(HttpRequest.BodyPublishers.ofString(jsonCredentials))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        ResponseModel response = gson.fromJson(postResponse.body(), ResponseModel.class);
        System.out.println(response.getStatusCode());
    }
}
