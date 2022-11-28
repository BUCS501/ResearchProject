package com.example.researchproject;

import java.util.ArrayList;
import java.util.HashMap;

// A class to represent a detailed restaurant returned from the Yelp Restaurant Details API
public class DetailedRestaurant extends Restaurant {

//    protected String id;
//    protected String name;
//    protected String address;
//    protected String city;
//    protected String state;
//    protected String zip;
//    protected String price;
//    protected String imageUrl;
//    protected double rating;
    // Additional fields for a detailed restaurant
    private String phoneNumber;
    private String displayedPhoneNumber;
    private String restaurantUrl;
    private ArrayList<Hours> weekHours;
    private HashMap<Long, ArrayList<Hours>> weekHoursMap;

    // Constructor for a detailed restaurant with only the basic fields
    public DetailedRestaurant(String id, String name, String address, String city, String state, String zip, String price, String imageUrl, double rating) {
        super(id, name, address, city, state, zip, price, imageUrl, rating);
    }

    // Constructor for a detailed restaurant with all fields
    public DetailedRestaurant(String id, String name, String address, String city, String state, String zip, String price, String imageUrl, double rating, String phoneNumber, String displayedPhoneNumber, String restaurantUrl, ArrayList<Hours> weekHours) {
        super(id, name, address, city, state, zip, price, imageUrl, rating);
        this.phoneNumber = phoneNumber;
        this.displayedPhoneNumber = displayedPhoneNumber;
        this.restaurantUrl = restaurantUrl;
        this.weekHours = weekHours;
        this.weekHoursMap = new HashMap<Long, ArrayList<Hours>>();
        initWeekHoursMap();
    }

    // Constructor for a detailed restaurant with a basic restaurant and all extra fields
    public DetailedRestaurant(Restaurant restaurant, String phoneNumber, String displayedPhoneNumber, String restaurantUrl, ArrayList<Hours> weekHours) {
        super(restaurant.getId(), restaurant.getName(), restaurant.getAddress(), restaurant.getCity(), restaurant.getState(), restaurant.getZip(), restaurant.getPrice(), restaurant.getImageUrl(), restaurant.getRating());
        this.phoneNumber = phoneNumber;
        this.displayedPhoneNumber = displayedPhoneNumber;
        this.restaurantUrl = restaurantUrl;
        this.weekHours = weekHours;
        this.weekHoursMap = new HashMap<Long, ArrayList<Hours>>();
        initWeekHoursMap();
    }

    // Getters and setters
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDisplayedPhoneNumber() {
        return displayedPhoneNumber;
    }

    public void setDisplayedPhoneNumber(String displayedPhoneNumber) {
        this.displayedPhoneNumber = displayedPhoneNumber;
    }

    public String getRestaurantUrl() {
        return restaurantUrl;
    }

    public void setRestaurantUrl(String restaurantUrl) {
        this.restaurantUrl = restaurantUrl;
    }

    public ArrayList<Hours> getWeekHours() {
        return weekHours;
    }

    public void setWeekHours(ArrayList<Hours> weekHours) {
        this.weekHours = weekHours;
    }

    public HashMap<Long, ArrayList<Hours>> getWeekHoursMap() {
        return weekHoursMap;
    }

    public void setWeekHoursMap(HashMap<Long, ArrayList<Hours>> weekHoursMap) {
        this.weekHoursMap = weekHoursMap;
    }

    public ArrayList<Hours> getHoursForDay(long day) {
        return weekHoursMap.get(day);
    }

    // Fill the weekHoursMap with info for a specific day
    public void setHoursForDay(Hours hours) {
        if (weekHoursMap == null) {
            weekHoursMap = new HashMap<>();
        }
        ArrayList<Hours> hoursForDay = weekHoursMap.get(hours.getDay());
        if (hoursForDay == null) {
            hoursForDay = new ArrayList<>();
        }
        hoursForDay.add(hours);
        weekHoursMap.put(hours.getDay(), hoursForDay);
    }

    // Initialize the weekHoursMap
    public void initWeekHoursMap() {
        for (Hours hours : weekHours) {
             // if hours not in map, add it
            ArrayList<Hours> hoursForDay = weekHoursMap.get(hours.getDay());
            if (hoursForDay == null) {
                hoursForDay = new ArrayList<>();
            }
            hoursForDay.add(hours);
            weekHoursMap.put(hours.getDay(), hoursForDay);
        }

    }

    // Convert the weekHoursMap to a string
    public String getFormattedHoursForDay(long day) {
        ArrayList<Hours> hoursForDay = weekHoursMap.get(day);
        if (hoursForDay == null) {
            return "Closed";
        }
        StringBuilder formattedHours = new StringBuilder();
        for (Hours hours : hoursForDay) {
            formattedHours.append(hours.getFormattedHours()).append(" ");
        }
        return formattedHours.toString();
    }

    public boolean isOpen() {
        // TODO: Implement this method if time permits, current struggle is with API version being too low (21 when 26 is required)
        return true;
    }

}
