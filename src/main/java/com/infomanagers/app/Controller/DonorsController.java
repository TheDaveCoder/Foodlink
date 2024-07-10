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
import javafx.scene.control.Button;
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
import java.util.ResourceBundle;
import java.time.LocalDate;

public class DonorsController implements Initializable{
    public Button accountBtn;
    public Button helpBtn;
    public Button addDonors;
    public TableView donorTable;
    public TableColumn tablePartyID;
    public TableColumn tableDonorName;
    public TableColumn tableContactInfo;
    public TableColumn tableType;
    public TableColumn tableDateAdded;
    public TableColumn tableEditedBy;

    public CollectiveData getCollectiveData() {
        return collectiveData;
    }

    public void setCollectiveData(CollectiveData collectiveData) {
        this.collectiveData = collectiveData;
    }

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
            tablePartyID.setCellValueFactory(new PropertyValueFactory<>("party_id"));
            tableDonorName.setCellValueFactory(new PropertyValueFactory<>("donor_name"));
            tableContactInfo.setCellValueFactory(new PropertyValueFactory<>("contact_information"));
            tableType.setCellValueFactory(new PropertyValueFactory<>("type"));
            tableDateAdded.setCellValueFactory(new PropertyValueFactory<>("date_added"));
            tableEditedBy.setCellValueFactory(new PropertyValueFactory<>("edited_by"));
            ArrayList<DonorTableView> donorTableViews = new ArrayList<>();
            for (int i = 0; i < collectiveData.getDonors().size(); i++) {
                donorTableViews.add(new DonorTableView(
                        collectiveData.getDonors().get(i).getParty_id(),
                        collectiveData.getDonors().get(i).getDonor_name(),
                        "1",
                        collectiveData.getDonors().get(i).getDonor_type(),
                        "2",
                        "3"
                ));
            }
            for (int i = 0; i < collectiveData.getDonors().size(); i++) {
                for (Party party : collectiveData.getParty()) {
                    if (collectiveData.getDonors().get(i).getParty_id() == party.getParty_id()) {
                        donorTableViews.get(i).setContact_information(party.getParty_contact_info());
                        donorTableViews.get(i).setDate(party.getDate_added());
                        donorTableViews.get(i).setAddedBy(party.getEdited_by());
                    }
                }
            }
            ObservableList<DonorTableView> observableList = FXCollections.observableList(donorTableViews);
            donorTable.setItems(observableList);
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

    public void updateData(Donors newDonor, String contactNumber) throws IOException, URISyntaxException, InterruptedException {
        collectiveData.getDonors().add(newDonor);
        Party party = new Party();
        party.setParty_id(newDonor.getParty_id());
        party.setBarangay_id(newDonor.getBarangay_id());
        party.setParty_contact_info(contactNumber);
        party.setEdited_by(responseModel.getStaffID());
        party.setParty_type("D");
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        party.setDate_added(currentDate.format(formatter));
        collectiveData.getParty().add(party);
        ArrayList<DonorTableView> donorTableViews = new ArrayList<>();
        for (int i = 0; i < collectiveData.getDonors().size(); i++) {
            donorTableViews.add(new DonorTableView(
                    collectiveData.getDonors().get(i).getParty_id(),
                    collectiveData.getDonors().get(i).getDonor_name(),
                    "1",
                    collectiveData.getDonors().get(i).getDonor_type(),
                    "2",
                    "3"
            ));
        }
        for (int i = 0; i < collectiveData.getDonors().size(); i++) {
            for (Party party1 : collectiveData.getParty()) {
                if (collectiveData.getDonors().get(i).getParty_id() == party1.getParty_id()) {
                    donorTableViews.get(i).setContact_information(party1.getParty_contact_info());
                    donorTableViews.get(i).setDate(party1.getDate_added());
                    donorTableViews.get(i).setAddedBy(party1.getEdited_by());
                }
            }
        }
        ObservableList<DonorTableView> observableList = FXCollections.observableList(donorTableViews);
        donorTable.setItems(observableList);
        UpdateDonorsTableModel updateTable = new UpdateDonorsTableModel();
        updateTable.setDonor(newDonor);
        updateTable.setParty(party);
        updateTable.performPutRequest();
    }

    public void addBtnClicked(ActionEvent actionEvent) throws IOException {
        // Open Prompt
        Stage promptStage = new Stage();
        FXMLLoader loader = new FXMLLoader(App.class.getResource("View/donor-add-view.fxml"));
        Parent root = loader.load();
        DonorPromptController controller = loader.getController();
        controller.initData(this);
        Scene scene = new Scene(root);
        promptStage.setScene(scene);
        promptStage.setTitle("Add a Donor");
        promptStage.setResizable(false);
        promptStage.show();
    }
}
