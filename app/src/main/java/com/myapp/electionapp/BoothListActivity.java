package com.myapp.electionapp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class BoothListActivity extends AppCompatActivity {
   public static String[] boothNames = {"Booth1A", "Booth2B", "Booth 3", "Booth 4", "Booth 5", "Booth 6", "Booth 7", "Booth 8", "Booth 9", "Booth 10", "Booth 11"};
    private String[] boothCoordingate = {"19.076,72.8777","Connaught+Place,+New+Delhi,Delhi","19.076,72.8777","19.076,72.8777","19.076,72.8777","19.076,72.8777","19.076,72.8777","19.076,72.8777","19.076,72.8777","19.076,72.8777","19.076,72.8777"};
    private int[] boothImages={R.drawable.booth,R.drawable.booth,R.drawable.booth,R.drawable.booth,R.drawable.booth,R.drawable.booth,R.drawable.booth,R.drawable.booth,R.drawable.booth,R.drawable.booth,R.drawable.booth};
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booth_list);
//        getBoothName getNames = new getBoothName(this);
//         getNames.execute();
        listView = (ListView)findViewById(R.id.boothListView);
        runlist();
    }

    public void runlist(){
        final CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position1, long id) {
                //CustomDialogue customDialogue = new CustomDialogue();
                //customDialogue.show(getSupportFragmentManager(),"Custom Dialogue");

                //Uri gmmIntentUri = Uri.parse("google.navigation:q=Connaught+Place,+New+Delhi,Delhi");
                    Uri gmmIntentUri = Uri.parse("google.navigation:q="+boothCoordingate[position1]);
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    if (mapIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(mapIntent);
                }

            }
        });
    }
    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return boothNames.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if(position == -1){
                convertView = getLayoutInflater().inflate(R.layout.select_booth_layout,null);
            }else {
                convertView = getLayoutInflater().inflate(R.layout.custom_list_layout, null);
                TextView boothName = (TextView) convertView.findViewById(R.id.customListBoothName);
                TextView boothCapacity = (TextView) convertView.findViewById(R.id.customListBoothCapacityTextView);
                TextView boothFacility = (TextView) convertView.findViewById(R.id.customListBoothFacilitiesText);
                ImageView boothImage = (ImageView) convertView.findViewById(R.id.customListBoothImage);
                boothName.setText(boothNames[position]);
                GetNoOfPeople getNoOfPeople = new GetNoOfPeople(getApplicationContext(),boothCapacity);
                getNoOfPeople.execute(boothNames[position]);
                //TODO //retreve capacity data and inflate in boothCapacity var
                //TODO //retreve facility data and inflate in boothFacility var
                //TODO //retreve facility data and inflate in boothImage Click listener
                boothImage.setImageResource(boothImages[position]);
//            boothImage.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent=null, chooser=null;
//                    intent.setData(Uri.parse(boothCoordingate[position]));
//                    chooser = Intent.createChooser(intent,"Launch Maps");
//                    startActivity(chooser);
//                }
//            });
            }

            return convertView;
        }

        @Override
        public int getViewTypeCount() {

            return getCount();
        }

        @Override
        public int getItemViewType(int position) {

            return position;
        }


    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(BoothListActivity.this, Main2Activity.class));
        finish();

    }
}
