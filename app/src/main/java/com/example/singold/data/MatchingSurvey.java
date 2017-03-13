package com.example.singold.data;

/**
 * Created by user on 02/03/2017.
 */

public class MatchingSurvey {
    @com.google.gson.annotations.SerializedName("id")
    private String id;
    @com.google.gson.annotations.SerializedName("country")
    private String country;
    @com.google.gson.annotations.SerializedName("year")
    private String year;
    @com.google.gson.annotations.SerializedName("language")
    private String language;
    @com.google.gson.annotations.SerializedName("city")
    private String city;

//fgffddfgfglklkl;
    ///zxczxczx


    public MatchingSurvey(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
