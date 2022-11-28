package com.example.researchproject;

// A class to represent the hours a restaurant is open
public class Hours implements Comparable<Hours> {

    // Fields
    private long day;
    private String open;
    private String close;

    // Constructor
    public Hours(long day, String open, String close) {
        this.day = day;
        this.open = open;
        this.close = close;
    }

    // Getters and setters
    public long getDay() {
        return day;
    }

    public void setDay(long day) {
        this.day = day;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    // Method to create the formatted time using 12-hour clock with AM/PM
    public String getFormattedHours() {
        int openHour = Integer.parseInt(open.substring(0, 2));
        int closeHour = Integer.parseInt(close.substring(0, 2));
        String openAmPm = openHour < 12 ? "AM" : "PM";
        String closeAmPm = closeHour < 12 ? "AM" : "PM";
        String formattedOpenHour = openHour == 12 ? "12" : String.valueOf(openHour % 12);
        String formattedCloseHour = closeHour == 12 ? "12" : String.valueOf(closeHour % 12);
        String formattedOpen = formattedOpenHour + ":" + open.substring(2) + " " + openAmPm;
        String formattedClose = formattedCloseHour + ":" + close.substring(2) + " " + closeAmPm;
        return formattedOpen + " - " + formattedClose;
    }

    // Method to compare two hours objects
    @Override
    public int compareTo(Hours other) {
        return Long.compare(this.day, other.day);
    }
}
