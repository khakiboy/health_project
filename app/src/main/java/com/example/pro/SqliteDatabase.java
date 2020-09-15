package com.example.pro;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.xml.sax.DTDHandler;

import java.util.ArrayList;
import java.util.List;

import androidx.core.graphics.drawable.TintAwareDrawable;
public class SqliteDatabase extends SQLiteOpenHelper {
    private    static final int DATABASE_VERSION =    3;
    private    static final String DATABASE_NAME = "pro";
    private    static final String TABLE_PATIENT = "patients";
    private    static final String TABLE_DOCTOR = "doctors";
    private    static final String TABLE_DOCTORFREETIME = "doctorfreetime";
    private    static final String TABLE_PHARMECY = "pharmecies";
    private    static final String TABLE_INSURENCE = "insurences";
    private    static final String TABLE_VISITREQUEST = "visitrequests";
    private    static final String TABLE_MEDICINE = "medicines";
    private    static final String TABLE_SICKNESS = "sicknesses";
    private    static final String TABLE_PRESCIPTION = "prescriptions";
    private    static final String TABLE_DRUGINTERACTION = "druginteractions";
    private    static final String TABLE_PRESCIPTIONMEDICINE = "prescriptionmedicine";
    private    static final String TABLE_PRESCIPTIONPHARMECY = "prescriptionpharmecy";
    private    static final String TABLE_INSURENCEPATIENT = "insurancepatients";
    private    static final String TABLE_PATIENTDOCTOR = "patientdoctor";
    private    static final String TABLE_PATIENTMEDICINE = "patientmedicine";
    private    static final String TABLE_PATIENTSICKNESS = "patientsickness";
    private    static final String TABLE_RECIVEDPRESCRIPTIONSPHARMACY = "recivedprescriptioinspharmacy";
    private static final String COLUMN_ID = "id", COLUMN_FIRSTNAME = "firstname", COLUMN_LASTNAME="lastname",COLUMN_BIRTHDATE="birthdate", COLUMN_EMAILADDRESS="emailaddress", COLUMN_NAME="name",
         COLUMN_USERNAME="username", COLUMN_PASSWORD="password",COLUMN_ADDRESS="address",COLUMN_REGISTERDATE="registerdate" , COLUMN_PERSONALPHONENUMBER="phonenumber" ,COLUMN_OFFICEPHONENUMBER="officephonenumber",
            COLUMN_INSURENCE="insurence", COLUMN_TAKHASOS="takhasos",COLUMN_GENDER="gender",COLUMN_DOCTOR="doctor",COLUMN_PATIENT="patient" , COLUMN_SYMPTOMS="symptoms" ,COLUMN_DATE="date",COLUMN_TYPE="type",COLUMN_DESCRIPTION="description",
            COLUMN_MEDICINE="medicine", COLUMN_PHARMECY="pharmecy",COLUMN_UNIQUEID="uniqueid",COLUMN_TIME="time",COLUMN_ALLERGIES="allergies",COLUMN_FREETIME="freetime",COLUMN_STATUS="status",COLUMN_OFFICEADDRESS="officeaddress",
            COLUMN_PRESCRIPTIONID="prescriptionid",COLUMN_MEDICINE1="medicine1" , COLUMN_MEDICINE2="medicine2", COLUMN_SICKNESS="sickness" ;
    public SqliteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PATIENT_TABLE = "CREATE    TABLE " + TABLE_PATIENT
                + "(" + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_FIRSTNAME + " TEXT,"
                + COLUMN_LASTNAME + " TEXT,"
                + COLUMN_BIRTHDATE + " TEXT,"
                + COLUMN_EMAILADDRESS + " TEXT,"
                + COLUMN_USERNAME + " TEXT,"
                + COLUMN_PASSWORD + " TEXT,"
                + COLUMN_REGISTERDATE + " TEXT,"
                + COLUMN_PERSONALPHONENUMBER + " TEXT,"
                + COLUMN_ADDRESS + " TEXT,"
                + COLUMN_INSURENCE + " TEXT,"
                + COLUMN_GENDER + " TEXT"
                + ")";
        String CREATE_DOCTOR_TABLE = "CREATE    TABLE " + TABLE_DOCTOR
                + "(" + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_FIRSTNAME + " TEXT,"
                + COLUMN_LASTNAME + " TEXT,"
                + COLUMN_TAKHASOS + " TEXT,"
                + COLUMN_BIRTHDATE + " TEXT,"
                + COLUMN_EMAILADDRESS + " TEXT,"
                + COLUMN_ADDRESS + " TEXT,"
                + COLUMN_USERNAME + " TEXT,"
                + COLUMN_PASSWORD + " TEXT,"
                + COLUMN_REGISTERDATE + " TEXT,"
                + COLUMN_PERSONALPHONENUMBER + " TEXT,"
                + COLUMN_OFFICEPHONENUMBER + " TEXT,"
                + COLUMN_OFFICEADDRESS + " TEXT,"
                + COLUMN_GENDER + " TEXT"
                + ")";
        String CREATE_PHARMECY_TABLE = "CREATE    TABLE " + TABLE_PHARMECY
                + "(" + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_EMAILADDRESS + " TEXT,"
                + COLUMN_USERNAME + " TEXT,"
                + COLUMN_PASSWORD + " TEXT,"
                + COLUMN_REGISTERDATE + " TEXT,"
                + COLUMN_OFFICEPHONENUMBER + " TEXT,"
                + COLUMN_ADDRESS + " TEXT"
                + ")";
        String CREATE_INSURENCE_TABLE = "CREATE    TABLE " + TABLE_INSURENCE
                + "(" + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_EMAILADDRESS + " TEXT,"
                + COLUMN_USERNAME + " TEXT,"
                + COLUMN_PASSWORD + " TEXT,"
                + COLUMN_REGISTERDATE + " TEXT,"
                + COLUMN_OFFICEPHONENUMBER + " TEXT,"
                + COLUMN_ADDRESS + " TEXT"
                + ")";
        String CREATE_VISITREQUEST_TABLE = "CREATE    TABLE " + TABLE_VISITREQUEST
                + "(" + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_DOCTOR + " TEXT,"
                + COLUMN_PATIENT + " TEXT,"
                + COLUMN_SYMPTOMS + " TEXT,"
                + COLUMN_ALLERGIES + " TEXT,"
                + COLUMN_DATE + " TEXT,"
                + COLUMN_TIME + " TEXT,"
                + COLUMN_TYPE + " TEXT"
                + ")";
        String CREATE_PRESCIPTION_TABLE = "CREATE    TABLE " + TABLE_PRESCIPTION
                + "(" + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_DOCTOR + " TEXT,"
                + COLUMN_PATIENT + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_DATE + " TEXT,"
                + COLUMN_UNIQUEID + " TEXT,"
                + COLUMN_SICKNESS + " TEXT"
                + ")";
        String CREATE_MEDICINE_TABLE = "CREATE    TABLE " + TABLE_MEDICINE
                + "(" + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT"
                + ")";
        String CREATE_SICKNESS_TABLE = "CREATE    TABLE " + TABLE_SICKNESS
                + "(" + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_SYMPTOMS + " TEXT,"
                + COLUMN_TYPE + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT"
                + ")";
        String CREATE_DRUGINTERACTION_TABLE = "CREATE    TABLE " + TABLE_DRUGINTERACTION
                + "(" + COLUMN_MEDICINE1 + " TEXT,"
                + COLUMN_MEDICINE2 + " TEXT"
                + ")";
        String CREATE_PRESCRIPTIONMEDICINE_TABLE = "CREATE    TABLE " + TABLE_PRESCIPTIONMEDICINE
                + "(" + COLUMN_PRESCRIPTIONID + " TEXT,"
                + COLUMN_MEDICINE + " TEXT"
                + ")";
        String CREATE_RECIEVEDPRESCRIPTIONPHARMECY_TABLE = "CREATE    TABLE " + TABLE_RECIVEDPRESCRIPTIONSPHARMACY
                + "(" + COLUMN_PRESCRIPTIONID + " TEXT,"
                + COLUMN_PHARMECY + " TEXT"
                + ")";
        String CREATE_PRESCRIPTIONPHAREMCY_TABLE = "CREATE    TABLE " + TABLE_PRESCIPTIONPHARMECY
                + "(" + COLUMN_PRESCRIPTIONID + " TEXT,"
                + COLUMN_PHARMECY + " TEXT"
                + ")";
        String CREATE_INSURENCEPATIENT_TABLE = "CREATE    TABLE " + TABLE_INSURENCEPATIENT
                + "(" + COLUMN_INSURENCE + " TEXT,"
                + COLUMN_PATIENT + " TEXT"
                + ")";
        String CREATE_PATIENTDOCTOR_TABLE = "CREATE    TABLE " + TABLE_PATIENTDOCTOR
                + "(" + COLUMN_DOCTOR + " TEXT,"
                + COLUMN_PATIENT + " TEXT"
                + ")";
        String CREATE_PATIENTMEDICINE_TABLE = "CREATE    TABLE " + TABLE_PATIENTMEDICINE
                + "(" + COLUMN_MEDICINE + " TEXT,"
                + COLUMN_PATIENT + " TEXT"
                + ")";
        String CREATE_PATIENTSICKNESS_TABLE = "CREATE    TABLE " + TABLE_PATIENTSICKNESS
                + "(" + COLUMN_SICKNESS + " TEXT,"
                + COLUMN_PATIENT + " TEXT"
                + ")";
        String CREATE_DOCTORFREETIME_TABLE = "CREATE    TABLE " + TABLE_DOCTORFREETIME
                + "(" + COLUMN_USERNAME + " TEXT,"
                + COLUMN_FREETIME + " TEXT,"
                + COLUMN_TYPE + " TEXT,"
                + COLUMN_STATUS + " TEXT"
                + ")";
        db.execSQL(CREATE_PATIENT_TABLE);
        db.execSQL(CREATE_DOCTOR_TABLE);
        db.execSQL(CREATE_PHARMECY_TABLE);
        db.execSQL(CREATE_INSURENCE_TABLE);
        db.execSQL(CREATE_VISITREQUEST_TABLE);
        db.execSQL(CREATE_PRESCIPTION_TABLE);
        db.execSQL(CREATE_MEDICINE_TABLE);
        db.execSQL(CREATE_SICKNESS_TABLE);
        db.execSQL(CREATE_DRUGINTERACTION_TABLE);
        db.execSQL(CREATE_PRESCRIPTIONMEDICINE_TABLE);
        db.execSQL(CREATE_PRESCRIPTIONPHAREMCY_TABLE);
        db.execSQL(CREATE_INSURENCEPATIENT_TABLE);
        db.execSQL(CREATE_PATIENTDOCTOR_TABLE);
        db.execSQL(CREATE_PATIENTMEDICINE_TABLE);
        db.execSQL(CREATE_PATIENTSICKNESS_TABLE);
        db.execSQL(CREATE_DOCTORFREETIME_TABLE);;
        db.execSQL(CREATE_RECIEVEDPRESCRIPTIONPHARMECY_TABLE);


