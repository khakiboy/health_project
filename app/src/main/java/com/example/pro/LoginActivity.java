package com.example.pro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    String type;
    EditText username;
    EditText password;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context=getApplicationContext();
        Intent intent=getIntent();
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        type=intent.getStringExtra("type");
    }
    public void clicklogin(View view) {
        if (type.equals("بیمار")){
            String user=username.getText().toString();
            String pass=password.getText().toString();
            SqliteDatabase database=new SqliteDatabase(getApplicationContext());
            Patient patient=database.findPatient(user);
            if(patient==null){
                Toast.makeText(context,"شناسه عبور اشتباه است.",Toast.LENGTH_SHORT).show();
            }else{
                if (patient.password.equals(pass)){
                    Intent intent=new Intent(LoginActivity.this,PatientMainActivity.class);
                    intent.putExtra("username",user);
                    startActivity(intent);
                }else{
                    Toast.makeText(context,"رمز ورود اشتباه است.",Toast.LENGTH_SHORT).show();
                }
            }

        }else if (type.equals("پزشک")){
            String user=username.getText().toString();
            String pass=password.getText().toString();
            SqliteDatabase database=new SqliteDatabase(getApplicationContext());
            Doctor doctor=database.findDoctor(user);
            if(doctor==null){
                Toast.makeText(context,"شناسه عبور اشتباه است.",Toast.LENGTH_SHORT).show();
            }else{
                if (doctor.password.equals(pass)){
                    Intent intent=new Intent(LoginActivity.this,DoctorMainActivity.class);
                    intent.putExtra("username",user);
                    startActivity(intent);
                }else{
                    Toast.makeText(context,"رمز ورود اشتباه است.",Toast.LENGTH_SHORT).show();
                }
            }
        }else if (type.equals("داروخانه")){
            String user=username.getText().toString();
            String pass=password.getText().toString();
            SqliteDatabase database=new SqliteDatabase(getApplicationContext());
            Pharmacy pharmacy=database.findPharmecy(user);
            if(pharmacy==null){
                Toast.makeText(context,"شناسه عبور اشتباه است.",Toast.LENGTH_SHORT).show();
            }else{
                if (pharmacy.password.equals(pass)){
                    Intent intent=new Intent(LoginActivity.this,ShowPrescribtionsActivity.class);
                    intent.putExtra("username",user);
                    intent.putExtra("usertype","pharmacy");
                    startActivity(intent);
                }else{
                    Toast.makeText(context,"رمز ورود اشتباه است.",Toast.LENGTH_SHORT).show();
                }
            }
        }else if (type.equals("بیمه")){
            String user=username.getText().toString();
            String pass=password.getText().toString();
            SqliteDatabase database=new SqliteDatabase(getApplicationContext());
            Insurance insurance=database.findInsurance(user);
            if(insurance==null){
                Toast.makeText(context,"شناسه عبور اشتباه است.",Toast.LENGTH_SHORT).show();
            }else{
                if (insurance.password.equals(pass)){
                    Intent intent=new Intent(LoginActivity.this,ShowPrescribtionsActivity.class);
                    intent.putExtra("username",user);
                    intent.putExtra("usertype","insurance");
                    startActivity(intent);
                }else{
                    Toast.makeText(context,"رمز ورود اشتباه است.",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    public void clickregister(View view) {
        if (type.equals("بیمار")){
            Intent intent=new Intent(LoginActivity.this,RegesterPatientActivity.class);
            startActivity(intent);
        }else if (type.equals("پزشک")){
            Intent intent=new Intent(LoginActivity.this,RegisterDoctorActivity.class);
            startActivity(intent);
        }else if (type.equals("داروخانه")){
            Intent intent=new Intent(LoginActivity.this,RegisterPharmecyActivity.class);
            startActivity(intent);
        }else if (type.equals("بیمه")){
            Intent intent=new Intent(LoginActivity.this,RegisterInsuranceActivity.class);
            startActivity(intent);
        }
    }
}