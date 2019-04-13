package com.myapp.electionapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AllPollingStationAdapter extends RecyclerView.Adapter<AllPollingStationAdapter.PollingViewHolder> {

    private Context mCtx;

    //we are storing all the products in a list
    private List<AllPollingData> PollingList;
    private List<AllPollingData> PollingListFiltered;

    public AllPollingStationAdapter(Context mCtx, List<AllPollingData> PollingList) {
        this.mCtx = mCtx;
        this.PollingList= PollingList;
        this.PollingListFiltered=PollingList;
    }


    @Override
    public PollingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.all_polling_station_card, null);
        return new PollingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PollingViewHolder holder, int position) {
        //getting the product of the specified position
        AllPollingData product = PollingList.get(position);


        holder.boothImage.setImageBitmap(product.getImage());
        holder.boothName.setText(product.getBoothName());
        holder.pid.setText(product.getFacility());
        holder.facility.setText(product.getFacility());
        holder.no_of_people.setText(product.getNo_of_people());




    }


    @Override
    public int getItemCount() {
        return PollingList.size();
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    PollingListFiltered = PollingList;
                } else {
                    List<AllPollingData> filteredList = new ArrayList<>();
                    for (AllPollingData row : PollingList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getBoothName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    PollingListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = PollingListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                PollingListFiltered= (ArrayList<AllPollingData>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    class PollingViewHolder extends RecyclerView.ViewHolder {

        TextView boothName, pid, facility,no_of_people;
        ImageView boothImage;

        public PollingViewHolder(View itemView) {
            super(itemView);

            final Intent[] i1 = new Intent[1];
            boothImage=itemView.findViewById(R.id.boothImage);
            boothName =itemView.findViewById(R.id.boothName);
            pid = itemView.findViewById(R.id.Polling_station_no);
            facility=itemView.findViewById(R.id.facility);
            no_of_people=(itemView).findViewById(R.id.number_of_people);
            final Context context=itemView.getContext();

                  }
    }
}
