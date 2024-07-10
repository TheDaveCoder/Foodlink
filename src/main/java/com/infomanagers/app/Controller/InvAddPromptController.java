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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class InvAddPromptController implements Initializable {

    public TextField address;
    public InventoryController inventoryController;
    public DatePicker expirationDate;
    public ComboBox categoryCombobox;
    public TextField quantityField;
    public ComboBox itemNameCombobox;
    public ComboBox donorCombobox;
    private Boolean isNewItem = false;
    private Boolean matchFound = false;
    private Boolean catMatchFound = false;

    public void initData(InventoryController inventoryController) {
        this.inventoryController = inventoryController;
        ObservableList<String> donorList = FXCollections.observableArrayList();
        ObservableList<String> currentItems = FXCollections.observableArrayList();
        ObservableList<String> currentCategories = FXCollections.observableArrayList();
        for (Donors donor : inventoryController.getCollectiveData().getDonors()) {
            donorList.add(donor.getDonor_name());
        }
        for (ItemInventory itemInventory : inventoryController.getCollectiveData().getItemInventory()) {
            currentItems.add(itemInventory.getItem_name());
            currentCategories.add(itemInventory.getItem_category());
        }
        donorCombobox.setItems(donorList);
        itemNameCombobox.setItems(currentItems);
        categoryCombobox.setItems(currentCategories);
    }
    public void confirmClicked(ActionEvent actionEvent) throws IOException, URISyntaxException, InterruptedException, ParseException {
        // Create the transaction
        Transaction newTransaction = new Transaction();
        newTransaction.setBarangay_id(inventoryController.getResponseModel().getBrgyID());
        newTransaction.setTransaction_id(generateUniqueID(0));
        newTransaction.setTransaction_type("Acquisition");
        newTransaction.setTransaction_date(getCurrentDate());
        newTransaction.setStaff_id(inventoryController.getResponseModel().getStaffID());
        for (Donors donor : inventoryController.getCollectiveData().getDonors()) {
            if (donor.getDonor_name().equals(donorCombobox.getValue())) {
                newTransaction.setParty_id(donor.getParty_id());
            }
        }
        TransactionDetail newTransactionDetail = new TransactionDetail();
        newTransactionDetail.setTransaction_id(newTransaction.getTransaction_id());
        for (ItemInventory itemInventory : inventoryController.getCollectiveData().getItemInventory()) {
            if (itemNameCombobox.getValue().equals(itemInventory.getItem_name())) {
                System.out.println("1 same name!" + itemInventory.getItem_name());
                System.out.println("Name 1: " + itemInventory.getItem_name());
                System.out.println("Name 2: " + itemNameCombobox.getValue());
                newTransactionDetail.setItem_id(itemInventory.getItem_id());
                matchFound = true;
                break;
            }
        }
        if (!matchFound) {
            newTransactionDetail.setItem_id(generateUniqueID(1));
        }
        newTransactionDetail.setTransaction_quantity(Integer.parseInt(quantityField.getText()));
        newTransactionDetail.setBarangay_id(newTransaction.getBarangay_id());
        ItemInventory newItemInventory = new ItemInventory();
        newItemInventory.setBarangay_id(newTransaction.getBarangay_id());
        newItemInventory.setItem_id(newTransactionDetail.getItem_id());
        newItemInventory.setItem_name(String.valueOf(itemNameCombobox.getValue()));
        for (ItemInventory itemInventory : inventoryController.getCollectiveData().getItemInventory()) {
            if (itemNameCombobox.getValue().equals(itemInventory.getItem_name())) {
                System.out.println("3 same name!");
                int newValue = Integer.parseInt(quantityField.getText()) + itemInventory.getItem_quantity();
                newItemInventory.setItem_quantity(newValue);
                matchFound = true;
                break;
            }
        }
        if (!matchFound) {
            newItemInventory.setItem_quantity(Integer.parseInt(quantityField.getText()));
        }
        for (ItemInventory itemInventory : inventoryController.getCollectiveData().getItemInventory()) {
            if (categoryCombobox.getValue().equals(itemInventory.getItem_category())) {
                newItemInventory.setItem_category(itemInventory.getItem_category());
                catMatchFound = true;
                break;
            }
        }
        if (!catMatchFound) {
            newItemInventory.setItem_category(String.valueOf(categoryCombobox.getValue()));
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (ItemInventory itemInventory : inventoryController.getCollectiveData().getItemInventory()) {
            if (itemNameCombobox.getValue().equals(itemInventory.getItem_name())) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date existingDate = sdf.parse(itemInventory.getItem_expiration_date());
                Date newDate = sdf.parse(expirationDate.getValue().format(formatter));
                if (newDate.before(existingDate)) {
                    newItemInventory.setItem_expiration_date(expirationDate.getValue().format(formatter));
                    break;
                } else {
                    newItemInventory.setItem_expiration_date(itemInventory.getItem_expiration_date());
                    break;
                }
            }
        }
        if (!matchFound) {
            newItemInventory.setItem_expiration_date(expirationDate.getValue().format(formatter));
        }
        inventoryController.updateData(newTransaction, newTransactionDetail, newItemInventory, isNewItem);
        Stage currentStage = (Stage) quantityField.getScene().getWindow();
        currentStage.close();
    }

    private int generateUniqueID(int listType) {
        Random random = new Random();
        if (listType == 0) {
            ArrayList<Transaction> transaction = inventoryController.getCollectiveData().getTransaction();
            int numLow = 0;
            int numHigh = 1000;
            int randomNum;
            boolean isUnique;
            do {
                randomNum = random.nextInt(numHigh - numLow + 1) + numLow;
                isUnique = isUniqueIdTr(transaction, randomNum);
            } while (!isUnique);
            return randomNum;
        } else if (listType == 1) {
            ArrayList<ItemInventory> itemInventories = inventoryController.getCollectiveData().getItemInventory();
            int numLow = 0;
            int numHigh = 1000;
            int randomNum;
            boolean isUnique;
            do {
                randomNum = random.nextInt(numHigh - numLow + 1) + numLow;
                isUnique = isUniqueIdII(itemInventories, randomNum);
            } while (!isUnique);
            return randomNum;
        }
        return 1001;
    }

    private String getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return currentDate.format(formatter);
    }
    private boolean isUniqueIdTr(ArrayList<Transaction> iterables, int randomNum) {
        return (iterables).stream().noneMatch(iterable -> iterable.getTransaction_id() == randomNum);
    }
    private boolean isUniqueIdII(ArrayList<ItemInventory> iterables, int randomNum) {
        return (iterables).stream().noneMatch(iterable -> iterable.getItem_id() == randomNum);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemNameCombobox.setEditable(true);
        categoryCombobox.setEditable(true);
    }
}
