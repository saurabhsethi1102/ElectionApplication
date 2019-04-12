package com.myapp.electionapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CandidateAdapter extends RecyclerView.Adapter<CandidateAdapter.CandidateViewHolder> {

    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private ArrayList<Object> candidateList;

    //getting the context and product list with constructor
    public CandidateAdapter(Context mCtx, ArrayList<Object> candidateList) {
        this.mCtx = mCtx;
        this.candidateList = candidateList;
    }

    @Override
    public CandidateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.candidate_card, null);
        return new CandidateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CandidateViewHolder holder, int position) {
        //getting the product of the specified position
        Candidates product = (Candidates) candidateList.get(position);


        holder.name.setText(product.getName());
        holder.party.setText(product.getParty());


        holder.candidateImage.setImageDrawable(mCtx.getResources().getDrawable(product.getCandidateImage()));
        holder.symbol.setImageDrawable(mCtx.getResources().getDrawable(product.getSymbol()));


    }


    @Override
    public int getItemCount() {
        return candidateList.size();
    }


    class CandidateViewHolder extends RecyclerView.ViewHolder {

        TextView name, party, affidavit;
        ImageView candidateImage,symbol;

        public CandidateViewHolder(View itemView) {
            super(itemView);

            final Intent[] i1 = new Intent[1];
            name =itemView.findViewById(R.id.name);
            party = itemView.findViewById(R.id.partyName);
            affidavit = itemView.findViewById(R.id.affidavitLink);
            candidateImage = itemView.findViewById(R.id.candidate);
            symbol = itemView.findViewById(R.id.symbol);

             final Context context=itemView.getContext();

        }
    }
}



