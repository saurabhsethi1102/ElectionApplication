package com.myapp.electionapp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class GetDetails extends AsyncTask<String,Void,String> {
    Context context;
    RecyclerView recyclerView;
    List<myDetails> myDetailsList;
    ArrayList<String> ar = new ArrayList<String>();

    public GetDetails(Context context, RecyclerView recyclerView){
        this.context = context;
        this.recyclerView = recyclerView;
    }
    @Override
    protected String doInBackground(String... strings) {
        String psname = PollingStation.psname;
        try{
            String link="http://avrutti.com/election/details.php";
            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            // Read Server Response
            while((line = reader.readLine()) != null) {
                sb.append(line);
                break;
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "Exception: " + e.getMessage();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "Exception: " + e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return "Exception: " + e.getMessage();
        }
    }
    @Override
    protected void onPostExecute(String result){
        Log.d("Result Activity",result);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        myDetailsList = new ArrayList<>();
        String[] splitArray = result.split("\\|");
        int length = (splitArray.length);
        for (int i=0; i<length; i=i+16){
            String name=splitArray[i];
            String capacity="No. of People in Queue - "+splitArray[i+1];
            String gis= splitArray[i+2];
            String facilities="Facilities Available - ";
            if (splitArray[i+3]=="1") {
                facilities += "Creche,";
            }
//            if (splitArray[i+4]) {
//                facilities += 'drinking_water,';
//            }
//            if (splitArray[i+5]) {
//                facilities += 'food,';
//            }
//            if (splitArray[i+6]) {
//                facilities += 'furniture,';
//            }
//            if (splitArray[i+7]) {
//                facilities += 'helpdesk,';
//            }
//            if (splitArray[i+8]) {
//                facilities += 'lighting,';
//            }
//            if (splitArray[i+9]) {
//                facilities += 'medical_kit,';
//            }
//            if (splitArray[i+10]) {
//                facilities += 'ramp,';
//            }
//            if (splitArray[i+11]) {
//                facilities += 'shed,';
//            }
//            if (splitArray[i+12]) {
//                facilities += 'signage,';
//            }
//            if (splitArray[i+13]) {
//                facilities += 'toilet,';
//            }
//            if (splitArray[i+14]) {
//                facilities += 'volunteer,';
//            }
//            if (splitArray[i+15]) {
//                facilities += 'wheelchair,';
//            }
            ar.add(splitArray[i+3]);
            String coordinateS = splitArray[i+3];
            String imageurl ="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…";
            myDetailsList.add(new myDetails(name,capacity,facilities,coordinateS,imageurl));
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        }
        DetailsAdapter adapter = new DetailsAdapter(context, myDetailsList);
//        setting adapter to recyclerview0
        recyclerView.setAdapter(adapter);
    }
}
