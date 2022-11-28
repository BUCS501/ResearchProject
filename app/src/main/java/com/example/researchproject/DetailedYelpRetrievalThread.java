package com.example.researchproject;


import android.app.Activity;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.CollationKey;
import java.util.ArrayList;
import java.util.Collections;

import javax.net.ssl.HttpsURLConnection;

// Class to get the information of a restaurant from the Detailed Restaurant Yelp API in the background
public class DetailedYelpRetrievalThread extends Thread {

    // Fields
    private static final String BASE_URL = "https://api.yelp.com/v3/businesses/";
    private final String id;
    private MainActivity originActivity;
    // TODO: decide if we want to use a fragment or an activity to display the detailed restaurant

    // Constructor when the id of the restaurant is known
    public DetailedYelpRetrievalThread(String id, MainActivity detailedRestaurantActivity) {
        this.id = id;
        this.originActivity = detailedRestaurantActivity;
    }

    // Constructor when the id of the restaurant is not known (used for testing and demo purposes)
    public DetailedYelpRetrievalThread(MainActivity mainActivity) {
        this.id = "WavvLdfdP6g8aZTtbBQHTw";
        this.originActivity = mainActivity;
    }

    // run() method as required by the Thread class
    public void run() {
        try {
            getDetailedRestaurant();
        } catch (IOException | JSONException | ParseException e) {
            e.printStackTrace();
        }
    }

    // Method to get the information of a restaurant from the Detailed Restaurant Yelp API
    private void getDetailedRestaurant() throws IOException, JSONException, ParseException {
        // Create the URL and open the connection using HttpsURLConnection
        URL url = new URL(BASE_URL + id);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + YelpAPIKey.API_KEY);
        connection.connect();
        // Get the response code and check if the connection was successful
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            System.out.println("Success");
        } else {
            System.out.println("Failed");
            return;
        }
        // If we reach here, the connection was successful and we can get the information
        // Create a BufferedReader to read the response
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String result = in.readLine();
        // Parse the response into a JSONObject
        JSONObject restaurant = (JSONObject) new JSONParser().parse(result);
        // Get the necessary information from the JSONObject
        String id = (String) restaurant.get("id");
        String name = (String) restaurant.get("name");
        String address = (String) ((JSONObject) restaurant.get("location")).get("address1");
        String city = (String) ((JSONObject) restaurant.get("location")).get("city");
        String state = (String) ((JSONObject) restaurant.get("location")).get("state");
        String zip = (String) ((JSONObject) restaurant.get("location")).get("zip_code");
        String price = (String) restaurant.get("price");
        String imageUrl = (String) restaurant.get("image_url");
        double rating = (double) restaurant.get("rating");
        String phone = (String) restaurant.get("phone");
        String displayedPhone = (String) restaurant.get("display_phone");
        String urlStr = (String) restaurant.get("url");
        JSONArray hoursOuter = (JSONArray) restaurant.get("hours");
        JSONArray hoursInner;
        // Hours may not be provided by API so we need to check if it is null
        if (hoursOuter != null) {
            hoursInner = (JSONArray) ((JSONObject) hoursOuter.get(0)).get("open");
        } else {
            hoursInner = new JSONArray();
        }
        ArrayList<Hours> hours = parseHours(hoursInner);
        Collections.sort(hours);
        // Create a new DetailedRestaurant object and pass it to the origin activity
        DetailedRestaurant detailedRestaurant = new DetailedRestaurant(id, name, address, city, state, zip, price, imageUrl, rating, phone, displayedPhone, urlStr, hours);
        originActivity.setRestaurant(detailedRestaurant);
    }

    // Method to parse the hours from the JSONArray into an ArrayList of Hours objects as one day may have multiple hours
    private ArrayList<Hours> parseHours(JSONArray hoursInner) {
        ArrayList<Hours> hours = new ArrayList<>();
        for (int i = 0; i < hoursInner.size(); i++) {
            JSONObject day = (JSONObject) hoursInner.get(i);
            long dayOfWeek = (long) day.get("day");
            String start = (String) day.get("start");
            String end = (String) day.get("end");
            hours.add(new Hours(dayOfWeek, start, end));
        }
        return hours;
    }

    public MainActivity getOriginActivity() {
        return originActivity;
    }

    public void setOriginActivity(MainActivity originActivity) {
        this.originActivity = originActivity;
    }
}