        //insert some data

        Medicine medicine=new Medicine("سرماخوردگی بزرگسالان","mikhori mipari hava");
        Medicine medicine2=new Medicine("استامینوفن 100","mikhori mipari hava");
        Medicine medicine3=new Medicine("استامینوفن 500","mikhori mipari hava");
        Medicine medicine1=new Medicine("پانادول","mikhori mipari hava");
        Medicine medicine22=new Medicine("ژلیفن","mikhori mipari hava");
        Medicine medicine33=new Medicine("بتامتازون","mikhori mipari hava");
        Medicine medicine11=new Medicine("دکسکلرفنیرامین","mikhori mipari hava");
        Medicine medicine221=new Medicine("دیازپام","mikhori mipari hava");
        Medicine medicine321=new Medicine("دیفن هیدرامین","mikhori mipari hava");
        this.addMedicine(medicine);
        this.addMedicine(medicine2);
        this.addMedicine(medicine3);
        this.addMedicine(medicine1);
        this.addMedicine(medicine22);
        this.addMedicine(medicine321);
        this.addMedicine(medicine11);
        this.addMedicine(medicine221);
        this.addMedicine(medicine33);
        Sickness sickness=new Sickness("سرماخوردگی","kok kandan","riavi","gerefti khalasi");
        Sickness sickness1=new Sickness("آنفولانزا","kok kandan","riavi","gerefti khalasi");
        Sickness sickness2=new Sickness("کرونا","kok kandan","riavi","gerefti khalasi");
        Sickness sickness3=new Sickness("آسم","kok kandan","riavi","gerefti khalasi");
        Sickness sickness4=new Sickness("آسم 2","kok kandan","riavi","gerefti khalasi");
        this.addSickness(sickness);
        this.addSickness(sickness1);
        this.addSickness(sickness2);
        this.addSickness(sickness3);
        this.addSickness(sickness4);


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOCTOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHARMECY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INSURENCE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VISITREQUEST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRESCIPTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDICINE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SICKNESS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRUGINTERACTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRESCIPTIONMEDICINE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRESCIPTIONPHARMECY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INSURENCEPATIENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOCTORFREETIME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENTDOCTOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENTMEDICINE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENTSICKNESS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIVEDPRESCRIPTIONSPHARMACY);
        onCreate(db);
    }
    public void dropdatabase(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOCTOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHARMECY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INSURENCE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VISITREQUEST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRESCIPTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDICINE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SICKNESS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRUGINTERACTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRESCIPTIONMEDICINE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRESCIPTIONPHARMECY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INSURENCEPATIENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOCTORFREETIME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENTDOCTOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENTMEDICINE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENTSICKNESS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIVEDPRESCRIPTIONSPHARMACY);
        onCreate(db);
    }
    public void addPatient(Patient patient){
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRSTNAME, patient.firstName);
        values.put(COLUMN_LASTNAME, patient.lastName);
        values.put(COLUMN_BIRTHDATE, patient.birthDate);
        values.put(COLUMN_EMAILADDRESS, patient.emailAdress);
        values.put(COLUMN_USERNAME, patient.username);
        values.put(COLUMN_PASSWORD, patient.password);
        values.put(COLUMN_REGISTERDATE, patient.registerDate);
        values.put(COLUMN_ADDRESS, patient.address);
        values.put(COLUMN_PERSONALPHONENUMBER, patient.phoneNumber);
        values.put(COLUMN_INSURENCE, patient.insurance);
        values.put(COLUMN_GENDER, patient.gender);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_PATIENT, null, values);
    }
    public Patient findPatient(String username){
        String query = "Select * FROM "    + TABLE_PATIENT + " WHERE " + COLUMN_USERNAME + " = '" + username+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Patient mpatient = null;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            int id = Integer.parseInt(cursor.getString(0));
            mpatient = new Patient(id);
            mpatient.firstName = cursor.getString(1);
            mpatient.lastName = cursor.getString(2);
            mpatient.birthDate = cursor.getString(3);
            mpatient.emailAdress = cursor.getString(4);
            mpatient.username = cursor.getString(5);
            mpatient.password = cursor.getString(6);
            mpatient.registerDate = cursor.getString(7);
            mpatient.phoneNumber = cursor.getString(8);
            mpatient.address = cursor.getString(9);
            mpatient.insurance = cursor.getString(10);
            mpatient.gender = cursor.getString(11);
        }
        cursor.close();
        return mpatient;
    }
    public void addDoctor(Doctor dr){
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRSTNAME, dr.firstName);
        values.put(COLUMN_LASTNAME, dr.lastName);
        values.put(COLUMN_BIRTHDATE, dr.birthDate);
        values.put(COLUMN_TAKHASOS, dr.takhasos);
        values.put(COLUMN_EMAILADDRESS, dr.emailAdress);
        values.put(COLUMN_USERNAME, dr.username);
        values.put(COLUMN_PASSWORD, dr.password);
        values.put(COLUMN_REGISTERDATE, dr.registerDate);
        values.put(COLUMN_ADDRESS, dr.address);
        values.put(COLUMN_PERSONALPHONENUMBER, dr.phoneNumber);
        values.put(COLUMN_OFFICEPHONENUMBER, dr.officePhoneNumber);
        values.put(COLUMN_OFFICEADDRESS, dr.officeAddress);
        values.put(COLUMN_GENDER, dr.gender);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_DOCTOR, null, values);
    }
    public Doctor findDoctor(String username){
        String query = "Select * FROM "    + TABLE_DOCTOR + " WHERE " + COLUMN_USERNAME + " = '" + username+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Doctor mpatient = null;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            int id = Integer.parseInt(cursor.getString(0));
            mpatient = new Doctor(id);
            mpatient.firstName = cursor.getString(1);
            mpatient.lastName = cursor.getString(2);
            mpatient.takhasos = cursor.getString(3);
            mpatient.birthDate = cursor.getString(4);
            mpatient.emailAdress = cursor.getString(5);
            mpatient.address = cursor.getString(6);
            mpatient.username = cursor.getString(7);
            mpatient.password = cursor.getString(8);
            mpatient.registerDate = cursor.getString(9);
            mpatient.phoneNumber = cursor.getString(10);
            mpatient.officePhoneNumber = cursor.getString(11);
            mpatient.officeAddress = cursor.getString(12);
            mpatient.gender = cursor.getString(13);
        }
        cursor.close();
        return mpatient;
    }
    public void addDoctorFreeTime(String username,String freeTime,String type,String status){
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_FREETIME, freeTime);
        values.put(COLUMN_TYPE, type);
        values.put(COLUMN_STATUS, status);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_DOCTORFREETIME, null, values);
    }
    public List<DoctorFreeTime> getDoctorFreeTimes(String username){
        String query = "Select * FROM " + TABLE_DOCTORFREETIME + " WHERE " + COLUMN_USERNAME + " = '" + username+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        DoctorFreeTime mpatient = null;
        List<DoctorFreeTime> doctorsList=new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            String user = cursor.getString(0);
            mpatient = new DoctorFreeTime(user);
            mpatient.freetime = cursor.getString(1);
            mpatient.type = cursor.getString(2);
            mpatient.status = cursor.getString(3);
            doctorsList.add(mpatient);
            while(cursor.moveToNext()) {
                user = cursor.getString(0);
                mpatient = new DoctorFreeTime(user);
                mpatient.freetime = cursor.getString(1);
                mpatient.type = cursor.getString(2);
                mpatient.status = cursor.getString(3);
                doctorsList.add(mpatient);
            }
        }
        cursor.close();
        return doctorsList;
    }
    public void addPharmecy(Pharmacy pharmacy){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, pharmacy.name);
        values.put(COLUMN_EMAILADDRESS, pharmacy.emailAddress);
        values.put(COLUMN_USERNAME, pharmacy.username);
        values.put(COLUMN_PASSWORD, pharmacy.password);
        values.put(COLUMN_REGISTERDATE, pharmacy.registerDate);
        values.put(COLUMN_OFFICEPHONENUMBER, pharmacy.phoneNumber);
        values.put(COLUMN_ADDRESS, pharmacy.address);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_PHARMECY, null, values);
    }
    public Pharmacy findPharmecy(String username){
        String query = "Select * FROM "    + TABLE_PHARMECY + " WHERE " + COLUMN_USERNAME + " = '" + username+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Pharmacy mpatient = null;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            int id = Integer.parseInt(cursor.getString(0));
            mpatient = new Pharmacy(id);
            mpatient.name = cursor.getString(1);
            mpatient.emailAddress = cursor.getString(2);
            mpatient.username = cursor.getString(3);
            mpatient.password = cursor.getString(4);
            mpatient.registerDate = cursor.getString(5);
            mpatient.phoneNumber = cursor.getString(6);
            mpatient.address = cursor.getString(7);
        }
        cursor.close();
        return mpatient;
    }
    public void addInsurance(Insurance insurance){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, insurance.name);
        values.put(COLUMN_EMAILADDRESS, insurance.emailAddress);
        values.put(COLUMN_USERNAME, insurance.username);
        values.put(COLUMN_PASSWORD, insurance.password);
        values.put(COLUMN_REGISTERDATE, insurance.registerDate);
        values.put(COLUMN_OFFICEPHONENUMBER, insurance.phoneNumber);
        values.put(COLUMN_ADDRESS, insurance.address);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_INSURENCE, null, values);
    }
    public void addVisitRequest(VisitRequest visitRequest){
        ContentValues values = new ContentValues();
        values.put(COLUMN_DOCTOR, visitRequest.doctor);
        values.put(COLUMN_PATIENT, visitRequest.patient);
        values.put(COLUMN_SYMPTOMS, visitRequest.symptoms);
        values.put(COLUMN_ALLERGIES, visitRequest.allergies);
        values.put(COLUMN_DATE, visitRequest.date);
        values.put(COLUMN_TIME, visitRequest.time);
        values.put(COLUMN_TYPE, visitRequest.type);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_VISITREQUEST, null, values);
    }
    public List<VisitRequest> getVisitRequests(String docUsername){
        String query = "Select * FROM "    + TABLE_VISITREQUEST + " WHERE " + COLUMN_DOCTOR + " = '" + docUsername +"' "
                +" ORDER BY "+ COLUMN_DATE +" ASC" ;
        SQLiteDatabase db = this.getWritableDatabase();
        VisitRequest mpatient = null;
        List<VisitRequest> visitRequestList=new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            int id = Integer.parseInt(cursor.getString(0));
            mpatient = new VisitRequest(id);
            mpatient.doctor = cursor.getString(1);
            mpatient.patient = cursor.getString(2);
            mpatient.symptoms = cursor.getString(3);
            mpatient.allergies = cursor.getString(4);
            mpatient.date = cursor.getString(5);
            mpatient.time = cursor.getString(6);
            mpatient.type = cursor.getString(7);
            visitRequestList.add(mpatient);
            while(cursor.moveToNext()) {
                id = Integer.parseInt(cursor.getString(0));
                mpatient = new VisitRequest(id);
                mpatient.doctor = cursor.getString(1);
                mpatient.patient = cursor.getString(2);
                mpatient.symptoms = cursor.getString(3);
                mpatient.allergies = cursor.getString(4);
                mpatient.date = cursor.getString(5);
                mpatient.time = cursor.getString(6);
                mpatient.type = cursor.getString(7);
                visitRequestList.add(mpatient);
            }
        }
        cursor.close();
        return visitRequestList;
    }
    public Insurance findInsurance(String username){
        String query = "Select * FROM "    + TABLE_INSURENCE + " WHERE " + COLUMN_USERNAME + " = '" + username+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Insurance mpatient = null;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            int id = Integer.parseInt(cursor.getString(0));
            mpatient = new Insurance(id);
            mpatient.name = cursor.getString(1);
            mpatient.emailAddress = cursor.getString(2);
            mpatient.username = cursor.getString(3);
            mpatient.password = cursor.getString(4);
            mpatient.registerDate = cursor.getString(5);
            mpatient.phoneNumber = cursor.getString(6);
            mpatient.address = cursor.getString(7);
        }
        cursor.close();
        return mpatient;
    }
    public List<Insurance> getAllInsurances(){
        String query = "Select * FROM "    + TABLE_INSURENCE ;
        SQLiteDatabase db = this.getWritableDatabase();
        Insurance mpatient = null;
        List<Insurance> insuranceList=new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            int id = Integer.parseInt(cursor.getString(0));
            mpatient = new Insurance(id);
            mpatient.name = cursor.getString(1);
            mpatient.emailAddress = cursor.getString(2);
            mpatient.username = cursor.getString(3);
            mpatient.password = cursor.getString(4);
            mpatient.registerDate = cursor.getString(5);
            mpatient.phoneNumber = cursor.getString(6);
            mpatient.address = cursor.getString(7);
            insuranceList.add(mpatient);
            while(cursor.moveToNext()) {
                id = Integer.parseInt(cursor.getString(0));
                mpatient = new Insurance(id);
                mpatient.name = cursor.getString(1);
                mpatient.emailAddress = cursor.getString(2);
                mpatient.username = cursor.getString(3);
                mpatient.password = cursor.getString(4);
                mpatient.registerDate = cursor.getString(5);
                mpatient.phoneNumber = cursor.getString(6);
                mpatient.address = cursor.getString(7);
                insuranceList.add(mpatient);
            }
        }
        cursor.close();
        return insuranceList;
    }
    public List<Pharmacy> getAllPharmecies(){
        String query = "Select * FROM "    + TABLE_PHARMECY ;
        SQLiteDatabase db = this.getWritableDatabase();
        Pharmacy mpatient = null;
        List<Pharmacy> pharmacyList=new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            int id = Integer.parseInt(cursor.getString(0));
            mpatient = new Pharmacy(id);
            mpatient.name = cursor.getString(1);
            mpatient.emailAddress = cursor.getString(2);
            mpatient.username = cursor.getString(3);
            mpatient.password = cursor.getString(4);
            mpatient.registerDate = cursor.getString(5);
            mpatient.phoneNumber = cursor.getString(6);
            mpatient.address = cursor.getString(7);
            pharmacyList.add(mpatient);
            while(cursor.moveToNext()) {
                id = Integer.parseInt(cursor.getString(0));
                mpatient = new Pharmacy(id);
                mpatient.name = cursor.getString(1);
                mpatient.emailAddress = cursor.getString(2);
                mpatient.username = cursor.getString(3);
                mpatient.password = cursor.getString(4);
                mpatient.registerDate = cursor.getString(5);
                mpatient.phoneNumber = cursor.getString(6);
                mpatient.address = cursor.getString(7);
                pharmacyList.add(mpatient);
            }
        }
        cursor.close();
        return pharmacyList;
    }
    public List<Doctor> getAllDoctors(){
        String query = "Select * FROM "    + TABLE_DOCTOR ;
        SQLiteDatabase db = this.getWritableDatabase();
        Doctor mpatient = null;
        List<Doctor> doctorsList=new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            int id = Integer.parseInt(cursor.getString(0));
            mpatient = new Doctor(id);
            mpatient.firstName = cursor.getString(1);
            mpatient.lastName = cursor.getString(2);
            mpatient.takhasos = cursor.getString(3);
            mpatient.birthDate = cursor.getString(4);
            mpatient.emailAdress = cursor.getString(5);
            mpatient.address = cursor.getString(6);
            mpatient.username = cursor.getString(7);
            mpatient.password = cursor.getString(8);
            mpatient.registerDate = cursor.getString(9);
            mpatient.phoneNumber = cursor.getString(10);
            mpatient.officePhoneNumber = cursor.getString(11);
            mpatient.officeAddress = cursor.getString(12);
            mpatient.gender = cursor.getString(13);
            doctorsList.add(mpatient);
            while(cursor.moveToNext()) {
                id = Integer.parseInt(cursor.getString(0));
                mpatient = new Doctor(id);
                mpatient.firstName = cursor.getString(1);
                mpatient.lastName = cursor.getString(2);
                mpatient.takhasos = cursor.getString(3);
                mpatient.birthDate = cursor.getString(4);
                mpatient.emailAdress = cursor.getString(5);
                mpatient.address = cursor.getString(6);
                mpatient.username = cursor.getString(7);
                mpatient.password = cursor.getString(8);
                mpatient.registerDate = cursor.getString(9);
                mpatient.phoneNumber = cursor.getString(10);
                mpatient.officePhoneNumber = cursor.getString(11);
                mpatient.officeAddress = cursor.getString(12);
                mpatient.gender = cursor.getString(13);
                doctorsList.add(mpatient);
            }
        }
        cursor.close();
        return doctorsList;
    }
    public void addMedicine(Medicine medicine){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, medicine.name);
        values.put(COLUMN_DESCRIPTION, medicine.description);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_MEDICINE, null, values);
    }
    public List<Medicine> getAllMedicine(){
        String query = "Select * FROM "  + TABLE_MEDICINE +" ORDER BY "+ COLUMN_NAME +" ASC";
        SQLiteDatabase db = this.getWritableDatabase();
        Medicine mpatient = null;
        List<Medicine> medicineList=new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            int id = Integer.parseInt(cursor.getString(0));
            mpatient = new Medicine(id);
            mpatient.name = cursor.getString(1);
            mpatient.description = cursor.getString(2);
            medicineList.add(mpatient);
            while(cursor.moveToNext()) {
                id = Integer.parseInt(cursor.getString(0));
                mpatient = new Medicine(id);
                mpatient.name = cursor.getString(1);
                mpatient.description = cursor.getString(2);
                medicineList.add(mpatient);
            }
        }
        cursor.close();
        return medicineList;
    }
    public void addPrescription(Prescription prescription){
        ContentValues values = new ContentValues();
        values.put(COLUMN_DOCTOR, prescription.doctor);
        values.put(COLUMN_PATIENT, prescription.patient);
        values.put(COLUMN_UNIQUEID, prescription.uniqueId);
        values.put(COLUMN_DESCRIPTION, prescription.description);
        values.put(COLUMN_DATE, prescription.date);
        values.put(COLUMN_SICKNESS, prescription.sickness);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_PRESCIPTION, null, values);
        for(String medicine:prescription.medicineList){
            values = new ContentValues();
            values.put(COLUMN_PRESCRIPTIONID, prescription.uniqueId);
            values.put(COLUMN_MEDICINE, medicine);
            db.insert(TABLE_PRESCIPTIONMEDICINE, null, values);
        }
        for(String pharmecy:prescription.pharmacyList){
            values = new ContentValues();
            values.put(COLUMN_PRESCRIPTIONID, prescription.uniqueId);
            values.put(COLUMN_PHARMECY, pharmecy);
            db.insert(TABLE_PRESCIPTIONPHARMECY, null, values);
        }
    }
    public List<String> getPrescriptionMedicines(String presciptionId){
        String query = "Select * FROM "  + TABLE_PRESCIPTIONMEDICINE + " WHERE " + COLUMN_PRESCRIPTIONID + " = '" + presciptionId +"' ";
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> prescriptionMedicineList=new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            prescriptionMedicineList.add(cursor.getString(1));
            while(cursor.moveToNext()) {
                prescriptionMedicineList.add(cursor.getString(1));
            }
        }
        cursor.close();
        return prescriptionMedicineList;
    }
    public List<String> getPrescriptionPharmecies(String presciptionId){
        String query = "Select * FROM "  + TABLE_PRESCIPTIONPHARMECY + " WHERE " + COLUMN_PRESCRIPTIONID + " = '" + presciptionId +"' ";
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> prescriptionMedicineList=new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            prescriptionMedicineList.add(cursor.getString(1));
            while(cursor.moveToNext()) {
                prescriptionMedicineList.add(cursor.getString(1));
            }
        }
        cursor.close();
        return prescriptionMedicineList;
    }
    public List<Prescription> getAllPrescription(String patientusername){
        String query = "Select * FROM "  + TABLE_PRESCIPTION+" WHERE " + COLUMN_PATIENT + " = '" + patientusername+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Prescription mpatient = null;
        List<Prescription> prescriptionList=new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            int id = Integer.parseInt(cursor.getString(0));
            mpatient = new Prescription(id);
            mpatient.doctor = cursor.getString(1);
            mpatient.patient = cursor.getString(2);
            mpatient.description = cursor.getString(3);
            mpatient.date = cursor.getString(4);
            mpatient.uniqueId = cursor.getString(5);
            mpatient.sickness = cursor.getString(6);
            mpatient.pharmacyList=this.getPrescriptionPharmecies(mpatient.uniqueId);
            mpatient.medicineList=this.getPrescriptionMedicines(mpatient.uniqueId);
            prescriptionList.add(mpatient);
            while(cursor.moveToNext()) {
                id = Integer.parseInt(cursor.getString(0));
                mpatient = new Prescription(id);
                mpatient.doctor = cursor.getString(1);
                mpatient.patient = cursor.getString(2);
                mpatient.description = cursor.getString(3);
                mpatient.date = cursor.getString(4);
                mpatient.uniqueId = cursor.getString(5);
                mpatient.sickness= cursor.getString(6);
                mpatient.pharmacyList=this.getPrescriptionPharmecies(mpatient.uniqueId);
                mpatient.medicineList=this.getPrescriptionMedicines(mpatient.uniqueId);
                prescriptionList.add(mpatient);
            }
        }
        cursor.close();
        return prescriptionList;
    }
    public Prescription findPrescription(String uniqId){
        String query = "Select * FROM " + TABLE_PRESCIPTION + " WHERE " + COLUMN_UNIQUEID + " = '" + uniqId+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Prescription mpatient = null;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            int id = Integer.parseInt(cursor.getString(0));
            mpatient = new Prescription(id);
            mpatient.doctor = cursor.getString(1);
            mpatient.patient = cursor.getString(2);
            mpatient.description = cursor.getString(3);
            mpatient.date = cursor.getString(4);
            mpatient.uniqueId = cursor.getString(5);
            mpatient.sickness = cursor.getString(6);
            mpatient.pharmacyList=this.getPrescriptionPharmecies(uniqId);
            mpatient.medicineList=this.getPrescriptionMedicines(uniqId);
        }
        cursor.close();
        return mpatient;
    }
    public void addRecievedPrescriptionPharmacy(String pharmacy,String prescription){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRESCRIPTIONID, prescription);
        values.put(COLUMN_PHARMECY, pharmacy);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_RECIVEDPRESCRIPTIONSPHARMACY, null, values);
    }
    public void addPatientSickness(String patientId,String sicknessName){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PATIENT, patientId);
        values.put(COLUMN_SICKNESS,sicknessName );
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_PATIENTSICKNESS, null, values);
    }
    public List<String> getPatientSickness(String patient){
        String query = "Select * FROM "  + TABLE_PATIENTSICKNESS + " WHERE " + COLUMN_PATIENT + " = '" + patient +"' ";
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> sicknessList=new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            sicknessList.add(cursor.getString(0));
            while(cursor.moveToNext()) {
                sicknessList.add(cursor.getString(0));
            }
        }
        cursor.close();
        return sicknessList;
    }
    public Boolean checkPatientSickness(String patient,String sickness){
        String query = "Select * FROM "  + TABLE_PATIENTSICKNESS
                + " WHERE " + COLUMN_PATIENT + " = '" + patient +"' and "
                + COLUMN_SICKNESS + " = '" + sickness +"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            return false;
        }
        cursor.close();
        return true;
    }
    public void addPatientDoctor(String patientId,String doctorId){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PATIENT, patientId);
        values.put(COLUMN_DOCTOR,doctorId);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_PATIENTDOCTOR, null, values);
    }
    public List<Doctor> getDoctorPatient(String patient){
        String query = "Select * FROM "  + TABLE_PATIENTDOCTOR + " WHERE " + COLUMN_PATIENT + " = '" + patient +"' ";
        SQLiteDatabase db = this.getWritableDatabase();
        List<Doctor> doctorList=new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            doctorList.add(this.findDoctor(cursor.getString(0)));
            while(cursor.moveToNext()) {
                doctorList.add(this.findDoctor(cursor.getString(0)));
            }
        }
        cursor.close();
        return doctorList;
    }
    public List<Patient> getPatientDoctor(String doctor){
        String query = "Select * FROM "  + TABLE_PATIENTDOCTOR + " WHERE " + COLUMN_DOCTOR + " = '" + doctor +"' ";
        SQLiteDatabase db = this.getWritableDatabase();
        List<Patient> patientList=new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            patientList.add(this.findPatient(cursor.getString(1)));
            while(cursor.moveToNext()) {
                patientList.add(this.findPatient(cursor.getString(1)));
            }
        }
        cursor.close();
        return patientList;
    }
    public Boolean checkPatientDoctor(String patient,String doctor){
        String query = "Select * FROM "  + TABLE_PATIENTDOCTOR
                + " WHERE " + COLUMN_PATIENT + " = '" + patient +"' and "
                + COLUMN_DOCTOR + " = '" + doctor +"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            return false;
        }
        cursor.close();
        return true;
    }
    public void addPatientMedicine(String patientId,String medicineName){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PATIENT, patientId);
        values.put(COLUMN_MEDICINE,medicineName);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_PATIENTMEDICINE, null, values);
    }
    public List<String> getPatientMedicine(String patient){
        String query = "Select * FROM "  + TABLE_PATIENTMEDICINE + " WHERE " + COLUMN_PATIENT + " = '" + patient +"' ";
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> sicknessList=new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            sicknessList.add(cursor.getString(0));
            while(cursor.moveToNext()) {
                sicknessList.add(cursor.getString(0));
            }
        }
        cursor.close();
        return sicknessList;
    }
    public Boolean checkPatientMedicine(String patient,String medicine){
        String query = "Select * FROM "  + TABLE_PATIENTMEDICINE
                + " WHERE " + COLUMN_PATIENT + " = '" + patient +"' and "
                + COLUMN_MEDICINE + " = '" + medicine+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            return false;
        }
        cursor.close();
        return true;
    }
    public List<Prescription> getRecievedPrescriptions(String pharmecy){
        String query = "Select * FROM "  + TABLE_RECIVEDPRESCRIPTIONSPHARMACY + " WHERE " + COLUMN_PHARMECY + " = '" + pharmecy +"' ";
        SQLiteDatabase db = this.getWritableDatabase();
        List<Prescription> prescriptionsList=new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            prescriptionsList.add(this.findPrescription(cursor.getString(0)));
            while(cursor.moveToNext()) {
                prescriptionsList.add(this.findPrescription(cursor.getString(0)));
            }
        }
        cursor.close();
        return prescriptionsList;
    }
    public void addSickness(Sickness sickness){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, sickness.name);
        values.put(COLUMN_SYMPTOMS, sickness.symptoms);
        values.put(COLUMN_TYPE, sickness.type);
        values.put(COLUMN_DESCRIPTION, sickness.description);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_SICKNESS, null, values);
    }
    public List<Sickness> getAllSicknesses(){
        String query = "Select * FROM " + TABLE_SICKNESS ;
        SQLiteDatabase db = this.getWritableDatabase();
        Sickness mpatient = null;
        List<Sickness> insuranceList=new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            int id = Integer.parseInt(cursor.getString(0));
            mpatient = new Sickness(id);
            mpatient.name = cursor.getString(1);
            mpatient.symptoms = cursor.getString(2);
            mpatient.type = cursor.getString(3);
            mpatient.description = cursor.getString(4);
            insuranceList.add(mpatient);
            while(cursor.moveToNext()) {
                id = Integer.parseInt(cursor.getString(0));
                mpatient = new Sickness(id);
                mpatient.name = cursor.getString(1);
                mpatient.symptoms = cursor.getString(2);
                mpatient.type = cursor.getString(3);
                mpatient.description = cursor.getString(4);
                insuranceList.add(mpatient);
            }
        }
        cursor.close();
        return insuranceList;
    }
    public List<Prescription> getAllPrescriptionsInsurance(String insurance){
        String query = "Select "+COLUMN_USERNAME +" FROM "  + TABLE_PATIENT+ " WHERE " + COLUMN_INSURENCE + " = '" + insurance +"' " ;
        SQLiteDatabase db = this.getWritableDatabase();
        List<Prescription> prescriptionList=new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            prescriptionList.addAll(this.getAllPrescription(cursor.getString(0)));
            while(cursor.moveToNext()) {
                prescriptionList.addAll(this.getAllPrescription(cursor.getString(0)));
            }
        }
        cursor.close();
        return prescriptionList;
    }
}