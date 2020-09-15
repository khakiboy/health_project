package com.example.pro;

import androidx.appcompat.app.AppCompatActivity;
import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RegesterPatientActivity extends AppCompatActivity  implements
        AdapterView.OnItemSelectedListener{
    String TAG = "HEHEHEHEHEHE";
    ArrayList<String> ins;
    String[] insurences;
    String[] genders={"جنسیت","مرد","زن"};
    String bime;
    String gender;
    String birthdatee;
    SqliteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regester_patient);
        database=new SqliteDatabase(getApplicationContext());
        List<Insurance> insuranceList=database.getAllInsurances();
        ins=new ArrayList<>();
        ins.add("بیمه ندارم");
        for(Insurance insurance: insuranceList){ins.add(insurance.name);}
        insurences= GetStringArray(ins);
        final Context context=getApplicationContext();
        final CheckBox agreement=findViewById(R.id.privacyagreement);
        final Button birthdate=(Button) findViewById(R.id.inputpatientBirthDate);
        Button register=findViewById(R.id.register);
        final EditText firstName=findViewById(R.id.newPatientFirstName);
        final EditText lastName=findViewById(R.id.newpatientLastName);

        Spinner spingender = (Spinner) findViewById(R.id.inputpatientGender);
        spingender.setOnItemSelectedListener(this);
        ArrayAdapter<String> spingenderadapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,genders);
        spingenderadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spingender.setAdapter(spingenderadapter);

        Spinner spin = (Spinner) findViewById(R.id.spinnerinsurence);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,insurences);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        final EditText email=findViewById(R.id.newpatientEmail);
        final EditText phonenumber=findViewById(R.id.newpatientPhoneNumber);
        final EditText address=findViewById(R.id.newpatientAddress);
        final EditText username=findViewById(R.id.newpatientUsername);
        final EditText password=findViewById(R.id.newpatientPassword);
        final EditText confirmPassword=findViewById(R.id.newpatientConfirmPassword);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(password.getText().toString().equals(confirmPassword.getText().toString())){
                    if (agreement.isChecked()){
                        Patient patient=new Patient();
                        patient.firstName=firstName.getText().toString();
                        patient.lastName=lastName.getText().toString();
                        patient.birthDate=birthdatee;
                        patient.emailAdress=email.getText().toString();
                        patient.address=address.getText().toString();
                        patient.username=username.getText().toString();
                        patient.phoneNumber=phonenumber.getText().toString();
                        patient.password=password.getText().toString();
                        patient.registerDate= String.valueOf(Calendar.getInstance().getTimeInMillis());
                        patient.insurance=bime;
                        patient.gender=gender;
                        database.addPatient(patient);
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
        final PersianDatePickerDialog picker = new PersianDatePickerDialog(this)
                .setPositiveButtonString("ثبت")
                .setNegativeButton("لغو")
                .setTodayButton("امروز")
                .setTodayButtonVisible(true)
                .setMinYear(1300)
                .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
                .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
                .setShowInBottomSheet(true)
                .setListener(new Listener() {
                    @Override
                    public void onDateSelected(PersianCalendar persianCalendar) {
                                /*
                                Log.d(TAG, "onDateSelected: "+persianCalendar.getGregorianChange());//Fri Oct 15 03:25:44 GMT+04:30 1582
                                Log.d(TAG, "onDateSelected: "+persianCalendar.getTimeInMillis());//1583253636577
                                Log.d(TAG, "onDateSelected: "+persianCalendar.getTime());//Tue Mar 03 20:10:36 GMT+03:30 2020
                                Log.d(TAG, "onDateSelected: "+persianCalendar.getDelimiter());//  /
                                Log.d(TAG, "onDateSelected: "+persianCalendar.getPersianLongDate());// سه‌شنبه  13  اسفند  1398
                                Log.d(TAG, "onDateSelected: "+persianCalendar.getPersianLongDateAndTime()); //سه‌شنبه  13  اسفند  1398 ساعت 20:10:36
                                Log.d(TAG, "onDateSelected: "+persianCalendar.getPersianMonthName()); //اسفند
                                Log.d(TAG, "onDateSelected: "+persianCalendar.isPersianLeapYear());//false
                                 */
                        birthdate.setText(persianCalendar.getPersianLongDate());
                        birthdatee=String.valueOf(persianCalendar.getTimeInMillis());
                    }

                    @Override
                    public void onDismissed() {

                    }
                });
        birthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picker.show();
            }
        });

    }
    public static String[] GetStringArray(ArrayList<String> arr)
    {

        // declaration and initialise String Array
        String str[] = new String[arr.size()];

        // ArrayList to Array Conversion
        for (int j = 0; j < arr.size(); j++) {

            // Assign each value to String array
            str[j] = arr.get(j);
        }

        return str;
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spin = (Spinner)adapterView;

        if(spin.getId() == R.id.inputpatientGender)
        {
            gender=genders[i];
            System.out.println("gender entered");
        }
        if(spin.getId() == R.id.spinnerinsurence)
        {
            bime=insurences[i];
            System.out.println("bime entered");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}