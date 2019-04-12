package com.myapp.electionapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class PollingStation2 extends AppCompatActivity {

    SearchView containersearchView;
    ListView listView;
    TextView textView;
    ArrayList<String> containersearchlist;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polling_station2);

        containersearchView =findViewById(R.id.containersearchView);
        listView=findViewById(R.id.listView);
        textView=findViewById(R.id.textView);

        containersearchlist = new ArrayList<>();
        containersearchlist.add("4 - Adarsh Nagar");
        containersearchlist.add("14 - Shalimar Bagh");
        containersearchlist.add("15 - Shakur Basti");
        containersearchlist.add("16 - Tri Nagar");
        containersearchlist.add("17 - Wazirpur");
        containersearchlist.add("18 - Model Town");
        containersearchlist.add("19 - Sadar Bazar");
        containersearchlist.add("20 - Chandni Chowk");
        containersearchlist.add("21 - Matia Mahal");
        containersearchlist.add("22 - Ballimaran");

        final ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.pooling_station_list,containersearchlist);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String product = ((TextView) view).getText().toString();

                Toast.makeText(PollingStation2.this,""+product,Toast.LENGTH_LONG).show();

       /*         Intent i = new Intent(getApplicationContext(), );
                // sending data to new activity
                i.putExtra("product", product);
                startActivity(i);*/

            }
        });


        containersearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(containersearchlist.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(PollingStation2.this, "No Match found",Toast.LENGTH_LONG).show();
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
}
