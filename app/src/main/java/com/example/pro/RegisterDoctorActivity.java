package com.example.pro;

import androidx.appcompat.app.AppCompatActivity;
import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.SortedMap;

public class RegisterDoctorActivity extends AppCompatActivity  implements
        AdapterView.OnItemSelectedListener{
    String[] genders={"جنسیت","مرد","زن"};
    String gender;
    String birthdatee;
    int sTHourhozori=0,sTminutehozori=0,eThourhozori=0,eTminutehozori=0;
    int sTHourgheirhozori=0,sTminutegheirhozori=0,eThourgheirhozori=0,eTminutegheirhozori=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_doctor);
        final Context context=getApplicationContext();
        final CheckBox agreement=findViewById(R.id.privacyagreementDr);
        final Button birthdate=(Button) findViewById(R.id.inputDrBirthDate);
        Button register=findViewById(R.id.registerDr);
        final EditText firstName=findViewById(R.id.newDrFirstName);
        final EditText lastName=findViewById(R.id.newDrLastName);
        Spinner spingender = (Spinner) findViewById(R.id.inputDrGender);
        spingender.setOnItemSelectedListener(this);
        ArrayAdapter spingenderadapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,genders);
        spingenderadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spingender.setAdapter(spingenderadapter);
        final EditText email=findViewById(R.id.newDrEmail);
        final EditText phonenumber=findViewById(R.id.newDrPhoneNumber);
        final EditText address=findViewById(R.id.newDrAddress);
        final EditText username=findViewById(R.id.newDrUsername);
        final EditText takhasos=findViewById(R.id.newDrTakhasos);
        final EditText password=findViewById(R.id.newDrPassword);
        final EditText confirmPassword=findViewById(R.id.newDrConfirmPassword);
        final EditText officeaddress=findViewById(R.id.newDrOfficeAddress);
        final EditText officephonenumber=findViewById(R.id.newDrOfficePhoneNumber);
        final EditText timepervisithozoori=findViewById(R.id.timePerVisitHozoori);
        final EditText timepervisitgheirhozoori=findViewById(R.id.timePerVisitGheirHozoori);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(password.getText().toString().equals(confirmPassword.getText().toString())){
                    if (agreement.isChecked()){
                        Doctor dr=new Doctor();
                        dr.firstName=firstName.getText().toString();
                        dr.lastName=lastName.getText().toString();
                        dr.birthDate=birthdatee;
                        dr.emailAdress=email.getText().toString();
                        dr.address=address.getText().toString();
                        dr.username=username.getText().toString();
                        dr.phoneNumber=phonenumber.getText().toString();
                        dr.password=password.getText().toString();
                        dr.registerDate= String.valueOf(Calendar.getInstance().getTimeInMillis());
                        dr.gender=gender;
                        dr.officeAddress=officeaddress.getText().toString();
                        dr.takhasos=takhasos.getText().toString();
                        dr.officePhoneNumber=officephonenumber.getText().toString();
                        SqliteDatabase database=new SqliteDatabase(getApplicationContext());
                        database.addDoctor(dr);
                        makeFreeTimes(dr.username,sTHourhozori,sTminutehozori,eThourhozori,eTminutehozori,Integer.parseInt(timepervisithozoori.getText().toString()),"حضوری");
                        makeFreeTimes(dr.username,sTHourgheirhozori,sTminutegheirhozori,eThourgheirhozori,eTminutegheirhozori,Integer.parseInt(timepervisitgheirhozoori.getText().toString()),"غیرحضوری");
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spin = (Spinner)adapterView;

        if(spin.getId() == R.id.inputDrGender)
        {
            gender=genders[i];
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void setStartTimeHozoori(View view) {
        final Button button=(Button) view;
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        button.setText(String.format("%02d",hourOfDay)+ ":" + String.format("%02d",minute));
                        sTHourhozori=hourOfDay;
                        sTminutehozori=minute;
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }
    public void setEndTimeHozoori(View view) {
        final Button button=(Button) view;
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        button.setText(String.format("%02d",hourOfDay)+ ":" + String.format("%02d",minute));
                        eThourhozori=hourOfDay;
                        eTminutehozori=minute;
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }
    public void setStartTimeGheirHozoori(View view) {
        final Button button=(Button) view;
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        button.setText(String.format("%02d",hourOfDay)+ ":" + String.format("%02d",minute));
                        sTHourgheirhozori=hourOfDay;
                        sTminutegheirhozori=minute;
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }
    public void setEndTimeGheirHozoori(View view) {
        final Button button=(Button) view;
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        button.setText(String.format("%02d",hourOfDay)+ ":" + String.format("%02d",minute));
                        eThourgheirhozori=hourOfDay;
                        eTminutegheirhozori=minute;
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }
    public void makeFreeTimes(String doctorId,int startHour,int startMinute,int endHour,int endMinute,int timepervisit,String type){
        int numberOfVisits=((endHour-startHour)*60 + (endMinute-startMinute))/timepervisit;
        int startTime=startHour*60 + startMinute;
        int thistime=startTime;
        int visitStartHour,visitStartMinute,visitEndHour,visitEndMinute;
        String freetime;
        SqliteDatabase sqliteDatabase=new SqliteDatabase(getApplicationContext());
        for(int i=0;i<numberOfVisits;i++){
            visitStartHour=thistime/60;
            visitStartMinute=thistime%60;
            visitEndHour=(thistime+timepervisit)/60;
            visitEndMinute=(thistime+timepervisit)%60;
            freetime=visitStartHour+"";
            if(visitStartMinute!=0){
                freetime+=":"+visitStartMinute;
            }
            freetime+=" تا "+visitEndHour;
            if(visitEndMinute!=0){
                freetime+=":"+visitEndMinute;
            }
            sqliteDatabase.addDoctorFreeTime(doctorId,freetime,type,"free");
            thistime+=timepervisit;
        }
    }



}