package com.example.pro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

public class ShowPatientsActivity extends AppCompatActivity {
    public static String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_patients);
        RecyclerView recyclerView=findViewById(R.id.patientsRecyclerView);
        SqliteDatabase sqliteDatabase=new SqliteDatabase(getApplicationContext());
        Intent intent=getIntent();
        ShowPatientsActivity.username=intent.getStringExtra("username");
        List<Patient> patientlist=sqliteDatabase.getPatientDoctor(ShowPatientsActivity.username);
        PatientsAdapter patientsAdapter=new PatientsAdapter(patientlist,this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(patientsAdapter);
    }
}