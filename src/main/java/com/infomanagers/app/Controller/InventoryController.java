package com.infomanagers.app.Controller;
import com.infomanagers.app.App;
import com.infomanagers.app.Model.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.time.LocalDate;
import java.time.Month;

public class InventoryController implements Initializable{
    public Button accountBtn;
    public Button helpBtn;
    public TableColumn tablePartyID;
    public TableColumn tableName;
    public TableColumn tableQuantity;
    public TableColumn tableCategory;
    public TableColumn tableExpirationDate;
    public TableView inventoryTable;
    public TableColumn transactionID;
    public TableColumn transactionType;
    public TableColumn date;
    public TableColumn partyID;
    public TableColumn witnessedBy;
    private CollectiveData collectiveData;
    private ResponseModel responseModel;
    public Button summary;
    public Button inventory;
    public Button donors;
    public Button ar;
    public Button history;

    public CollectiveData getCollectiveData() {
        return collectiveData;
    }
    public ResponseModel getResponseModel() {
        return responseModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            tablePartyID.setCellValueFactory(new PropertyValueFactory<>("item_id"));
            tableName.setCellValueFactory(new PropertyValueFactory<>("item_name"));
            tableQuantity.setCellValueFactory(new PropertyValueFactory<>("item_quantity"));
            tableCategory.setCellValueFactory(new PropertyValueFactory<>("item_category"));
            tableExpirationDate.setCellValueFactory(new PropertyValueFactory<>("item_expiration_date"));
            ObservableList<ItemInventory> observableList = FXCollections.observableList(collectiveData.getItemInventory());
            inventoryTable.setItems(observableList);
        });
    }
    public void initData(CollectiveData collectiveData, ResponseModel responseModel) {
        this.collectiveData = collectiveData;
        this.responseModel = responseModel;
    }
    public void mouseEntered(MouseEvent mouseEvent) {
        summary.setText("Summary");
        inventory.setText("Food and Supply Inventory");
        donors.setText("Donors List");
        ar.setText("Recipients List");
        history.setText("Transaction History");
    }
    public void mouseExited(MouseEvent mouseEvent) {
        summary.setText("");
        inventory.setText("");
        donors.setText("");
        ar.setText("");
        history.setText("");
    }

    public void clickedAccount(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("View/account-view.fxml"));
        Parent help = loader.load();
        AccountController accountController = loader.getController();
        accountController.initData(collectiveData, responseModel);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        // Set the new scene
        stage.setScene(new Scene(help));
    }

    public void clickedHelp(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("View/help-view.fxml"));
        Parent help = loader.load();
        HelpController helpController = loader.getController();
        helpController.initData(collectiveData, responseModel);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        // Set the new scene
        stage.setScene(new Scene(help));
    }

    public void clickedSummary(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("View/summary-view.fxml"));
        Parent summary = loader.load();
        SummaryController summaryController = loader.getController();
        summaryController.initData(collectiveData, responseModel);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        // Set the new scene
        stage.setScene(new Scene(summary));
    }

    public void clickedDonors(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("View/donors-view.fxml"));
        Parent donors = loader.load();
        DonorsController donorsController = loader.getController();
        donorsController.initData(collectiveData, responseModel);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        // Set the new scene
        stage.setScene(new Scene(donors));
    }

    public void clickedAr(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("View/recipients-view.fxml"));
        Parent ar = loader.load();
        ArController arController = loader.getController();
        arController.initData(collectiveData, responseModel);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        // Set the new scene
        stage.setScene(new Scene(ar));
    }

    public void clickedHistory(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("View/history-view.fxml"));
        Parent history = loader.load();
        HistoryController historyController = loader.getController();
        historyController.initData(collectiveData, responseModel);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        // Set the new scene
        stage.setScene(new Scene(history));
    }

    public void addBtnClicked(ActionEvent actionEvent) throws IOException {
        Stage promptStage = new Stage();
        FXMLLoader loader = new FXMLLoader(App.class.getResource("View/inventory-add-view.fxml"));
        Parent root = loader.load();
        InvAddPromptController controller = loader.getController();
        controller.initData(this);
        Scene scene = new Scene(root);
        promptStage.setScene(scene);
        promptStage.setTitle("Add Item to Inventory");
        promptStage.setResizable(false);
        promptStage.show();
    }

    public void donateBtnClicked(ActionEvent actionEvent) throws IOException {
        Stage promptStage = new Stage();
        FXMLLoader loader = new FXMLLoader(App.class.getResource("View/inventory-donate-view.fxml"));
        Parent root = loader.load();
        InvDonatePromptController controller = loader.getController();
        controller.initData(this);
        Scene scene = new Scene(root);
        promptStage.setScene(scene);
        promptStage.setTitle("Donate an Item");
        promptStage.setResizable(false);
        promptStage.show();
    }

    public void recordBtnClicked(ActionEvent actionEvent) throws IOException {
        Stage promptStage = new Stage();
        FXMLLoader loader = new FXMLLoader(App.class.getResource("View/record-discrepancy-view.fxml"));
        Parent root = loader.load();
        discrepancyPromptController controller = loader.getController();
        controller.initData(this);
        Scene scene = new Scene(root);
        promptStage.setScene(scene);
        promptStage.setTitle("Record a Discrepancy");
        promptStage.setResizable(false);
        promptStage.show();
    }

    public void discrepancyBtnClicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("View/discrepancy-view.fxml"));
        Parent discrepancy = loader.load();
        DiscrepancyController discrepancyController = loader.getController();
        discrepancyController.initData(collectiveData, responseModel);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        // Set the new scene
        stage.setScene(new Scene(discrepancy));
    }

    public void updateData(Transaction newTransaction, TransactionDetail newTransactionDetail, ItemInventory newItemInventory, Boolean isNewItem) {
        collectiveData.getTransaction().add(newTransaction);
        collectiveData.getTransactionDetail().add(newTransactionDetail);
        if (!isNewItem) {
            for(ItemInventory olditemInventory : collectiveData.getItemInventory()) {
                if (olditemInventory.getItem_id() == newItemInventory.getItem_id()) {
                    collectiveData.getItemInventory().remove(olditemInventory);
                    break;
                }
            }
        }
        collectiveData.getItemInventory().add(newItemInventory);
        ObservableList<ItemInventory> observableList = FXCollections.observableList(collectiveData.getItemInventory());
        inventoryTable.setItems(observableList);
    }

    public void addDonation(Transaction newTransaction, TransactionDetail newTransactionDetail) {
        collectiveData.getTransaction().add(newTransaction);
        collectiveData.getTransactionDetail().add(newTransactionDetail);
        for (ItemInventory itemInventory : collectiveData.getItemInventory()) {
            if (itemInventory.getItem_id() == newTransactionDetail.getItem_id()) {
                int newQty = itemInventory.getItem_quantity() - newTransactionDetail.getTransaction_quantity();
                itemInventory.setItem_quantity(newQty);
            }
        }
        ObservableList<ItemInventory> observableList = FXCollections.observableList(collectiveData.getItemInventory());
        inventoryTable.setItems(observableList);
        inventoryTable.refresh();
    }

    public void updateDiscrepancy(Discrepancy newDiscrepancy) {
        for (ItemInventory itemInventory : collectiveData.getItemInventory()) {
            if (itemInventory.getItem_id() == newDiscrepancy.getItem_id()) {
                int newQty = itemInventory.getItem_quantity() - newDiscrepancy.getUnits_lost();
                itemInventory.setItem_quantity(newQty);
            }
        }
        collectiveData.getDiscrepancies().add(newDiscrepancy);
        ObservableList<ItemInventory> observableList = FXCollections.observableList(collectiveData.getItemInventory());
        inventoryTable.setItems(observableList);
        inventoryTable.refresh();
    }
}
