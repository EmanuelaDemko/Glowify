package com.example.project.model;


import jakarta.persistence.*;

@Entity
@Table(name="staff")
public class Staff extends User {
    private String position;
    private String specialty;

    public String getPosition(){return position;}
    public void setPosition(String position){this.position=position;}
    public String getSpecialty(){return specialty;}
    public void setSpecialty(String specialty){this.specialty=specialty;}
}
