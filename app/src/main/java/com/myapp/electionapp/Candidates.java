package com.myapp.electionapp;

import android.graphics.Bitmap;

public class Candidates {

    private Bitmap candidateImage;
    private String name;
    private String party;
    private Bitmap symbol;
    private String affidavit;

    public Candidates(Bitmap candidateImage, String name, String party, Bitmap symbol) {
        this.candidateImage = candidateImage;
        this.name = name;
        this.party = party;
        this.symbol = symbol;

    }

    public Bitmap getCandidateImage() {
        return candidateImage;
    }

    public String getName() {
        return name;
    }

    public String getParty() {
        return party;
    }

    public Bitmap getSymbol() {
        return symbol;
    }
}
