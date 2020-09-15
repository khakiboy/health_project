package com.example.pro;

import java.util.Date;
import java.util.List;

public class Patient {
    int id;
    String firstName;
    String lastName;
    String gender;
    String birthDate;
    String emailAdress;
    String username;
    String password;
    String registerDate;
    String phoneNumber;
    String address;
    String  insurance;
    List<VisitRequest> visitRequestList;
    List<Prescription> prescriptionList;

    public Patient(int id) {
        this.id=id;
    }

    public Patient() {

    }
}
