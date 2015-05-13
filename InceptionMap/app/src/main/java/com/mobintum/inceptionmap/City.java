package com.mobintum.inceptionmap;

import java.util.ArrayList;

/**
 * Created by Rick on 13/05/15.
 */
public class City {

    private double lat;
    private double lon;
    private String nameCity;
    private String picture;

    public City(double lat, double lon, String nameCity, String picture) {
        this.lat = lat;
        this.lon = lon;
        this.nameCity = nameCity;
        this.picture = picture;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }


    public static ArrayList<City> getData(){
        ArrayList<City> cities = new ArrayList<City>();



        cities.add(new City(48.860327, 2.342425,"Paris","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQQn0X5TyNRAHifOpCF7dQNXmt3dFMY5HHHq_IIEIVTU6LnnuaB"));
        cities.add(new City(40.717626, -73.997534,"New York","http://cache.graphicslib.viator.com/graphicslib/thumbs674x446/2625/SITours/new-york-city-in-one-day-small-group-sightseeing-tour-in-new-york-city-147596.jpg"));
        cities.add(new City(37.784513, -122.405079,"San Francisco","https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcS2XDhxWrqYlHgeGYVP-xycedjruBzivTt5NUrL2RESditLhV4jBQ"));

        return cities;
    }

    public static ArrayList<String> getNames(){
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<City> cities =City.getData();
        for(int i =0; i<cities.size();i++){
            names.add(cities.get(i).getNameCity());
        }

        return names;
    }
}
