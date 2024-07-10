package com.infomanagers.app.Model;

public class TransactionDetail {
    private int transaction_id;
    private int item_id;
    private int transaction_quantity;
    private String barangay_id;

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getTransaction_quantity() {
        return transaction_quantity;
    }

    public void setTransaction_quantity(int transaction_quantity) {
        this.transaction_quantity = transaction_quantity;
    }

    public String getBarangay_id() {
        return barangay_id;
    }

    public void setBarangay_id(String barangay_id) {
        this.barangay_id = barangay_id;
    }
}
