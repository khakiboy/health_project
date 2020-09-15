package com.example.pro;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import ir.hamsaa.persiandatepicker.util.PersianCalendar;
import java.time.Year;
import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChoicedMedicineAdapter extends RecyclerView.Adapter<ChoicedMedicineAdapter.MyTaskViewHolder> {
    SqliteDatabase database;
    List<String> choicedList;
    AddPrescriptionActivity addPrescriptionActivity;
    public ChoicedMedicineAdapter(List<String> choicedList, AddPrescriptionActivity addPrescriptionActivity){
        this.choicedList= choicedList;
        this.addPrescriptionActivity=addPrescriptionActivity;
        this.database=new SqliteDatabase(MainActivity.context);
    }

    @NonNull
    @Override
    public ChoicedMedicineAdapter.MyTaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_choiced_medicine,viewGroup,false);
        MyTaskViewHolder tvh=new MyTaskViewHolder(v);
        return tvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyTaskViewHolder holder, final int position) {
        holder.medicineName.setText(choicedList.get(position));
        holder.removeChoicedMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choicedList.remove(position);
                notifyDataSetChanged();
            }
        });
    }
    @Override
    public int getItemCount() {
        return choicedList.size();
    }

    public static class MyTaskViewHolder extends RecyclerView.ViewHolder{
        LinearLayout choicedMedicineCard;
        TextView medicineName;
        Button removeChoicedMedicine;
        public MyTaskViewHolder(@NonNull View itemView) {
            super(itemView);
            choicedMedicineCard=itemView.findViewById(R.id.choiced_medicine_card);
            medicineName=itemView.findViewById(R.id.choicedMedicineName);
            removeChoicedMedicine=itemView.findViewById(R.id.removeChoicedMedicine);
        }
    }

}
