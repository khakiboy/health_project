package com.example.pro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class RegisterPharmecyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pharmecy);
        final Context context=getApplicationContext();
        final CheckBox agreement=findViewById(R.id.pharmecyprivacyagreement);
        Button register=findViewById(R.id.registerpharmecy);
        final EditText firstName=findViewById(R.id.newpharmecyFirstName);
        final EditText email=findViewById(R.id.newpharmecyEmail);
        final EditText phonenumber=findViewById(R.id.newpharmecyPhoneNumber);
        final EditText address=findViewById(R.id.newpharmecyAddress);
        final EditText username=findViewById(R.id.newpharmecyUsername);
        final EditText password=findViewById(R.id.newpharmecyPassword);
        final EditText confirmPassword=findViewById(R.id.newpharmecyConfirmPassword);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(password.getText().toString().equals(confirmPassword.getText().toString())){
                    if (agreement.isChecked()){
                        Pharmacy pharmacy=new Pharmacy();
                        pharmacy.name=firstName.getText().toString();
                        pharmacy.emailAddress=email.getText().toString();
                        pharmacy.address=address.getText().toString();
                        pharmacy.username=username.getText().toString();
                        pharmacy.phoneNumber=phonenumber.getText().toString();
                        pharmacy.registerDate= String.valueOf(Calendar.getInstance().getTimeInMillis());
                        pharmacy.password=password.getText().toString();
                        SqliteDatabase database=new SqliteDatabase(getApplicationContext());
                        database.addPharmecy(pharmacy);
                        Toast.makeText(context,"حساب جدید با موفقیت ایجاد شد.",Toast.LENGTH_LONG).show();
                        finish();
                    }else{
                        Toast.makeText(context,"باید شرایط و ضوابط را بپذیرید",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(context,"باید رمز و تایید رمز یکسان باشد.",Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}