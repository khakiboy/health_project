package com.example.pro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class ShowPrescribtionsActivity extends AppCompatActivity {
    public static String username;
    public static String usertype;
    List<Prescription> prescriptionList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_prescribtions);
        RecyclerView recyclerView=findViewById(R.id.prescriptionsRecyclerView);
        SqliteDatabase sqliteDatabase=new SqliteDatabase(getApplicationContext());
        Intent intent=getIntent();
        ShowPrescribtionsActivity.username=intent.getStringExtra("username");
        ShowPrescribtionsActivity.usertype=intent.getStringExtra("usertype");
        if(ShowPrescribtionsActivity.usertype.equals("patient")){
            prescriptionList=sqliteDatabase.getAllPrescription(ShowPrescribtionsActivity.username);
        }else if(ShowPrescribtionsActivity.usertype.equals("pharmacy")){
            prescriptionList=sqliteDatabase.getRecievedPrescriptions(ShowPrescribtionsActivity.username);
        }else{
            prescriptionList=sqliteDatabase.getAllPrescriptionsInsurance(ShowPrescribtionsActivity.username);
        }
        PrescriptionsAdapter prescriptionsAdapter=new PrescriptionsAdapter(prescriptionList,this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(prescriptionsAdapter);
    }
}