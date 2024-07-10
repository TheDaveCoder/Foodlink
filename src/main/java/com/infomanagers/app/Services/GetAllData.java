package com.infomanagers.app.Services;

import com.google.gson.*;
import com.infomanagers.app.Model.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class GetAllData {
    public static CollectiveData fetchAllData() throws IOException, InterruptedException, URISyntaxException {
        HttpRequest getReq = HttpRequest.newBuilder()
                .uri(new URI("https://osrohfg9l3.execute-api.ap-southeast-1.amazonaws.com/Production/Data"))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getReq, HttpResponse.BodyHandlers.ofString());
        JsonParser jsonParser = new JsonParser();
        JsonObject jp = jsonParser.parse(getResponse.body()).getAsJsonObject();
        CollectiveData collectiveData = new CollectiveData();
        collectiveData.setStatusCode(jp.get("statusCode").getAsInt());
        Gson gson = new Gson();
        collectiveData.setItemInventory(new ArrayList<ItemInventory>());
        collectiveData.setTransactionDetail(new ArrayList<TransactionDetail>());
        collectiveData.setTransaction(new ArrayList<Transaction>());
        collectiveData.setParty(new ArrayList<Party>());
        collectiveData.setDonors(new ArrayList<Donors>());
        collectiveData.setAuthorizedRecipients(new ArrayList<AuthorizedRecipient>());
        collectiveData.setDiscrepancies(new ArrayList<Discrepancy>());
        for (JsonElement result : jp.get("ItemInventory").getAsJsonArray()) {
            collectiveData.getItemInventory().add(gson.fromJson(result, ItemInventory.class));
        }
        for (JsonElement result : jp.get("TransactionDetail").getAsJsonArray()) {
            collectiveData.getTransactionDetail().add(gson.fromJson(result, TransactionDetail.class));
        }
        for (JsonElement result : jp.get("Transaction").getAsJsonArray()) {
            collectiveData.getTransaction().add(gson.fromJson(result, Transaction.class));
        }
        for (JsonElement result : jp.get("Party").getAsJsonArray()) {
            collectiveData.getParty().add(gson.fromJson(result, Party.class));
        }
        for (JsonElement result : jp.get("Donors").getAsJsonArray()) {
            collectiveData.getDonors().add(gson.fromJson(result, Donors.class));
        }
        for (JsonElement result : jp.get("AuthorizedRecipient").getAsJsonArray()) {
            collectiveData.getAuthorizedRecipients().add(gson.fromJson(result, AuthorizedRecipient.class));
        }
        for (JsonElement result : jp.get("Discrepancy").getAsJsonArray()) {
            collectiveData.getDiscrepancies().add(gson.fromJson(result, Discrepancy.class));
        }
        return collectiveData;
    }
}
