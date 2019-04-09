package com.myapp.electionapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CustomDialogue extends DialogFragment {
    private TextView name,capacity,facility;
    private Button location;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_dialogue_layout,null);
        name = (TextView) view.findViewById(R.id.customDialogueBoothName);
        capacity = (TextView)view.findViewById(R.id.customDialogueBoothCapacity);
        facility = (TextView)view.findViewById(R.id.customDialogueBoothFacility);
        location = (Button) view.findViewById(R.id.customDialogueBoothLocation);

        Bundle bundle = getArguments();
        String nameString = bundle.getString("Name");
        final String locationString = bundle.getString("Location");

        name.setText(nameString);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapIntent = new Intent(Intent.ACTION_VIEW);
                mapIntent.setData(Uri.parse(locationString));
                Intent mapChooser = Intent.createChooser(mapIntent,"Launch Maps");
                startActivity(mapChooser);
            }
        });
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(view);
        return builder.create();
    }


}
