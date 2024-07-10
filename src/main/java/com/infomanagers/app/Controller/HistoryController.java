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
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.time.LocalDate;
import java.time.Month;

public class HistoryController implements Initializable{
    public Button accountBtn;
    public Button helpBtn;
    public TableColumn transactionID;
    public TableColumn transactionType;
    public TableColumn date;
    public TableColumn partyID;
    public TableColumn witnessedBy;
    public TableView historyTable;
    private CollectiveData collectiveData;
    private ResponseModel responseModel;
    public Button summary;
    public Button inventory;
    public Button donors;
    public Button ar;
    public Button history;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            transactionID.setCellValueFactory(new PropertyValueFactory<>("transaction_id"));
            transactionType.setCellValueFactory(new PropertyValueFactory<>("transaction_type"));
            date.setCellValueFactory(new PropertyValueFactory<>("transaction_date"));
            partyID.setCellValueFactory(new PropertyValueFactory<>("party_id"));
            witnessedBy.setCellValueFactory(new PropertyValueFactory<>("staff_id"));
            ObservableList<Transaction> observableList = FXCollections.observableList(collectiveData.getTransaction());
            historyTable.setItems(observableList);
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

    public void clickedInventory(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("View/inventory-view.fxml"));
        Parent inventory = loader.load();
        InventoryController inventoryController = loader.getController();
        inventoryController.initData(collectiveData, responseModel);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        // Set the new scene
        stage.setScene(new Scene(inventory));
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
}
