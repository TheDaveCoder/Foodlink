package com.infomanagers.app.Model;
import java.util.ArrayList;

public class CollectiveData {
    private int statusCode;
    private ArrayList<ItemInventory> itemInventory;
    private ArrayList<TransactionDetail> transactionDetail;
    private ArrayList<Transaction> transaction;
    private ArrayList<Party> party;
    private ArrayList<Donors> donors;
    private ArrayList<AuthorizedRecipient> authorizedRecipients;
    private ArrayList<Discrepancy> discrepancies;
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    public int getStatusCode() {
        return statusCode;
    }
    public ArrayList<ItemInventory> getItemInventory() {
        return itemInventory;
    }

    public void setItemInventory(ArrayList<ItemInventory> itemInventory) {
        this.itemInventory = itemInventory;
    }

    public ArrayList<TransactionDetail> getTransactionDetail() {
        return transactionDetail;
    }

    public void setTransactionDetail(ArrayList<TransactionDetail> transactionDetail) {
        this.transactionDetail = transactionDetail;
    }

    public ArrayList<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(ArrayList<Transaction> transaction) {
        this.transaction = transaction;
    }

    public ArrayList<Party> getParty() {
        return party;
    }

    public void setParty(ArrayList<Party> party) {
        this.party = party;
    }

    public ArrayList<Donors> getDonors() {
        return donors;
    }

    public void setDonors(ArrayList<Donors> donors) {
        this.donors = donors;
    }

    public ArrayList<AuthorizedRecipient> getAuthorizedRecipients() {
        return authorizedRecipients;
    }

    public void setAuthorizedRecipients(ArrayList<AuthorizedRecipient> authorizedRecipients) {
        this.authorizedRecipients = authorizedRecipients;
    }
    public ArrayList<Discrepancy> getDiscrepancies() {
        return discrepancies;
    }

    public void setDiscrepancies(ArrayList<Discrepancy> discrepancies) {
        this.discrepancies = discrepancies;
    }
}
