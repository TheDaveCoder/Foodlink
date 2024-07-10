package com.infomanagers.app.Controller;

import com.infomanagers.app.Model.ArTableView;
import com.infomanagers.app.Model.AuthorizedRecipient;
import com.infomanagers.app.Model.Barangay;
import com.infomanagers.app.Model.Party;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class ArEditPromptController implements Initializable {
    public TextField address;
    public TextField familySize;
    public ArController arController;
    public ArTableView arTableView;

    public void initData(ArController arController, ArTableView arTableView) {
        this.arController = arController;
        this.arTableView = arTableView;
        address.setText(arTableView.getAddress());
        familySize.setText(String.valueOf(arTableView.getFamily_size()));
    }
    public void confirmClicked(ActionEvent actionEvent) throws IOException, URISyntaxException, InterruptedException {
        AuthorizedRecipient updatedAr = new AuthorizedRecipient();
        updatedAr.setParty_id(arTableView.getParty_id());
        updatedAr.setFirst_name(arTableView.getFirst_name());
        updatedAr.setLast_name(arTableView.getLast_name());
        updatedAr.setAr_address(address.getText());
        updatedAr.setFamily_size(Integer.parseInt(familySize.getText()));
        for(Party party : arController.getCollectiveData().getParty()) {
            if (party.getParty_id() == updatedAr.getParty_id()) {
                updatedAr.setBarangay_id(party.getBarangay_id());
            }
        }
        Party updatedParty = new Party();
        for(Party party : arController.getCollectiveData().getParty()) {
            if (party.getParty_id() == updatedAr.getParty_id()) {
                updatedParty.setParty_type(party.getParty_type());
                updatedParty.setBarangay_id(party.getBarangay_id());
                updatedParty.setParty_contact_info(party.getParty_contact_info());
                updatedParty.setEdited_by(arController.getResponseModel().getStaffID());
                updatedParty.setParty_type(party.getParty_type());
                LocalDate currentDate = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                updatedParty.setDate_added(currentDate.format(formatter));
            }
        }
        arController.modifyData(updatedAr, updatedParty);
        Stage currentStage = (Stage) familySize.getScene().getWindow();
        currentStage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void deleteClicked(ActionEvent actionEvent) throws URISyntaxException, IOException, InterruptedException {
        arController.deleteData(arTableView.getParty_id());
        Stage currentStage = (Stage) familySize.getScene().getWindow();
        currentStage.close();
    }
}
