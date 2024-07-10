package com.infomanagers.app.Model;

public class Account {
    private String barangay_id;
    private String staff_id;
    private String account_type;
    private Barangay barangay;
    public String getBarangay_id() {
        return barangay_id;
    }
    public void setBarangay_id(String barangay_id) {
        this.barangay_id = barangay_id;
    }
    public String getStaff_id() {
        return staff_id;
    }
    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }
    public String getAccount_type() {
        return account_type;
    }
    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }
    public Barangay getBarangay() {
        return barangay;
    }
    public void setBarangay(Barangay barangay) {
        this.barangay = barangay;
    }
}
