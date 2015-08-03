package com.example.nova.models;

/**
 * Created by Nova on 2015-08-01.
 */
public class Comment {
    private String thumbnailUrl;
    private String name;
    private String comment;
    private double rating;

    public Comment(){

    }

    public Comment(String thumbnailUrl, String name,  double rating, String comment){
        this.thumbnailUrl = thumbnailUrl;
        this.comment = comment;
        this.rating = rating;
        this.name = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
