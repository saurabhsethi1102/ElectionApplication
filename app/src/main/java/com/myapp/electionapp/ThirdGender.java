package com.myapp.electionapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class ThirdGender extends AppCompatActivity {

    String uri="geo:0,0?q=india";
    TextView pickUpTImeText;
    EditText mobile_number;
    TimePicker pickTime;
    Button pickUpPointText, submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_gender);
        pickUpPointText=findViewById(R.id.pickUpPoint);
        pickUpTImeText=findViewById(R.id.pickUptime);
        mobile_number=findViewById(R.id.mobile_number);
        pickTime=findViewById(R.id.pickUpTime);
        submit=findViewById(R.id.submit);
        if (Main2Activity.lang==1){
            updateViews("hi");
        }
        else{
            updateViews("en");
        }

        submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                int hour = pickTime.getHour();
                int minute = pickTime.getMinute();
                String htime =  String.valueOf(hour);
                String mtime =  String.valueOf(minute);
                String time = htime + ":" +mtime;
                Toast.makeText(ThirdGender.this, time, Toast.LENGTH_SHORT).show();
                store(mobile_number.toString(), time);

            }
        });

        Button pickUpPoint = (Button) findViewById(R.id.pickUpPoint);
        pickUpPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse(uri);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });

    }

    public void updateViews(String languageCode){
        Context context=LocaleHelper.setLocale(this, languageCode);
        Resources resources=context.getResources();

        getSupportActionBar().setTitle(resources.getString(R.string.facilities_for_third_gender));
        pickUpTImeText.setText(resources.getString(R.string.pick_up_time));
        pickUpPointText.setText(resources.getString(R.string.pick_up_point));
        submit.setText(resources.getString(R.string.submit_request));

    }
    public void store(String s, String t){
        smsRequest sms  = new smsRequest(getApplicationContext());
        sms.execute(s,t);
    }
}
