package com.example.pro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ShowListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);
        List<String> data=new ArrayList<>();
        Intent intent=getIntent();
        String user=intent.getStringExtra("username");
        String pageTitle=intent.getStringExtra("pageTitle");
        SqliteDatabase sqliteDatabase=new SqliteDatabase(getApplicationContext());
        TextView pagetitle=findViewById(R.id.pageTitle);
        pagetitle.setText(pageTitle);
        if(pageTitle.equals("سوابق بیماری")){
            data=sqliteDatabase.getPatientSickness(user);
        }
        if(pageTitle.equals("پزشک های من")){
            List<Doctor> doctorList=sqliteDatabase.getDoctorPatient(user);
            for(Doctor doctor:doctorList){
                data.add(doctor.firstName+" "+doctor.lastName+"("+doctor.takhasos+")");
            }
        }
        if(pageTitle.equals("دارو های مصرف شده")){
            data=sqliteDatabase.getPatientMedicine(user);
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.card_list,data);
        ListView listView = (ListView) findViewById(R.id.showList);
        listView.setAdapter(adapter);
    }
}