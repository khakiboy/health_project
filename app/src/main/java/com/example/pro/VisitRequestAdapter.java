package com.example.pro;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import ir.hamsaa.persiandatepicker.util.PersianCalendar;
import java.time.Year;
import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VisitRequestAdapter extends RecyclerView.Adapter<VisitRequestAdapter.MyTaskViewHolder> {
    SqliteDatabase database;
    List<VisitRequest> visitrequestList;
    ShowVisitRequestsActivity showVisitRequestsActivity;
     public VisitRequestAdapter(List<VisitRequest> myVisitRequests, ShowVisitRequestsActivity showVisitRequestsActivity){
         this.visitrequestList = myVisitRequests;
         this.showVisitRequestsActivity=showVisitRequestsActivity;
         this.database=new SqliteDatabase(MainActivity.context);
     }

    @NonNull
    @Override
    public VisitRequestAdapter.MyTaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
         View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_visit_request,viewGroup,false);
         MyTaskViewHolder tvh=new MyTaskViewHolder(v);
        return tvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyTaskViewHolder holder, final int position) {
         final Patient patient=database.findPatient(visitrequestList.get(position).patient);
         holder.patientname.setText(patient.firstName+" "+patient.lastName);
        PersianCalendar calendar=new PersianCalendar();
        calendar.setTimeInMillis(Long.parseLong(visitrequestList.get(position).date));
        holder.visitrequsetDate.setText(calendar.getPersianShortDate());
        holder.visitrequestTime.setText(visitrequestList.get(position).time);
        holder.visitRequestCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(showVisitRequestsActivity,AddPrescriptionActivity.class);
                intent.putExtra("patient",patient.username);
                intent.putExtra("doctor",ShowVisitRequestsActivity.username);
                showVisitRequestsActivity.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return visitrequestList.size();
    }

    public static class MyTaskViewHolder extends RecyclerView.ViewHolder{
         LinearLayout visitRequestCard;
         TextView patientname;
         TextView visitrequsetDate;
         TextView visitrequestTime;
         public MyTaskViewHolder(@NonNull View itemView) {
             super(itemView);
             visitRequestCard=itemView.findViewById(R.id.visit_request_card);
             patientname=itemView.findViewById(R.id.visitrequestpatientname);
             visitrequestTime=itemView.findViewById(R.id.visitrequestvisitTime);
             visitrequsetDate=itemView.findViewById(R.id.visitrequestvisitDate);
         }
    }

}
