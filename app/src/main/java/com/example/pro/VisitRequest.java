package com.example.pro;

import java.util.Date;

public class VisitRequest {
    int id;
    String doctor;
    String  patient;
    String symptoms;
    String allergies;
    String date;
    String time;
    String type;

    public VisitRequest(int id) {
        this.id=id;
    }
    public VisitRequest() {

    }
}
