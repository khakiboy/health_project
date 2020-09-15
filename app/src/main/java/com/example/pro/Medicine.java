package com.example.pro;

import java.util.List;

public class Medicine {
    int id;
    String name;
    String description;
    List<Medicine> drugInteractionList;

    public Medicine(int id) {
        this.id=id;
    }
    public Medicine(String name, String description) {
        this.description=description;
        this.name=name;
    }
}
