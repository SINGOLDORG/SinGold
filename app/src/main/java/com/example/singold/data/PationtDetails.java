package com.example.singold.data;

/**
 * Created by user on 02/03/2017.
 */

public class PationtDetails {
    @com.google.gson.annotations.SerializedName("id")
    private String id;
    @com.google.gson.annotations.SerializedName("fName")
    private String fName;
    @com.google.gson.annotations.SerializedName("lName")
    private String lName;
    @com.google.gson.annotations.SerializedName("familyPhone")
    private String familyPhone;
    @com.google.gson.annotations.SerializedName("address")
    private String address;
    @com.google.gson.annotations.SerializedName("pId")
    private String pId;
    @com.google.gson.annotations.SerializedName("year")
    private String year;

    public PationtDetails(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getFamilyPhone() {
        return familyPhone;
    }

    public void setFamilyPhone(String familyPhone) {
        this.familyPhone = familyPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
