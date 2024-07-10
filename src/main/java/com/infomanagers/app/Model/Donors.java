package com.infomanagers.app.Model;

public class Donors {
    private int party_id;
    private String donor_name;
    private String donor_type;
    private String barangay_id;

    public int getParty_id() {
        return party_id;
    }

    public void setParty_id(int party_id) {
        this.party_id = party_id;
    }

    public String getDonor_name() {
        return donor_name;
    }

    public void setDonor_name(String donor_name) {
        this.donor_name = donor_name;
    }

    public String getDonor_type() {
        return donor_type;
    }

    public void setDonor_type(String donor_type) {
        this.donor_type = donor_type;
    }

    public String getBarangay_id() {
        return barangay_id;
    }

    public void setBarangay_id(String barangay_id) {
        this.barangay_id = barangay_id;
    }
}
