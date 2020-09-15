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

public class PatientsAdapter extends RecyclerView.Adapter<PatientsAdapter.MyTaskViewHolder> {
    SqliteDatabase database;
    List<Patient> patients;
    ShowPatientsActivity showPatientsActivity;
     public PatientsAdapter(List<Patient> patientList, ShowPatientsActivity showPatientsActivity){
         this.patients = patientList;
         this.showPatientsActivity=showPatientsActivity;
         this.database=new SqliteDatabase(MainActivity.context);
     }

    @NonNull
    @Override
    public PatientsAdapter.MyTaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
         View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_patients,viewGroup,false);
         MyTaskViewHolder tvh=new MyTaskViewHolder(v);
        return tvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyTaskViewHolder holder, final int position) {
         final Patient patient=database.findPatient(patients.get(position).username);
         holder.patinetName.setText(patient.firstName+" "+patient.lastName);
        holder.patientsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(showPatientsActivity,AddPrescriptionActivity.class);
                intent.putExtra("patient",patient.username);
                intent.putExtra("doctor",ShowPatientsActivity.username);
                showPatientsActivity.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return patients.size();
    }

    public static class MyTaskViewHolder extends RecyclerView.ViewHolder{
         LinearLayout patientsCard;
         TextView patinetName;
         public MyTaskViewHolder(@NonNull View itemView) {
             super(itemView);
             patientsCard=itemView.findViewById(R.id.patients_card);
             patinetName=itemView.findViewById(R.id.patientFullName);
         }
    }

}
