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
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

          /*Intent i1=new Intent(Intent.ACTION_VIEW, Uri());
          startActivity(i1);*/
        candidatesList = new ArrayList<>();


        if (Main2Activity.lang == 1) {
            updateViews("hi");
        }
        else{
            updateViews("en");
        }
        candidatesList.add(
                new Candidates(R.drawable.vijaygoyal,
                        "Vijay Goyal",
                        "Bhartiya Janata Party",
                        R.drawable.bjpicon,
                       "click here"));


        candidatesList.add(
                new Candidates(R.drawable.pankajgupta,
                        "Pankaj Gupta",
                        " Aam Admi Party",
                        R.drawable.aapicon,
                        "Click Here "));


        candidatesList.add(
                new Candidates(R.drawable.kapilsibal,
                        "Kapil Sibal",
                        " Indian National Congress",
                        R.drawable.congressicon,
                        "Click Here "));


        candidatesList.add(
                new Candidates(R.drawable.narendrakumarpandey,
                        "Narendra Kumar Pandey",
                        " Bahujan Samaj Party",
                        R.drawable.bspicon,
                        "Click Here "));

        CandidateAdapter adapter = new CandidateAdapter(this, candidatesList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

    }
    public void updateViews(String languageCode){
        Context context=LocaleHelper.setLocale(this, languageCode);
        Resources resources=context.getResources();

        getSupportActionBar().setTitle(resources.getString(R.string.know_your_candidate));
    }
}
