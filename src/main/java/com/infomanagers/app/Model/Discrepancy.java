package com.infomanagers.app.Model;

public class Discrepancy {
    private int discrepancy_id;
    private int item_id;
    private int units_lost;
    private String date_reported;
    private String barangay_id;
    private String staff_id;
    public int getDiscrepancy_id() {
        return discrepancy_id;
    }

    public void setDiscrepancy_id(int discrepancy_id) {
        this.discrepancy_id = discrepancy_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getUnits_lost() {
        return units_lost;
    }

    public void setUnits_lost(int units_lost) {
        this.units_lost = units_lost;
    }

    public String getDate_reported() {
        return date_reported;
    }

    public void setDate_reported(String date_reported) {
        this.date_reported = date_reported;
    }

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
}
