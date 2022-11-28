package com.example.researchproject;

// A class to represent a basic restaurant returned from the Yelp Restaurant List API
public class Restaurant {

    // Protected fields so that they can be accessed by subclass (DetailedRestaurant)
    protected String id;
    protected String name;
    protected String address;
    protected String city;
    protected String state;
    protected String zip;
    protected String price;
    protected String imageUrl;
    protected double rating;

    // Constructor for a basic restaurant
    public Restaurant(String id, String name, String address, String city, String state, String zip, String price, String imageUrl, double rating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.price = price;
        this.imageUrl = imageUrl;
        this.rating = rating;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    // Method to return a string representation of the restaurant's address
    public String getFormattedAddress() {
        return address + ", " + city + ", " + state + " " + zip;
    }

}
