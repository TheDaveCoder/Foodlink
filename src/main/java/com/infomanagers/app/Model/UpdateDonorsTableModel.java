package com.infomanagers.app.Model;

import com.google.gson.Gson;
import com.infomanagers.app.Model.Donors;
import com.infomanagers.app.Model.Party;
import com.infomanagers.app.Model.ResponseModel;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UpdateDonorsTableModel {
    private Donors donor;
    private Party party;
    public Donors getDonor() {
        return donor;
    }
    public void setDonor(Donors donor) {
        this.donor = donor;
    }
    public Party getParty() {
        return party;
    }
    public void setParty(Party party) {
        this.party = party;
    }

    public void performPutRequest() throws IOException, InterruptedException, URISyntaxException {
        Gson gson = new Gson();
        String jsonCredentials = gson.toJson(this);
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI("https://osrohfg9l3.execute-api.ap-southeast-1.amazonaws.com/Production/Data/DonorTable"))
                .header("x-api-key", "rKTEP4fd8Sanbul28ILOl5CKRPtgrZZy5NfNXYUZ")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonCredentials))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        ResponseModel response = gson.fromJson(postResponse.body(), ResponseModel.class);
        System.out.println(response.getStatusCode());
    }
}
