package com.infomanagers.app.Model;

public class Party {
    private int party_id;
    private String barangay_id;
    private String party_contact_info;
    private String edited_by;
    private String party_type;
    private String date_added;
    public int getParty_id() {
        return party_id;
    }

    public void setParty_id(int party_id) {
        this.party_id = party_id;
    }

    public String getBarangay_id() {
        return barangay_id;
    }

    public void setBarangay_id(String barangay_id) {
        this.barangay_id = barangay_id;
    }

    public String getParty_contact_info() {
        return party_contact_info;
    }

    public void setParty_contact_info(String party_contact_info) {
        this.party_contact_info = party_contact_info;
    }

    public String getEdited_by() {
        return edited_by;
    }

    public void setEdited_by(String edited_by) {
        this.edited_by = edited_by;
    }

    public String getParty_type() {
        return party_type;
    }

    public void setParty_type(String party_type) {
        this.party_type = party_type;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }

}
