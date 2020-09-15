package com.example.pro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PatientMainActivity extends AppCompatActivity {
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_main);
        Intent intent=getIntent();
        user=intent.getStringExtra("username");
    }
    public void nobatPezeshk(View view) {
        Intent intent=new Intent(PatientMainActivity.this,VisitRequestActivity.class);
        intent.putExtra("username",user);
        startActivity(intent);
    }
    public void logout(View view) {
        Intent mStartActivity = new Intent(PatientMainActivity.this, MainActivity.class);
        startActivity(mStartActivity);
        System.exit(0);
    }

    public void clickMyPrescriptions(View view) {
        Intent intent=new Intent(PatientMainActivity.this,ShowPrescribtionsActivity.class);
        intent.putExtra("username",user);
        intent.putExtra("usertype","patient");
        startActivity(intent);
    }

    public void onclickButtons(View view) {
        Button button=(Button) view;
        String buttonClicked=button.getText().toString();
        Intent intent=new Intent(PatientMainActivity.this,ShowListActivity.class);
        intent.putExtra("pageTitle",buttonClicked);
        intent.putExtra("username",user);
        startActivity(intent);
    }
}