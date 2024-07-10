package com.infomanagers.app.Controller;

import com.infomanagers.app.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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

public class discrepancyPromptController implements Initializable {
    public TextField address;
    private InventoryController inventoryController;
    public TextField quantityField;
    public ComboBox itemNameCombobox;

    public void initData(InventoryController inventoryController) {
        this.inventoryController = inventoryController;
        ObservableList<String> currentItems = FXCollections.observableArrayList();
        for (ItemInventory itemInventory : inventoryController.getCollectiveData().getItemInventory()) {
            currentItems.add(itemInventory.getItem_name());
        }
        itemNameCombobox.setItems(currentItems);
    }
    public void confirmClicked(ActionEvent actionEvent) throws IOException, URISyntaxException, InterruptedException {
        Random random = new Random();
        ArrayList<Discrepancy> discrepancies = inventoryController.getCollectiveData().getDiscrepancies();
        Discrepancy discrepancy = new Discrepancy();
        int numLow = 0;
        int numHigh = 1000;
        int randomNum;
        boolean isUnique;
        do {
            randomNum = random.nextInt(numHigh - numLow + 1) + numLow;
            isUnique = isUniqueId(discrepancies, randomNum);
        } while (!isUnique);
        discrepancy.setDiscrepancy_id(randomNum);
        for (ItemInventory itemInventory : inventoryController.getCollectiveData().getItemInventory()) {
            if (itemInventory.getItem_name().equals(itemNameCombobox.getValue())) {
                discrepancy.setItem_id(itemInventory.getItem_id());
            }
        }
        discrepancy.setUnits_lost(Integer.parseInt(quantityField.getText()));
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        discrepancy.setDate_reported(currentDate.format(formatter));
        discrepancy.setBarangay_id(inventoryController.getResponseModel().getBrgyID());
        discrepancy.setStaff_id(inventoryController.getResponseModel().getStaffID());
        inventoryController.updateDiscrepancy(discrepancy);
        Stage currentStage = (Stage) quantityField.getScene().getWindow();
        currentStage.close();
    }
    private boolean isUniqueId(ArrayList<Discrepancy> discrepancies, int randomNum) {
        return discrepancies.stream().noneMatch(discrepancy -> discrepancy.getDiscrepancy_id() == randomNum);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
