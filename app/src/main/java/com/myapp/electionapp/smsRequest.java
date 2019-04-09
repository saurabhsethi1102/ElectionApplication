package com.myapp.electionapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class smsRequest extends AsyncTask<String, Void, String> {
    private Context context;
    smsRequest(Context context){
        this.context =context;
    }
    @Override
    protected String doInBackground(String... strings) {
        String mobile = strings[0];
        String time = strings[1];
        String link="http://avrutti.com/election/smsRequest.php";
        try{
            String data  = URLEncoder.encode("mobile", "UTF-8") + "=" +
                    URLEncoder.encode(mobile, "UTF-8");
            data  += "&" + URLEncoder.encode("time", "UTF-8") + "=" +
                    URLEncoder.encode(time, "UTF-8");
            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setReadTimeout(100000);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            wr.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            // Read Server Response
            while((line = reader.readLine()) != null) {
                sb.append(line);
                //break;
            }
            return sb.toString();


        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            return "Exception URL: " + e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return "Exception IO: " + e.getMessage();
        }
    }
    @Override
    protected void onPostExecute(String result){
        Log.d("Result Activity",result);
        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
    }
}
