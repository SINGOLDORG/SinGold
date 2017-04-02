package com.example.singold.data;

/**
 * Created by user on 02/03/2017.
 */

public class MatchingSurvey {

    @com.google.gson.annotations.SerializedName("country")
    private String country;
    @com.google.gson.annotations.SerializedName("year")
    private String year;
    @com.google.gson.annotations.SerializedName("language")
    private String language;
    @com.google.gson.annotations.SerializedName("religion")
    private String religion;


    public MatchingSurvey(){}



    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }
}
