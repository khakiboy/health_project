package com.example.pro;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;

public class PrescriptionsAdapter extends RecyclerView.Adapter<PrescriptionsAdapter.MyTaskViewHolder> {
    SqliteDatabase database;
    List<Prescription> prescriptions;
    ShowPrescribtionsActivity showPrescribtionsActivity;
     public PrescriptionsAdapter(List<Prescription> prescriptionslist,ShowPrescribtionsActivity showPrescribtionsActivity){
         this.prescriptions = prescriptionslist;
         this.showPrescribtionsActivity=showPrescribtionsActivity;
         this.database=new SqliteDatabase(MainActivity.context);
     }

    @NonNull
    @Override
    public PrescriptionsAdapter.MyTaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
         View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_prescription,viewGroup,false);
         MyTaskViewHolder tvh=new MyTaskViewHolder(v);
        return tvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyTaskViewHolder holder, final int position) {
        final Doctor doctor=database.findDoctor(prescriptions.get(position).doctor);
        final Patient patient=database.findPatient(prescriptions.get(position).patient);
         holder.doctorName.setText(doctor.firstName+" "+doctor.lastName+"("+doctor.takhasos+")-"+patient.firstName+" "+patient.lastName);
        PersianCalendar calendar=new PersianCalendar();
        calendar.setTimeInMillis(Long.parseLong(prescriptions.get(position).date));
        holder.prescriptionDate.setText(calendar.getPersianShortDate());
        holder.sicknessname.setText(prescriptions.get(position).sickness);
        holder.prescriptionCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(showPrescribtionsActivity,ShowPrescriptionDetailActivity.class);
                intent.putExtra("uniqueId",prescriptions.get(position).uniqueId);
                intent.putExtra("userType",ShowPrescribtionsActivity.usertype);
                intent.putExtra("patientUsername",prescriptions.get(position).patient);
                showPrescribtionsActivity.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return prescriptions.size();
    }

    public static class MyTaskViewHolder extends RecyclerView.ViewHolder{
         LinearLayout prescriptionCard;
         TextView doctorName;
        TextView prescriptionDate;
        TextView sicknessname;
         public MyTaskViewHolder(@NonNull View itemView) {
             super(itemView);
             prescriptionCard=itemView.findViewById(R.id.prescriptions_card);
             doctorName=itemView.findViewById(R.id.prescriptionsDoctorName);
             sicknessname=itemView.findViewById(R.id.sicknessNamee);
             prescriptionDate=itemView.findViewById(R.id.prescriptionsDate);
         }
    }

}
