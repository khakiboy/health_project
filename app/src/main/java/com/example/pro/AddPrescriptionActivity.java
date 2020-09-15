package com.example.pro;

import androidx.annotation.ArrayRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class AddPrescriptionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static ArrayList<String> ins,ins2,sicknesins;
    List<String> choicedMedinies,choicedpharmecies;
    ChoicedMedicineAdapter choicedMedicineAdapter;
    ChoicedPharmecyAdapter choicedPharmecyAdapter;
    String doctor,patient,sicknes;
    EditText description;
    SqliteDatabase sqliteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prescription);
        Intent intent=getIntent();
        patient=intent.getStringExtra("patient");
        doctor=intent.getStringExtra("doctor");
        sqliteDatabase=new SqliteDatabase(getApplicationContext());
        List<Medicine> medicineList=sqliteDatabase.getAllMedicine();
        AddPrescriptionActivity.ins=new ArrayList<>();
        AddPrescriptionActivity.ins.add("انتخاب دارو");
        description=findViewById(R.id.descriptionInput);
        for(Medicine medicine: medicineList){
            AddPrescriptionActivity.ins.add(medicine.name);
        }
        Spinner spinner=findViewById(R.id.medicinelistSpinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter spindradapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,AddPrescriptionActivity.ins);
        spindradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spindradapter);
        RecyclerView recyclerView=findViewById(R.id.choicedMedicines);
        choicedMedinies=new ArrayList<>();
        choicedMedicineAdapter=new ChoicedMedicineAdapter(choicedMedinies,this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(choicedMedicineAdapter);

        List<Pharmacy> pharmacyList=sqliteDatabase.getAllPharmecies();
        AddPrescriptionActivity.ins2=new ArrayList<>();
        AddPrescriptionActivity.ins2.add("انتخاب داروخانه");
        for(Pharmacy pharmacy: pharmacyList){
            AddPrescriptionActivity.ins2.add(pharmacy.name);
        }
        Spinner spinner2=findViewById(R.id.pharmecylistSpinner);
        spinner2.setOnItemSelectedListener(this);
        ArrayAdapter spindradapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,AddPrescriptionActivity.ins2);
        spindradapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(spindradapter2);
        RecyclerView recyclerView2=findViewById(R.id.choicedPharmecies);
        choicedpharmecies=new ArrayList<>();
        choicedPharmecyAdapter=new ChoicedPharmecyAdapter(choicedpharmecies,this);
        LinearLayoutManager linearLayoutManager2=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView2.setLayoutManager(linearLayoutManager2);
        recyclerView2.setAdapter(choicedPharmecyAdapter);


        List<Sickness> sicknessList=sqliteDatabase.getAllSicknesses();
        AddPrescriptionActivity.sicknesins=new ArrayList<>();
        AddPrescriptionActivity.sicknesins.add("انتخاب بیماری");
        for(Sickness sickness: sicknessList){
            AddPrescriptionActivity.sicknesins.add(sickness.name);
        }
        Spinner spinner4=findViewById(R.id.sicknessname);
        spinner4.setOnItemSelectedListener(this);
        ArrayAdapter spindradapter4 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,AddPrescriptionActivity.sicknesins);
        spindradapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(spindradapter4);

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
        Spinner spin=(Spinner) adapterView;
        if(spin==findViewById(R.id.medicinelistSpinner)){
            if(AddPrescriptionActivity.ins.get(i).equals("انتخاب دارو")){
                return;
            }
            choicedMedinies.add(AddPrescriptionActivity.ins.get(i));
            choicedMedicineAdapter.notifyDataSetChanged();
        }
        if(spin==findViewById(R.id.pharmecylistSpinner)){
            if(AddPrescriptionActivity.ins2.get(i).equals("انتخاب داروخانه")){
                return;
            }
            choicedpharmecies.add(AddPrescriptionActivity.ins2.get(i));
            choicedPharmecyAdapter.notifyDataSetChanged();
        }
        if(spin==findViewById(R.id.sicknessname)){
            if(AddPrescriptionActivity.sicknesins.get(i).equals("انتخاب بیماری")){
                return;
            }
            sicknes=AddPrescriptionActivity.sicknesins.get(i);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void submitprescription(View view) {
        if(sicknes==null){
            return;
        }
        Prescription prescription=new Prescription();
        prescription.doctor=doctor;
        prescription.patient=patient;
        prescription.description=description.getText().toString();
        prescription.uniqueId=UUID.randomUUID().toString();
        prescription.medicineList=choicedMedinies;
        prescription.pharmacyList=choicedpharmecies;
        prescription.sickness=sicknes;
        Calendar calendar=Calendar.getInstance();
        prescription.date=String.valueOf(calendar.getTimeInMillis());
        sqliteDatabase.addPrescription(prescription);
        if(sqliteDatabase.checkPatientDoctor(patient,doctor)){
            sqliteDatabase.addPatientDoctor(patient,doctor);
        }
        if(sqliteDatabase.checkPatientSickness(patient,sicknes)){
            sqliteDatabase.addPatientSickness(patient,sicknes);
        }
        for(String medicine:choicedMedinies){
            if(sqliteDatabase.checkPatientMedicine(patient,medicine)){
                sqliteDatabase.addPatientMedicine(patient,medicine);
            }
        }
        finish();
    }
}