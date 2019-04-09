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
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
        try{
            String link="http://avrutti.com/election/details.php";
            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
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
        for (int i=0; i<length; i=i+4){
            String name=splitArray[i];
            String capacity="Capacity - "+splitArray[i+1];
            String facilities="Facilities Available -"+splitArray[i+2];
            ar.add(splitArray[i+3]);
            String coordinateS = splitArray[i+3];
            String imageurl = "https://www.google.co.in/url?sa=i&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwjDj-71nMLhAhUW73MBHaH4AOgQjRx6BAgBEAU&url=https%3A%2F%2Fwww.blog.google%2Fproducts%2Fgoogle-pay%2Fannouncing-google-pay%2F&psig=AOvVaw0xwmH1JK1KSlhrGEEFugWp&ust=1554872319989117";
            myDetailsList.add(new myDetails(name,capacity,facilities,coordinateS,imageurl));
        }
        DetailsAdapter adapter = new DetailsAdapter(context, myDetailsList);
        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }
}
