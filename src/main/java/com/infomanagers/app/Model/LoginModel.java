package com.infomanagers.app.Model;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.net.http.HttpRequest.BodyPublishers;

public class LoginModel {
    private String barangayID;
    private String staffID;
    private String password;
    public String getBarangayID() {
        return barangayID;
    }
    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }
    public String getPassword() {
        return password;
    }
    public LoginModel(String barangayID, String staffID, String password) {
        this.barangayID = barangayID;
        this.staffID = staffID;
        this.password = password;
    }
    public void hashPassword() throws NoSuchAlgorithmException {
        String password = this.password;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(password.getBytes());
        StringBuilder hexString = new StringBuilder();

        for (byte hashByte: hashBytes) {
            String hex = Integer.toHexString(0xff & hashByte);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        this.password = hexString.toString();
    }
    public ResponseModel checkCredentials() throws IOException, InterruptedException, URISyntaxException {
        System.out.println(password);
        Gson gson = new Gson();
        String jsonCredentials = gson.toJson(this);
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI("https://osrohfg9l3.execute-api.ap-southeast-1.amazonaws.com/Production/Login"))
                .header("x-api-key", "rKTEP4fd8Sanbul28ILOl5CKRPtgrZZy5NfNXYUZ")
                .POST(BodyPublishers.ofString(jsonCredentials))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());

        ResponseModel response = gson.fromJson(postResponse.body(), ResponseModel.class);
        return response;
    }
}
