package com.myapp.electionapp;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AllPollingStation extends AppCompatActivity {


    private SearchView searchView;


    List<AllPollingData> pollingList;

    //the recyclerview
    RecyclerView recyclerView;
    AllPollingStationAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_polling_station);
        searchView = (SearchView) findViewById(R.id.containersearchView);



        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        fetch();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        pollingList = new ArrayList<>();


         adapter = new AllPollingStationAdapter(this, pollingList);
        
        recyclerView.setAdapter(adapter);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(pollingList.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(AllPollingStation.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

    }
    public void fetch(){
        GetDetails getDetails = new GetDetails(this,recyclerView);
        getDetails.execute();
    }
}
