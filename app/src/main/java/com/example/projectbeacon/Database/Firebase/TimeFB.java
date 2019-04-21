package com.example.projectbeacon.Database.Firebase;

import com.example.projectbeacon.Model.Time;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeFB {
    FirebaseFirestore db;
    private String TAG = "TimeFB";

    private void initFirestore() {
        db = FirebaseFirestore.getInstance();
    }

    public TimeFB(){
        initFirestore();
    }

    public void uploadTime(){
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();

        Date date1 = new Date();
        Date date2 = new Date();
        int front[] = {5,6,9,12,13,16,18,20,21};
        int back[] = {6,9,12,13,16,18,20,21,5};

        for(int i=0; i<front.length; i++){
            if(i==10){

            } else {
                date1.setHours(front[i]);
                date1.setMinutes(1);
//                calendar1.setTime(date1);
//                calendar1.set(Calendar.HOUR_OF_DAY, front[i]);
//                calendar1.set(Calendar.MINUTE, 1);
//                calendar1.set(Calendar.SECOND, 0);
//                calendar1.set(Calendar.MILLISECOND, 0);

                date2.setHours(back[i]);
                date2.setMinutes(1);
//                calendar2.setTime(date2);
//                calendar2.set(Calendar.HOUR_OF_DAY, back[i]);
//                calendar2.set(Calendar.MINUTE, 1);
//                calendar2.set(Calendar.SECOND, 0);
//                calendar2.set(Calendar.MILLISECOND, 0);

                SimpleDateFormat formatter = new SimpleDateFormat(
                        "HH:mm");
                String str1 = formatter.format(date1);
                String str2 = formatter.format(date2);

                Time t2 = new Time("T"+i,""+str1,""+str2);
                db.collection("times").document(t2.getTime_id()).set(t2);

            }

            Time t = new Time("T9","25:00","25:00");
            db.collection("times").document(t.getTime_id()).set(t);

        }
//        return new Date(calendar.getTime().getTime());
    }
}
