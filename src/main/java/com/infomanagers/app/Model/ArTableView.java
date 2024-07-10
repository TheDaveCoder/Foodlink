package com.infomanagers.app.Model;

public class ArTableView {
    private int party_id;
    private String last_name;
    private String first_name;
    private String address;
    private int family_size;
    private String date_added;
    private String modified_by;

    public ArTableView(int party_id, String last_name, String first_name, String address, int family_size, String date_added, String modified_by) {
        this.party_id = party_id;
        this.last_name = last_name;
        this.first_name = first_name;
        this.address = address;
        this.family_size = family_size;
        this.date_added = date_added;
        this.modified_by = modified_by;
    }

    public int getParty_id() {
        return party_id;
    }
    public void setParty_id(int party_id) {
        this.party_id = party_id;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getFamily_size() {
        return family_size;
    }
    public void setFamily_size(int family_size) {
        this.family_size = family_size;
    }
    public String getDate_added() {
        return date_added;
    }
    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }
    public String getModified_by() {
        return modified_by;
    }
    public void setModified_by(String modified_by) {
        this.modified_by = modified_by;
    }
}
