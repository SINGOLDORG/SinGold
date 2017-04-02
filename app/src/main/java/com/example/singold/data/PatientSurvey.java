package com.example.singold.data;

import android.widget.Button;
import android.widget.RadioGroup;

import com.example.singold.R;

/**
 * Created by user on 02/03/2017.
 */

public class PatientSurvey {


    @com.google.gson.annotations.SerializedName("id")
    private String id;
    @com.google.gson.annotations.SerializedName("idPatient")
    private String idPatient;
    @com.google.gson.annotations.SerializedName("country")
    private String country;
    @com.google.gson.annotations.SerializedName("year")
    private String year;
    @com.google.gson.annotations.SerializedName("youngMusic")
    private String youngMusic;
    @com.google.gson.annotations.SerializedName("homeMusic")
    private String homeMusic;
    @com.google.gson.annotations.SerializedName("education")
    private String education;
    @com.google.gson.annotations.SerializedName("language")
    private String language;
    @com.google.gson.annotations.SerializedName("religion")
    private String religion;
    @com.google.gson.annotations.SerializedName("fSinger")
    private String fSinger;
    @com.google.gson.annotations.SerializedName("fSongs")
    private String fSongs;
    @com.google.gson.annotations.SerializedName("classic")
    private String classic;
    @com.google.gson.annotations.SerializedName("israel")
    private String israel;
    @com.google.gson.annotations.SerializedName("dance")
    private String dance;
    @com.google.gson.annotations.SerializedName("loazi")
    private String loazi;
    @com.google.gson.annotations.SerializedName("relaxing")
    private String relaxing;


    public PatientSurvey() {
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }



    public String getYoungMusic() {
        return youngMusic;
    }

    public void setYoungMusic(String youngMusic) {
        this.youngMusic = youngMusic;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getHomeMusic() {
        return homeMusic;
    }

    public void setHomeMusic(String homeMusic) {
        this.homeMusic = homeMusic;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getfSinger() {
        return fSinger;
    }

    public void setfSinger(String fSinger) {
        this.fSinger = fSinger;
    }

    public String getfSongs() {
        return fSongs;
    }

    public void setfSongs(String fSongs) {
        this.fSongs = fSongs;
    }

    public String getClassic() {
        return classic;
    }

    public void setClassic(String classic) {
        this.classic = classic;
    }

    public String getIsrael() {
        return israel;
    }

    public void setIsrael(String israel) {
        this.israel = israel;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDance() {
        return dance;
    }

    public void setDance(String dance) {
        this.dance = dance;
    }

    public String getLoazi() {
        return loazi;
    }

    public void setLoazi(String loazi) {
        this.loazi = loazi;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getRelaxing() {
        return relaxing;
    }

    public void setRelaxing(String relaxing) {
        this.relaxing = relaxing;
    }


}

