package com.codiseo.starbuzz;

public class Drink {

    private String name;

    private String description;

    private int photoId;

    private boolean isFavorite;

    public Drink(String name, String description, int photoId, boolean isFavorite) {
        this.name = name;
        this.description = description;
        this.photoId = photoId;
        this.isFavorite = isFavorite;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPhotoId() {
        return photoId;
    }

    public boolean isFavorite() {
        return isFavorite;
    }
}
