package com.infomanagers.app.Model;

public class AuthorizedRecipient {
    private int party_id;
    private String first_name;
    private String last_name;
    private String ar_address;
    private int family_size;
    private String barangay_id;
    public int getParty_id() {
        return party_id;
    }

    public void setParty_id(int party_id) {
        this.party_id = party_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAr_address() {
        return ar_address;
    }

    public void setAr_address(String ar_address) {
        this.ar_address = ar_address;
    }

    public int getFamily_size() {
        return family_size;
    }

    public void setFamily_size(int family_size) {
        this.family_size = family_size;
    }

    public String getBarangay_id() {
        return barangay_id;
    }

    public void setBarangay_id(String barangay_id) {
        this.barangay_id = barangay_id;
    }
}
