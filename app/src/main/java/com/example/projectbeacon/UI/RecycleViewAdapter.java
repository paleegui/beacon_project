package com.example.projectbeacon.UI;

import android.app.AlertDialog;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


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
        Beacon beacon = this.beacons.get(i).getBeacon();
        viewHolder.room_name.setText("ห้องนอน");
        viewHolder.beacon_name.setText("Beacon "+i);

        viewHolder.ranging.setText("ระยะทาง: "+String.format("%.2f",beacon.getDistance()) +" เมตร");

    }

    @Override
    public int getItemCount() {
        return beacons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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

//
//            editeButton = (Button) itemView.findViewById(R.id.editeButton);
//            deleteButton = (Button) itemView.findViewById(R.id.deleteButton);
//
//            editeButton.setOnClickListener(this);
//            deleteButton.setOnClickListener(this);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //go to next screen/ DetailActivity
                    int position = getAdapterPosition();
//
//                    Grocery grocery = groceryItem.get(position);
//                    Intent intent = new Intent(context, DetailActivity.class);
//                    intent.putExtra("name", grocery.getName());
//                    intent.putExtra("quantity", grocery.getQuantity());
//                    intent.putExtra("dateAdded", grocery.getDateItemAdded());
//                    intent.putExtra("id", grocery.getId());
//                    context.startActivity(intent);

                }
            });
        }
        public  void byPassActivity(){
//            DatabaseHandler db = new DatabaseHandler(context);
//            if(db.getGroceriesCount() <= 0){
//                context.startActivity(new Intent(context, MainActivity.class));
//            }
        }
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
//                case R.id.editeButton:
//                    editeItem(groceryItem.get(getAdapterPosition()));
//                    break;
//                case R.id.deleteButton:
//                    deleteItem(groceryItem.get(getAdapterPosition()).getId());
//                    break;
//            }
            }

//        public void deleteItem(final int id) {
//            //create an AlertDiaglog
//            alertDialogBuilder = new AlertDialog.Builder(context);
//
//            inflater = LayoutInflater.from(context);
//            View view = inflater.inflate(R.layout.confirmation_dialog, null);
//            Button onButton = (Button) view.findViewById(R.id.noButton);
//            Button yesButton = (Button) view.findViewById(R.id.yesButton);
//            alertDialogBuilder.setView(view);
//            dialog = alertDialogBuilder.create();
//            dialog.show();
//
//            onButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    dialog.dismiss();
//                }
//            });
//
//            yesButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    //delete the item.
//                    DatabaseHandler db = new DatabaseHandler(context);
//                    //delete item
//                    db.deleteGrocery(id);
//                    groceryItem.remove(getAdapterPosition());
//                    notifyItemRemoved(getAdapterPosition());
//
//                    dialog.dismiss();
//                }
//            });
//        }
//
//        public void editeItem(final Grocery grocery) {
//            alertDialogBuilder = new AlertDialog.Builder(context);
//            inflater = LayoutInflater.from(context);
//            View view = inflater.inflate(R.layout.popup, null);
//
//            final EditText groceryItem = (EditText) view.findViewById(R.id.groceryItem);
//            final EditText quantity = (EditText) view.findViewById(R.id.groceryQty);
//            final Button saveButton = (Button) view.findViewById(R.id.saveButton);
//            final TextView title = (TextView) view.findViewById(R.id.title);
//            title.setText("Edit Grocery");
//
//            alertDialogBuilder.setView(view);
//            dialog = alertDialogBuilder.create();
//            dialog.show();
//
//            saveButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    DatabaseHandler db = new DatabaseHandler(context);
//
//                    //update item
//                    grocery.setName(groceryItem.getText().toString());
//                    grocery.setQuantity(groceryItem.getText().toString());
//
//                    if (!groceryItem.getText().toString().isEmpty()
//                            && !groceryItem.getText().toString().isEmpty()) {
//                        db.updateGrocery(grocery);
//                        notifyItemChanged(getAdapterPosition(), grocery);
//                    } else {
//                        Snackbar.make(view, "Add Grocery and Quantity", Snackbar.LENGTH_LONG).show();
//                    }
//                    dialog.dismiss();
//                }
//            });
        }

    }
}