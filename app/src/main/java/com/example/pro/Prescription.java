package com.example.pro;

import java.util.List;

public class Prescription {
    int id;
    String patient;
    String doctor;
    String description;
    String date;
    String sickness;
    String uniqueId;
    List<String> medicineList;
    List<String> pharmacyList;

    public Prescription(int id) {
        this.id=id;
    }

    public Prescription() {

    }
}
