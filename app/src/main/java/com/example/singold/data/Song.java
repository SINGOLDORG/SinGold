package com.example.singold.data;

/**
 * Created by user on 02/03/2017.
 */

public class Song {
    @com.google.gson.annotations.SerializedName("id")
    private String id;
    @com.google.gson.annotations.SerializedName("idPatient")
    private String idPatient;
    @com.google.gson.annotations.SerializedName("name")
    private String name;
    @com.google.gson.annotations.SerializedName("singer")
    private String singer;
    @com.google.gson.annotations.SerializedName("link")
    private String link;

    public Song(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(String idPatient) {
        this.idPatient = idPatient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
