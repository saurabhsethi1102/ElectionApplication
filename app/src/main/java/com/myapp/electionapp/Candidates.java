package com.myapp.electionapp;

public class Candidates {

    private int candidateImage;
    private String name;
    private String party;
    private int symbol;
    private String affidavit;

    public Candidates(int candidateImage, String name, String party, int symbol, String affidavit) {
        this.candidateImage = candidateImage;
        this.name = name;
        this.party = party;
        this.symbol = symbol;
        this.affidavit = affidavit;
    }

    public int getCandidateImage() {
        return candidateImage;
    }

    public String getName() {
        return name;
    }

    public String getParty() {
        return party;
    }

    public int getSymbol() {
        return symbol;
    }

    public String getAffidavit() {
        return affidavit;
    }
}
