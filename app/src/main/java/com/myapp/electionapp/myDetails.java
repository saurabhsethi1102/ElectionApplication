package com.myapp.electionapp;

public class myDetails {
//    private String imageText;
    private String name;
    private String capacity;
    private String facilities;
    private String coordinates;
    private String link;
    public myDetails(String name, String capacity, String facilities,String coordinates,String link) {
//        this.imageText = imageText;
        this.name = name;
        this.capacity = capacity;
        this.facilities = facilities;
        this.coordinates = coordinates;
        this.link = link;
    }

//    public String getImageText() {
//        return imageText;
//    }

    public String getName() {
        return name;
    }
    public String getCapacity() {
        return capacity;
    }
    public String getFacilities() {
        return facilities;
    }
    public String getCoordinates() {
        return coordinates;
    }
    public String getLink() {
        return link;
    }


}
