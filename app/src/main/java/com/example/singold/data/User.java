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
    @com.google.gson.annotations.SerializedName("EnterId")
    private String EnterId;
    @com.google.gson.annotations.SerializedName("confId")
    private String confId;
    @com.google.gson.annotations.SerializedName("save")
    private String save;

    public User()

    {

    }

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

    public String getEnterId() {
        return EnterId;
    }

    public void setEnterId(String enterId) {
        EnterId = enterId;
    }

    public String getConfId() {
        return confId;
    }

    public void setConfId(String confId) {
        this.confId = confId;
    }

    public String getSave() {
        return save;
    }

    public void setSave(String save) {
        this.save = save;
    }
}
