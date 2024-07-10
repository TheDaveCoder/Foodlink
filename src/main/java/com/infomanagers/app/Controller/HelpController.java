package com.infomanagers.app.Controller;
import com.infomanagers.app.App;
import com.infomanagers.app.Model.*;
import javafx.application.Platform;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.time.LocalDate;
import java.time.Month;

public class HelpController implements Initializable{
    public Label donorCount;
    public Label newDonor;
    public Label newDonorDate;
    public Label newRecipients;
    public Label newRecipientDate;
    public Label recipientCount;
    public Label unitCount;
    public Label newUnits;
    public Label newUnitDate;
    public LineChart graph;
    public Button accountBtn;
    public Button helpBtn;
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
            // run shit
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
        FXMLLoader loader = new FXMLLoader(App.class.getResource("View/discrepancy-view.fxml"));
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
        FXMLLoader loader = new FXMLLoader(App.class.getResource("View/ar-view.fxml"));
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

    public void clickedHome(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("View/summary-view.fxml"));
        Parent summary = loader.load();
        SummaryController summaryController = loader.getController();
        summaryController.initData(collectiveData, responseModel);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(summary));
    }
}
