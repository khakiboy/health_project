package com.example.pro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DoctorMainActivity extends AppCompatActivity {
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main);
        Intent intent=getIntent();
        username=intent.getStringExtra("username");
    }

    public void showVisitrequstsbutton(View view) {
        Intent intent=new Intent(DoctorMainActivity.this,ShowVisitRequestsActivity.class);
        intent.putExtra("username",username);
        startActivity(intent);
    }

    public void logout(View view) {
        Intent mStartActivity = new Intent(DoctorMainActivity.this, MainActivity.class);
        startActivity(mStartActivity);
        System.exit(0);
    }

    public void showMyPatients(View view) {
        Intent intent=new Intent(DoctorMainActivity.this,ShowPatientsActivity.class);
        intent.putExtra("username",username);
        startActivity(intent);
    }
}