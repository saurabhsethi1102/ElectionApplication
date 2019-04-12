package com.myapp.electionapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class candidateDetails extends AsyncTask<String,Void,String> {
    Context context;
    RecyclerView recyclerView;
    public candidateDetails(Context context, RecyclerView recyclerView){
        this.context=context;
        this.recyclerView=recyclerView;
    }
    @Override
    protected String doInBackground(String... strings) {
        String psname = PollingStation.psname;
        try{
            String link="http://laxmiwafersncones.com/candidatedetails.php";
            String data  = URLEncoder.encode("psname", "UTF-8") + "=" +
                    URLEncoder.encode(psname, "UTF-8");
            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setReadTimeout(100000);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            wr.flush();

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
    protected void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        ArrayList<Object> candidatesList = new ArrayList<>();
        String[] a=result.split("\\|");
        for(int i=0; i<a.length; i+=4){
//            candidatesList.add(
//                    new Candidates(R.drawable.vijaygoyal    ,
//                            "Vijay Goyal",
//                            "Bhartiya Janata Party",
//                            R.drawable.bjpicon));

        }

        CandidateAdapter adapter = new CandidateAdapter(context, candidatesList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

    }
}
