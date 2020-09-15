package com.example.pro;

import androidx.appcompat.app.AppCompatActivity;
import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.nio.channels.CancelledKeyException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class VisitRequestActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ArrayList<String> ins;
    String[] doctors;
    String[] requesttype={"انتخاب نوع نوبت","حضوری","غیرحضوری"};
    String[] doctorFreeTimes={"nothing"};
    String choicedDoc;
    String reqtype;
    String docfreetime;
    List<Doctor> doctorsList;
    String visitRequestDate;
    SqliteDatabase database;
    List<DoctorFreeTime> doctorFreeTimeList=new ArrayList<>();
    Spinner inputvisitdatespinner;
    ArrayAdapter spinvisitadapter;
    int yechi=0;
    String docUsername;
    String user;
    EditText alaem,hasasiatha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_request);
        Intent intent=getIntent();
        user=intent.getStringExtra("username");
        database = new SqliteDatabase(getApplicationContext());
        doctorsList=database.getAllDoctors();
        ins=new ArrayList<>();
        ins.add("پزشک را انتخاب کنید.");
        for(Doctor doctor: doctorsList){ins.add(doctor.id+"- "+doctor.firstName+" "+doctor.lastName+"("+doctor.takhasos+")");}
        doctors= GetStringArray(ins);
        //choice doctor

        Spinner spindr = (Spinner) findViewById(R.id.inputdr);
        spindr.setOnItemSelectedListener(this);
        ArrayAdapter spindradapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,doctors);
        spindradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spindr.setAdapter(spindradapter);

        alaem=findViewById(R.id.inputalaem);
        hasasiatha=findViewById(R.id.inputhassasiatha);


        //requestType

        Spinner spinner = (Spinner) findViewById(R.id.inputrequestType);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,requesttype);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);


        final Button visitdatebutton=findViewById(R.id.inputVisitDate);
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
                        if(persianCalendar.getTimeInMillis()<Calendar.getInstance().getTimeInMillis()){
                            visitdatebutton.setText("تاریخ باید در آینده باشد.");
                        }else{
                            visitRequestDate=String.valueOf(persianCalendar.getTimeInMillis());
                            visitdatebutton.setText(persianCalendar.getPersianLongDate());
                        }
                    }

                    @Override
                    public void onDismissed() {
                    }
                });
        visitdatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picker.show();
            }
        });


        Button submit=findViewById(R.id.submitVisitRequest);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VisitRequest visitRequest=new VisitRequest();
                visitRequest.doctor=docUsername;
                visitRequest.patient=user;
                visitRequest.allergies=hasasiatha.getText().toString();
                visitRequest.symptoms=alaem.getText().toString();
                visitRequest.type=reqtype;
                visitRequest.date=visitRequestDate;
                visitRequest.time=docfreetime;
                database.addVisitRequest(visitRequest);
                Toast.makeText(getApplicationContext(),"نوبت مورد نظر برای شما رزرو شد.",Toast.LENGTH_LONG).show();
                finish();
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
        if(spin.getId() == R.id.inputdr)
        {
            System.out.println("doc intered");
            choicedDoc=doctors[i];
            String[] splited=choicedDoc.split("-");
            try {
                int docId = Integer.parseInt(splited[0]);
                docUsername=getDoctorUsername(docId);
                doctorFreeTimeList=database.getDoctorFreeTimes(docUsername);
            } catch (NumberFormatException nfe) {

            }
        }
        if(spin.getId() == R.id.inputrequestType)
        {
            System.out.println("reqType intered");
            reqtype=requesttype[i];
            ins=new ArrayList<>();
            if(i==0){
                ins.add("انتخاب ساغت ");
            }else{
                ins.add("انتخاب ساغت "+reqtype);
            }
            for(DoctorFreeTime dc: doctorFreeTimeList){
                System.out.println(dc.freetime);
                Calendar choicedDay=Calendar.getInstance();
                choicedDay.setTimeInMillis(Long.valueOf(visitRequestDate));
                if(dc.status.equals("free")&& dc.type.equals(reqtype) && notReserved(choicedDay,dc.freetime)){
                    ins.add(dc.freetime);
                }
            }
            doctorFreeTimes= GetStringArray(ins);
            inputvisitdatespinner = (Spinner) findViewById(R.id.inputVisitTime);
            inputvisitdatespinner.setOnItemSelectedListener(this);
            spinvisitadapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,doctorFreeTimes);
            spinvisitadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            inputvisitdatespinner.setAdapter(spinvisitadapter);
            if(yechi==0){
                yechi++;
            }else{
                inputvisitdatespinner.setVisibility(View.VISIBLE);
            }
        }
        if(spin.getId() == R.id.inputVisitTime)
        {
            System.out.println("doc free time intered");
            docfreetime=doctorFreeTimes[i];
        }
    }

    private boolean notReserved(Calendar choicedDay, String freetime) {
        List<VisitRequest> visited=database.getVisitRequests(docUsername);
        for(VisitRequest visitRequest:visited){
            Calendar reservedDate=Calendar.getInstance();
            reservedDate.setTimeInMillis(Long.valueOf(visitRequest.date));
            if(choicedDay.get(Calendar.YEAR)==reservedDate.get(Calendar.YEAR)
                    && choicedDay.get(Calendar.MONTH)==reservedDate.get(Calendar.MONTH)
                    && choicedDay.get(Calendar.DAY_OF_MONTH)==reservedDate.get(Calendar.DAY_OF_MONTH)
                    && freetime.equals(visitRequest.time)){
                return false;
            }
        }
        return true;
    }

    public String getDoctorUsername(int id){
        for(Doctor doctor:doctorsList){
            if(doctor.id==id){
                return doctor.username;
            }
        }
        return null;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}