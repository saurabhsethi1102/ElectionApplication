package com.myapp.electionapp;


import android.graphics.Bitmap;
import android.widget.TextView;

public class AllPollingData {

    Bitmap image;
    String boothName,pId,facility,no_of_people;


    public AllPollingData(Bitmap image, String boothName, String pId, String facility,String no_of_people) {
        this.image = image;
        this.boothName = boothName;
        this.pId = pId;
        this.facility = facility;
        this.no_of_people = no_of_people;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getNo_of_people() {
        return no_of_people;
    }

    public String getBoothName() {
        return boothName;
    }

    public String getpId() {
        return pId;
    }

    public String getFacility() {
        return facility;
    }
}
