package com.infomanagers.app.Controller;

import com.infomanagers.app.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;

public class InvDonatePromptController implements Initializable {
    public TextField address;
    public InventoryController inventoryController;
    public TextField quantityField;
    public ComboBox itemNameCombobox;
    public ComboBox arCombobox;
    public TextField signatureField;

    public void initData(InventoryController inventoryController) {
        this.inventoryController = inventoryController;
        ObservableList<String> recipientList = FXCollections.observableArrayList();
        ObservableList<String> currentItems = FXCollections.observableArrayList();
        for (AuthorizedRecipient authorizedRecipient : inventoryController.getCollectiveData().getAuthorizedRecipients()) {
            recipientList.add(authorizedRecipient.getFirst_name() + " " + authorizedRecipient.getLast_name());
        }
        for (ItemInventory itemInventory : inventoryController.getCollectiveData().getItemInventory()) {
            currentItems.add(itemInventory.getItem_name());
        }
        arCombobox.setItems(recipientList);
        itemNameCombobox.setItems(currentItems);
    }
    public void confirmClicked(ActionEvent actionEvent) throws IOException, URISyntaxException, InterruptedException {
        // Create the transaction
        Transaction newTransaction = new Transaction();
        newTransaction.setBarangay_id(inventoryController.getResponseModel().getBrgyID());
        newTransaction.setTransaction_id(generateUniqueID());
        newTransaction.setTransaction_type("Donation");
        newTransaction.setTransaction_date(getCurrentDate());
        newTransaction.setStaff_id(inventoryController.getResponseModel().getStaffID());
        for (AuthorizedRecipient recipient : inventoryController.getCollectiveData().getAuthorizedRecipients()) {
            String checkCounter = recipient.getFirst_name() + " " + recipient.getLast_name();
            if (checkCounter.equals(arCombobox.getValue())) {
                newTransaction.setParty_id(recipient.getParty_id());
            }
        }
        TransactionDetail newTransactionDetail = new TransactionDetail();
        newTransactionDetail.setTransaction_id(newTransaction.getTransaction_id());
        for (ItemInventory itemInventory : inventoryController.getCollectiveData().getItemInventory()) {
            if (itemNameCombobox.getValue().equals(itemInventory.getItem_name())) {
                newTransactionDetail.setItem_id(itemInventory.getItem_id());
            }
        }
        newTransactionDetail.setTransaction_quantity(Integer.parseInt(quantityField.getText()));
        newTransactionDetail.setBarangay_id(newTransaction.getBarangay_id());
        inventoryController.addDonation(newTransaction, newTransactionDetail);
        Stage currentStage = (Stage) quantityField.getScene().getWindow();
        currentStage.close();
    }

    private int generateUniqueID() {
        Random random = new Random();
        ArrayList<Transaction> transaction = inventoryController.getCollectiveData().getTransaction();
        int numLow = 0;
        int numHigh = 1000;
        int randomNum;
        boolean isUnique;
        do {
            randomNum = random.nextInt(numHigh - numLow + 1) + numLow;
            isUnique = isUniqueId(transaction, randomNum);
        } while (!isUnique);
        return randomNum;
    }

    private boolean isUniqueId(ArrayList<Transaction> transacs, int randomNum) {
        return transacs.stream().noneMatch(transaction -> transaction.getTransaction_id() == randomNum);
    }

    private String getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return currentDate.format(formatter);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
