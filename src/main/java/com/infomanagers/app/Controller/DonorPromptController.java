package com.infomanagers.app.Controller;

import com.infomanagers.app.Controller.DonorsController;
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

public class DonorPromptController implements Initializable {

    public TextField firstName;
    public TextField lastName;
    public ComboBox donorType;
    public TextField contactNumber;
    public DonorsController donorsController;

    public void initData(DonorsController donorsController) {
        this.donorsController = donorsController;
    }
    public void confirmClicked(ActionEvent actionEvent) throws IOException, URISyntaxException, InterruptedException {
        Random random = new Random();
        ArrayList<Donors> donors = donorsController.getCollectiveData().getDonors();
        int numLow = 0;
        int numHigh = 1000;
        int randomNum;
        boolean isUnique;
        do {
            randomNum = random.nextInt(numHigh - numLow + 1) + numLow;
            isUnique = isUniqueId(donors, randomNum);
        } while (!isUnique);
        Donors newDonor = new Donors();
        newDonor.setParty_id(randomNum);
        newDonor.setDonor_name(lastName.getText() + " " + firstName.getText());
        newDonor.setDonor_type(String.valueOf(donorType.getValue()));
        newDonor.setBarangay_id(donors.getFirst().getBarangay_id());
        donorsController.updateData(newDonor, contactNumber.getText());
        Stage currentStage = (Stage) lastName.getScene().getWindow();
        currentStage.close();
    }

    private boolean isUniqueId(ArrayList<Donors> donors, int randomNum) {
        return donors.stream().noneMatch(donor -> donor.getParty_id() == randomNum);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> newChoices = FXCollections.observableArrayList("Individual", "Group");
        donorType.setItems(newChoices);
    }
}
