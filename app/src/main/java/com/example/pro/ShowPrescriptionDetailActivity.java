package com.example.pro;

import androidx.appcompat.app.AppCompatActivity;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShowPrescriptionDetailActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    List<String> pharmecyNames;
    String choicedPharmecy;
    List<Pharmacy> pharmacyList;
    String uniqId;
    SqliteDatabase sqliteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_prescription_detail);
        Intent intent=getIntent();
        sqliteDatabase=new SqliteDatabase(getApplicationContext());
        uniqId=intent.getStringExtra("uniqueId");
        String userType=intent.getStringExtra("userType");
        String patientUsername=intent.getStringExtra("patientUsername");
        Prescription prescription=sqliteDatabase.findPrescription(uniqId);
        TextView docname=findViewById(R.id.predoctorName);
        Doctor doctor=sqliteDatabase.findDoctor(prescription.doctor);
        docname.setText(doctor.firstName+" "+doctor.lastName+"("+doctor.takhasos+")");
        TextView sicknessname=findViewById(R.id.presicknessname);
        sicknessname.setText(prescription.sickness);
        TextView date=findViewById(R.id.predate);
        PersianCalendar calendar=new PersianCalendar();
        calendar.setTimeInMillis(Long.parseLong(prescription.date));
        date.setText(calendar.getPersianShortDate());
        TextView doctorDescription=findViewById(R.id.preDocDescription);
        doctorDescription.setText(prescription.description);
        List<String> medicines=prescription.medicineList;
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.card_list,medicines);
        ListView listView = (ListView) findViewById(R.id.choicedMedicinlist);
        listView.setAdapter(adapter);
        Spinner spin = (Spinner) findViewById(R.id.choicePharmacySpinner);
        if(userType.equals("patient")){
            pharmacyList=sqliteDatabase.getAllPharmecies();
            pharmecyNames=new ArrayList<>();
            pharmecyNames.add("انتخاب داروخانه");
            List<String> choicedPharmecies=prescription.pharmacyList;
            for(Pharmacy pharmacy:pharmacyList){
                if(isSuggested(choicedPharmecies,pharmacy)){
                    pharmecyNames.add(pharmacy.id+"-"+pharmacy.name+" (پیشنهاد شده)");
                }else {
                    pharmecyNames.add(pharmacy.id+"-"+pharmacy.name);
                }
            }
            spin.setOnItemSelectedListener(this);
            ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,pharmecyNames);
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(aa);
        }
        else{
            spin.setVisibility(View.GONE);
            Button button=findViewById(R.id.sendPrescriptionToPharmacy);
            button.setVisibility(View.GONE);
            TextView textView=findViewById(R.id.prescriptionDetailPageTitle);
            Patient patient=sqliteDatabase.findPatient(patientUsername);
            textView.setText(patient.firstName+" "+patient.lastName);
        }


    }

    private boolean isSuggested(List<String> choicedPharmecies, Pharmacy pharmacy) {
        for(String s:choicedPharmecies){
            if(s.equals(pharmacy.name)){
                return true;
            }
        }
        return false;
    }

    public void sendToPharmacy(View view) {
        String[] splited=choicedPharmecy.split("-");
        try {
            int choichedparmecyId=Integer.parseInt(splited[0]);
            String pharmecyUsername=getPharmecyUsername(choichedparmecyId);
            sqliteDatabase.addRecievedPrescriptionPharmacy(pharmecyUsername,uniqId);
            Toast.makeText(getApplicationContext(),"نسخه شما برای داروخانه ارسال شد.",Toast.LENGTH_LONG).show();
            finish();
        } catch (NumberFormatException nfe) {
            Toast.makeText(getApplicationContext(),"یک داروخانه انتخاب کنید.",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        choicedPharmecy=pharmecyNames.get(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public String getPharmecyUsername(int id){
        for(Pharmacy pharmacy:pharmacyList){
            if(pharmacy.id==id){
                return pharmacy.username;
            }
        }
        return null;
    }
}