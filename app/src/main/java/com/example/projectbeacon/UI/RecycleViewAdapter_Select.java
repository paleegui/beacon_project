package com.example.projectbeacon.UI;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projectbeacon.Activities.AddBeacon;
import com.example.projectbeacon.Beacon.TheBeacon;
import com.example.projectbeacon.R;

import org.altbeacon.beacon.Beacon;

import java.io.Serializable;
import java.util.ArrayList;

public class RecycleViewAdapter_Select extends RecyclerView.Adapter<RecycleViewAdapter_Select.ViewHolder> implements Serializable{
    private Context context;
    private ArrayList<Beacon> beacons;

    public RecycleViewAdapter_Select(Context context,ArrayList<Beacon> beacons){
        this.context = context;
        this.beacons = beacons;

    }

    @NonNull
    @Override
    public RecycleViewAdapter_Select.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.beacon_card, viewGroup, false);
        return new RecycleViewAdapter_Select.ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapter_Select.ViewHolder viewHolder, int i) {
        Beacon beacon = this.beacons.get(i);

        viewHolder.beacon_name.setText(""+beacon.getBluetoothName());
        viewHolder.UUID.setText(""+beacon.getId1());
        viewHolder.major.setText("Major "+beacon.getId2());
        viewHolder.minor.setText("Minor "+beacon.getId3());
        viewHolder.RSSI.setText("RSSI "+beacon.getRssi()+" dBm");
        viewHolder.TX.setText("Tx "+beacon.getTxPower()+" dBm");
        viewHolder.ranging.setText(String.format("%.2f",beacon.getDistance()) +" m");
    }

    @Override
    public int getItemCount() {
        return this.beacons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView beacon_name;
        private TextView UUID;
        private TextView major;
        private TextView minor;
        private TextView RSSI;
        private TextView TX;
        private TextView ranging;


        public ViewHolder(@NonNull View itemView,final Context cnt) {
            super(itemView);

            context = cnt;

            UUID = (TextView)itemView.findViewById(R.id.UUID_selectText);
            major = (TextView)itemView.findViewById(R.id.major_selectText);
            minor = (TextView)itemView.findViewById(R.id.minor_selectText);
            RSSI = (TextView)itemView.findViewById(R.id.RSSI_selectText);
            TX =(TextView)itemView.findViewById(R.id.TX_selectText);
            beacon_name = (TextView)itemView.findViewById(R.id.becon_select_name);
            ranging = (TextView)itemView.findViewById(R.id.ranging_select);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Beacon beacon = beacons.get(position);
                    TheBeacon b = new TheBeacon();
                    b.setBeacon(beacon);
                    Intent intent = new Intent(context, AddBeacon.class);
                    intent.putExtra("beacon", b);
                    intent.putExtra("Flag","addBeacon");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });

        }
    }
}
