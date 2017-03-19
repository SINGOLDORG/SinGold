package com.example.singold.data;

/**
 * Created by user on 02/03/2017.
 */

public class User
{
    @com.google.gson.annotations.SerializedName("id")
    private String id;
    @com.google.gson.annotations.SerializedName("idInstitute")
    private String idInstitute;
    @com.google.gson.annotations.SerializedName("fName")
    private String fName;
    @com.google.gson.annotations.SerializedName("lName")
    private String lName;
    @com.google.gson.annotations.SerializedName("username")
    private String username;
    @com.google.gson.annotations.SerializedName("EnterId")
    private String EnterId;


    public User() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdInstitute() {
        return idInstitute;
    }

    public void setIdInstitute(String idInstitute) {
        this.idInstitute = idInstitute;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEnterId() {
        return EnterId;
    }

    public void setEnterId(String enterId) {
        EnterId = enterId;
    }


}
