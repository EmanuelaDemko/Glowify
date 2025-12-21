package com.example.project.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="clients")
public class Client extends User {
    private LocalDate birthDate;
    private String address;

    public LocalDate getBirthDate(){return birthDate;}
    public void setBirthDate(LocalDate birthDate){this.birthDate=birthDate;}
    public String getAddress(){return address;}
    public void setAddress(String address){this.address=address;}
}

