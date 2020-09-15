package com.example.pro;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterInsuranceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_insurance);
        final Context context=getApplicationContext();
        final CheckBox agreement=findViewById(R.id.insuranceprivacyagreement);
        Button register=findViewById(R.id.registerinsurance);
        final EditText firstName=findViewById(R.id.newinsuranceFirstName);
        final EditText email=findViewById(R.id.newinsuranceEmail);
        final EditText phonenumber=findViewById(R.id.newinsurancePhoneNumber);
        final EditText address=findViewById(R.id.newinsuranceAddress);
        final EditText username=findViewById(R.id.newinsuranceUsername);
        final EditText password=findViewById(R.id.newinsurancePassword);
        final EditText confirmPassword=findViewById(R.id.newinsuranceConfirmPassword);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(password.getText().toString().equals(confirmPassword.getText().toString())){
                    if (agreement.isChecked()){
                        Insurance insurance=new Insurance();
                        insurance.name=firstName.getText().toString();
                        insurance.emailAddress=email.getText().toString();
                        insurance.address=address.getText().toString();
                        insurance.username=username.getText().toString();
                        insurance.phoneNumber=phonenumber.getText().toString();
                        insurance.registerDate= String.valueOf(Calendar.getInstance().getTimeInMillis());
                        insurance.password=password.getText().toString();
                        SqliteDatabase database=new SqliteDatabase(getApplicationContext());
                        database.addInsurance(insurance);
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