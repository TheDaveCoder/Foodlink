package com.infomanagers.app.Controller;

import com.infomanagers.app.Model.AuthorizedRecipient;
import com.infomanagers.app.Model.Donors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class ArAddPromptController implements Initializable {

    public TextField firstName;
    public TextField lastName;
    public TextField address;
    public TextField familySize;
    public TextField contactNumber;
    public ArController arController;

    public void initData(ArController arController) {
        this.arController = arController;
    }
    public void confirmClicked(ActionEvent actionEvent) throws IOException, URISyntaxException, InterruptedException {
        Random random = new Random();
        ArrayList<AuthorizedRecipient> ars = arController.getCollectiveData().getAuthorizedRecipients();
        int numLow = 0;
        int numHigh = 1000;
        int randomNum;
        boolean isUnique;
        do {
            randomNum = random.nextInt(numHigh - numLow + 1) + numLow;
            isUnique = isUniqueId(ars, randomNum);
        } while (!isUnique);
        AuthorizedRecipient newAr = new AuthorizedRecipient();
        newAr.setParty_id(randomNum);
        newAr.setLast_name(lastName.getText());
        newAr.setFirst_name(firstName.getText());
        newAr.setAr_address(address.getText());
        newAr.setFamily_size(Integer.parseInt(familySize.getText()));
        newAr.setLast_name(lastName.getText());
        newAr.setBarangay_id(ars.getFirst().getBarangay_id());
        arController.updateData(newAr, contactNumber.getText());
        Stage currentStage = (Stage) lastName.getScene().getWindow();
        currentStage.close();
    }

    private boolean isUniqueId(ArrayList<AuthorizedRecipient> authorizedRecipients, int randomNum) {
        return authorizedRecipients.stream().noneMatch(authorizedRecipient -> authorizedRecipient.getParty_id() == randomNum);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
