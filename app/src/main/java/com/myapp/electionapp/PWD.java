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

import org.w3c.dom.Text;

public class PWD extends AppCompatActivity {

    String uri="geo:0,0?q=india";
    TextView pickUpTimeText, clickPWDText;
    EditText mobileNumber;
    TimePicker pickTime;
    Button pickUpPointText, pwdAppText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwd);
        mobileNumber=findViewById(R.id.mobile_number);
        pickTime=findViewById(R.id.pickTime);
        pickUpTimeText=findViewById(R.id.pickUpTime);
        pickUpPointText=findViewById(R.id.pickUpPoint);
        clickPWDText=findViewById(R.id.clickPWDApp);
        pwdAppText=findViewById(R.id.pwdApp);
        if (Main2Activity.lang==1){
            updateViews("hi");
        }
        else{
            updateViews("en");
        }
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


        final Button pwdApp = (Button) findViewById(R.id.pwdApp);
        pwdApp.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                int hour = pickTime.getHour();
                int minute = pickTime.getMinute();
                String htime =  String.valueOf(hour);
                String mtime =  String.valueOf(minute);
                String time = htime + ":" +mtime;
                Toast.makeText(PWD.this, time, Toast.LENGTH_SHORT).show();
                store(mobileNumber.toString(), time);
                Intent pwdIntent = getPackageManager().getLaunchIntentForPackage("pwd.eci.com.pwdapp");
                if (pwdIntent!= null) {
                    startActivity(pwdIntent);
                } else {
                    Intent i=new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("https://play.google.com/store/apps/details?id=pwd.eci.com.pwdapp"));
                    startActivity(i);
                }
            }
        });

    }
    public void updateViews(String languageCode){
        Context context=LocaleHelper.setLocale(this, languageCode);
        Resources resources=context.getResources();

        getSupportActionBar().setTitle(resources.getString(R.string.facility_for_pwd));
        pickUpTimeText.setText(resources.getString(R.string.pick_up_time));
        pickUpPointText.setText(resources.getString(R.string.pick_up_point));
        clickPWDText.setText(resources.getString(R.string.click_on_button));
        pwdAppText.setText(resources.getString(R.string.open_pwd_app));
    }

    public void store(String s, String t){
        smsRequest sms  = new smsRequest(getApplicationContext());
        sms.execute(s,t);
    }
}
