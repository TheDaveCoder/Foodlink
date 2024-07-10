package com.infomanagers.app.Model;

public class DonorTableView {
    public int getParty_id() {
        return party_id;
    }

    public String getDonor_name() {
        return donor_name;
    }

    public String getContact_information() {
        return contact_information;
    }

    public String getType() {
        return type;
    }

    public String getDate_added() {
        return date_added;
    }

    public String getEdited_by() {
        return edited_by;
    }

    private int party_id;
    private String donor_name;
    private String contact_information;
    private String type;
    private String date_added;
    private String edited_by;
    public DonorTableView(int party_id, String donor_name, String contact_information, String type, String date, String addedBy) {
        this.party_id = party_id;
        this.donor_name = donor_name;
        this.contact_information = contact_information;
        this.type = type;
        this.date_added = date;
        this.edited_by = addedBy;
    }
    public void setContact_information(String ci) {
        this.contact_information = ci;
    }
    public void setDate(String date) {
        this.date_added = date;
    }
    public void setAddedBy(String ab) {
        this.edited_by = ab;
    }
}
