package com.myapp.electionapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;

import java.util.ArrayList;
import java.util.List;

public class knowCandidate extends AppCompatActivity {

    List<Candidates> candidatesList;

    //the recyclerview
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_know_candidate);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        candidateDetails candidateDetails=new candidateDetails(this, recyclerView);
        candidateDetails.execute();
    }
    public void updateViews(String languageCode){
        Context context=LocaleHelper.setLocale(this, languageCode);
        Resources resources=context.getResources();

        getSupportActionBar().setTitle(resources.getString(R.string.know_your_candidate));
    }
}
