package com.example.pro;

public class Sickness {
    int id;
    String name;
    String symptoms;
    String type;
    String description;

    public Sickness(){

    }
    public Sickness(int id){
        this.id=id;
    }
    public Sickness(String name,String symptoms,String type,String description){
        this.name=name;
        this.symptoms=symptoms;
        this.type=type;
        this.description=description;

    }
}
