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

public class ArController implements Initializable{
    public Button accountBtn;
    public Button helpBtn;
    public TableColumn tablePartyID;
    public TableColumn tableLastName;
    public TableColumn tableFirstName;
    public TableColumn tableAddress;
    public TableColumn tableFamilySize;
    public TableColumn tableDateAdded;
    public TableView arTable;
    public TableColumn tableModifiedBy;

    public CollectiveData getCollectiveData() {
        return collectiveData;
    }

    private CollectiveData collectiveData;

    public ResponseModel getResponseModel() {
        return responseModel;
    }

    private ResponseModel responseModel;
    public Button summary;
    public Button inventory;
    public Button donors;
    public Button ar;
    public Button history;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            tablePartyID.setCellValueFactory(new PropertyValueFactory<>("party_id"));
            tableLastName.setCellValueFactory(new PropertyValueFactory<>("last_name"));
            tableFirstName.setCellValueFactory(new PropertyValueFactory<>("first_name"));
            tableAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            tableFamilySize.setCellValueFactory(new PropertyValueFactory<>("family_size"));
            tableDateAdded.setCellValueFactory(new PropertyValueFactory<>("date_added"));
            tableModifiedBy.setCellValueFactory(new PropertyValueFactory<>("modified_by"));
            ArrayList<ArTableView> arTableViews = new ArrayList<>();
            for (int i = 0; i < collectiveData.getAuthorizedRecipients().size(); i++) {
                arTableViews.add(new ArTableView(
                        collectiveData.getAuthorizedRecipients().get(i).getParty_id(),
                        collectiveData.getAuthorizedRecipients().get(i).getLast_name(),
                        collectiveData.getAuthorizedRecipients().get(i).getFirst_name(),
                        collectiveData.getAuthorizedRecipients().get(i).getAr_address(),
                        collectiveData.getAuthorizedRecipients().get(i).getFamily_size(),
                        "0",
                        "1"
                ));
            }
            for (int i = 0; i < collectiveData.getAuthorizedRecipients().size(); i++) {
                for (Party party : collectiveData.getParty()) {
                    if (collectiveData.getAuthorizedRecipients().get(i).getParty_id() == party.getParty_id()) {
                        arTableViews.get(i).setDate_added(party.getDate_added());
                        arTableViews.get(i).setModified_by(party.getEdited_by());
                    }
                }
            }
            ObservableList<ArTableView> observableList = FXCollections.observableList(arTableViews);
            arTable.setItems(observableList);
        });
    }
    public void initData(CollectiveData collectiveData, ResponseModel responseModel) {
        this.collectiveData = collectiveData;
        this.responseModel = responseModel;
    }

    public void updateData(AuthorizedRecipient newAr, String contactNumber) throws IOException, URISyntaxException, InterruptedException {
        collectiveData.getAuthorizedRecipients().add(newAr);
        Party party = new Party();
        party.setParty_id(newAr.getParty_id());
        party.setBarangay_id(newAr.getBarangay_id());
        party.setParty_contact_info(contactNumber);
        party.setEdited_by(responseModel.getStaffID());
        party.setParty_type("AR");
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        party.setDate_added(currentDate.format(formatter));
        collectiveData.getParty().add(party);
        ArrayList<ArTableView> arTableViews = new ArrayList<>();
        for (int i = 0; i < collectiveData.getAuthorizedRecipients().size(); i++) {
            arTableViews.add(new ArTableView(
                    collectiveData.getAuthorizedRecipients().get(i).getParty_id(),
                    collectiveData.getAuthorizedRecipients().get(i).getLast_name(),
                    collectiveData.getAuthorizedRecipients().get(i).getFirst_name(),
                    collectiveData.getAuthorizedRecipients().get(i).getAr_address(),
                    collectiveData.getAuthorizedRecipients().get(i).getFamily_size(),
                    "0",
                    "1"
            ));
        }
        for (int i = 0; i < collectiveData.getAuthorizedRecipients().size(); i++) {
            for (Party party1 : collectiveData.getParty()) {
                if (collectiveData.getAuthorizedRecipients().get(i).getParty_id() == party1.getParty_id()) {
                    arTableViews.get(i).setDate_added(party1.getDate_added());
                    arTableViews.get(i).setModified_by(party1.getEdited_by());
                }
            }
        }
        ObservableList<ArTableView> observableList = FXCollections.observableList(arTableViews);
        arTable.setItems(observableList);
        UpdateArTableModel updateTable = new UpdateArTableModel();
        updateTable.setAr(newAr);
        updateTable.setParty(party);
        updateTable.performPutRequest(0);
    }

    public void modifyData(AuthorizedRecipient updatedAr, Party updatedParty) throws IOException, URISyntaxException, InterruptedException {
        for(Party party : collectiveData.getParty()) {
            if (party.getParty_id() == updatedParty.getParty_id()) {
                collectiveData.getParty().remove(party);
                break;
            }
        }
        collectiveData.getParty().add(updatedParty);
        for(AuthorizedRecipient ar : collectiveData.getAuthorizedRecipients()) {
            if (ar.getParty_id() == updatedAr.getParty_id()) {
                collectiveData.getAuthorizedRecipients().remove(ar);
                break;
            }
        }
        collectiveData.getAuthorizedRecipients().add(updatedAr);
        ArrayList<ArTableView> arTableViews = new ArrayList<>();
        for (int i = 0; i < collectiveData.getAuthorizedRecipients().size(); i++) {
            arTableViews.add(new ArTableView(
                    collectiveData.getAuthorizedRecipients().get(i).getParty_id(),
                    collectiveData.getAuthorizedRecipients().get(i).getLast_name(),
                    collectiveData.getAuthorizedRecipients().get(i).getFirst_name(),
                    collectiveData.getAuthorizedRecipients().get(i).getAr_address(),
                    collectiveData.getAuthorizedRecipients().get(i).getFamily_size(),
                    "0",
                    "1"
            ));
        }
        for (int i = 0; i < collectiveData.getAuthorizedRecipients().size(); i++) {
            for (Party party1 : collectiveData.getParty()) {
                if (collectiveData.getAuthorizedRecipients().get(i).getParty_id() == party1.getParty_id()) {
                    arTableViews.get(i).setDate_added(party1.getDate_added());
                    arTableViews.get(i).setModified_by(party1.getEdited_by());
                }
            }
        }
        ObservableList<ArTableView> observableList = FXCollections.observableList(arTableViews);
        arTable.setItems(observableList);
        UpdateArTableModel updateTable = new UpdateArTableModel();
        updateTable.setAr(updatedAr);
        updateTable.setParty(updatedParty);
        updateTable.performPutRequest(1);
    }

    public void deleteData(int partyId) throws URISyntaxException, IOException, InterruptedException {
        for(Party party : collectiveData.getParty()) {
            if (party.getParty_id() == partyId) {
                collectiveData.getParty().remove(party);
                break;
            }
        }
        for(AuthorizedRecipient ar : collectiveData.getAuthorizedRecipients()) {
            if (ar.getParty_id() == partyId) {
                collectiveData.getAuthorizedRecipients().remove(ar);
                break;
            }
        }
        ArrayList<ArTableView> arTableViews = new ArrayList<>();
        for (int i = 0; i < collectiveData.getAuthorizedRecipients().size(); i++) {
            arTableViews.add(new ArTableView(
                    collectiveData.getAuthorizedRecipients().get(i).getParty_id(),
                    collectiveData.getAuthorizedRecipients().get(i).getLast_name(),
                    collectiveData.getAuthorizedRecipients().get(i).getFirst_name(),
                    collectiveData.getAuthorizedRecipients().get(i).getAr_address(),
                    collectiveData.getAuthorizedRecipients().get(i).getFamily_size(),
                    "0",
                    "1"
            ));
        }
        for (int i = 0; i < collectiveData.getAuthorizedRecipients().size(); i++) {
            for (Party party1 : collectiveData.getParty()) {
                if (collectiveData.getAuthorizedRecipients().get(i).getParty_id() == party1.getParty_id()) {
                    arTableViews.get(i).setDate_added(party1.getDate_added());
                    arTableViews.get(i).setModified_by(party1.getEdited_by());
                }
            }
        }
        ObservableList<ArTableView> observableList = FXCollections.observableList(arTableViews);
        arTable.setItems(observableList);
        DeleteArTableModel deleteArTableModel = new DeleteArTableModel();
        deleteArTableModel.setPartyID(partyId);
        deleteArTableModel.performDeleteRequest();
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
        // Open Prompt
        Stage promptStage = new Stage();
        FXMLLoader loader = new FXMLLoader(App.class.getResource("View/ar-add-view.fxml"));
        Parent root = loader.load();
        ArAddPromptController controller = loader.getController();
        controller.initData(this);
        Scene scene = new Scene(root);
        promptStage.setScene(scene);
        promptStage.setTitle("Add an Authorized Recipient");
        promptStage.setResizable(false);
        promptStage.show();
    }

    public void editBtnClicked(ActionEvent actionEvent) throws IOException {
        ArTableView arTableView = (ArTableView) arTable.getSelectionModel().getSelectedItem();
        Stage promptStage = new Stage();
        FXMLLoader loader = new FXMLLoader(App.class.getResource("View/ar-edit-view.fxml"));
        Parent root = loader.load();
        ArEditPromptController controller = loader.getController();
        controller.initData(this, arTableView);
        Scene scene = new Scene(root);
        promptStage.setScene(scene);
        promptStage.setTitle("Modify an Authorized Recipient");
        promptStage.setResizable(false);
        promptStage.show();
    }
}
