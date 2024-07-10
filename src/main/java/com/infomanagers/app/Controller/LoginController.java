package com.infomanagers.app.Controller;

import com.google.gson.Gson;
import com.infomanagers.app.Model.Barangay;
import com.infomanagers.app.App;
import com.infomanagers.app.Model.CollectiveData;
import com.infomanagers.app.Model.LoginModel;
import com.infomanagers.app.Model.ResponseModel;
import com.infomanagers.app.Services.GetAllData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import static com.infomanagers.app.Services.GetAllData.fetchAllData;

public class LoginController implements Initializable {
    public Button loginBtn;
    public TextField password;
    public TextField staffID;
    public TextField brgyID;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void performLogin(ActionEvent actionEvent) throws NoSuchAlgorithmException, IOException, URISyntaxException, InterruptedException {
        LoginModel lm = new LoginModel(brgyID.getText(), staffID.getText(), password.getText());
        lm.hashPassword();
        ResponseModel response = lm.checkCredentials();
        if (response.getStatusCode().equals("200")) {
            System.out.println("Login Successful!");
            // Fetch all necessary data
            CollectiveData collectiveData = GetAllData.fetchAllData();
            if (collectiveData.getStatusCode() == 200) {
                // Setup and set scene to summary
                FXMLLoader loader = new FXMLLoader(App.class.getResource("View/summary-view.fxml"));
                Parent summary = loader.load();
                SummaryController summaryController = loader.getController();
                summaryController.initData(collectiveData, response);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                // Set the new scene
                stage.setScene(new Scene(summary));
            } else {
                System.out.println("Failed to fetch data!");
            }
        } else {
            System.out.println("Failed to Login!");
        }
    }
}