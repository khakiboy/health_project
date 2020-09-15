package com.example.pro;

import java.util.List;

public class Doctor {
    int id;
    String firstName;
    String lastName;
    String takhasos;
    String birthDate;
    String phoneNumber;
    String gender;
    String address;
    String emailAdress;
    String username;
    String password;
    String registerDate;
    String officePhoneNumber;
    String officeAddress;
    List<VisitRequest> visitRequestList;
    List<Prescription> prescriptionList;

    public Doctor(int id) {
        this.id=id;
    }

    public Doctor() {

    }
}
