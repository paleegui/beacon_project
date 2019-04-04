package com.example.projectbeacon.UI;

import android.app.AlertDialog;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.projectbeacon.Activities.AddBeacon;
import com.example.projectbeacon.Activities.Beacon_info;
import com.example.projectbeacon.Beacon.TheBeacon;
import com.example.projectbeacon.R;

import org.altbeacon.beacon.Beacon;

import java.util.ArrayList;


public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<TheBeacon> beacons;
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog dialog;
    private LayoutInflater inflater;

    public RecycleViewAdapter(Context context, ArrayList<TheBeacon> beacons) {
        this.context = context;
        this.beacons = beacons;
    }

    @NonNull
    @Override
    public RecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.beacon, viewGroup, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapter.ViewHolder viewHolder, int i) {
        TheBeacon beacon = this.beacons.get(i);
        int number = i+1;
        viewHolder.room_name.setText(beacon.getRoomName());
        viewHolder.beacon_name.setText("Beacon "+number);
        viewHolder.ranging.setText("ระยะทาง: "+String.format("%.2f",beacon.getBeacon().getDistance()) +" เมตร");
    }

    @Override
    public int getItemCount() {
        return beacons.size();
    }

    public interface OnItemChange {
        void notifyAdapter(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
//        public int id
        private TextView beacon_name;
        private TextView room_name;
        private TextView ranging;


        public ViewHolder(@NonNull View itemView, final Context ctx) {
            super(itemView);
            context = ctx;
            room_name = (TextView)itemView.findViewById(R.id.roomNameID);
            beacon_name = (TextView)itemView.findViewById(R.id.beaconNameID);
            ranging = (TextView)itemView.findViewById(R.id.rangingID);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //go to next screen/ DetailActivity
                    int position = getAdapterPosition();
                    TheBeacon beacon = beacons.get(position);
                    Intent intent = new Intent(context, Beacon_info.class);
                    intent.putExtra("beacon", beacon);
                    intent.putExtra("id",beacon.getId());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }

    }


}