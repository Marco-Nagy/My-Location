package com.example.mylocation;

public class IpModel {
    String country;
    double lon;
    double lat;

    public String getCountry() {
        return country;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    public IpModel(String country, double lon, double lat) {
        this.country = country;
        this.lon = lon;
        this.lat = lat;
    }
}
