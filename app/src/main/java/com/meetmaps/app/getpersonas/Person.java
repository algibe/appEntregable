package com.meetmaps.app.getpersonas;

import java.io.Serializable;

public class Person implements Serializable{

    private String idPerson;
    private String username;
    private String position;
    private String company;

    public Person(String idPerson, String username, String position, String company){

        this.idPerson =idPerson;
        this.username =username;
        this.position = position;
        this.company = company;

    }

    public String getUsername(){
        return this.username;
    }

    public String getPosition(){
        return this.position;
    }

    public String getCompany(){
        return this.company;
    }

    public String getIdPerson(){
        return this.idPerson;
    }



}
