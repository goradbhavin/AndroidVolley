package com.gb.mysecondvolleydemo;

import java.util.List;

/**
 * Created by bhavin on 8/4/2018.
 */

public class Movie {

    private String title,imageUrl;
    private int year;
    private double rating;
    private List<String> genre;

    public Movie(String title, String imageUrl, int year, double rating, List<String> genre) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.year = year;
        this.rating = rating;
        this.genre = genre;
    }

    public Movie() {

    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getYear() {
        return year;
    }

    public double getRating() {
        return rating;
    }

    public List<String> getGenre() {
        return genre;
    }
}
