package com.infomanagers.app.Model;

public class ItemInventory {
    private String barangay_id;
    private int item_id;
    private String item_name;
    private int item_quantity;
    private String item_category;
    private String item_expiration_date;
    public String getBarangay_id() {
        return barangay_id;
    }
    public void setBarangay_id(String barangay_id) {
        this.barangay_id = barangay_id;
    }
    public int getItem_id() {
        return item_id;
    }
    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }
    public String getItem_name() {
        return item_name;
    }
    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }
    public int getItem_quantity() {
        return item_quantity;
    }
    public void setItem_quantity(int item_quantity) {
        this.item_quantity = item_quantity;
    }
    public String getItem_category() {
        return item_category;
    }
    public void setItem_category(String item_category) {
        this.item_category = item_category;
    }
    public String getItem_expiration_date() {
        return item_expiration_date;
    }
    public void setItem_expiration_date(String item_expiration_date) {
        this.item_expiration_date = item_expiration_date;
    }
}
