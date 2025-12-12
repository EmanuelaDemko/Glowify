package com.glowify.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="medical_records")
public class MedicalRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    private Client client;

    @ManyToOne
    private Staff staff; // who recorded

    private LocalDateTime createdAt;

    @Column(length=4000)
    private String description;

    // getters/setters
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public Client getClient(){return client;}
    public void setClient(Client client){this.client=client;}
    public Staff getStaff(){return staff;}
    public void setStaff(Staff staff){this.staff=staff;}
    public LocalDateTime getCreatedAt(){return createdAt;}
    public void setCreatedAt(LocalDateTime createdAt){this.createdAt=createdAt;}
    public String getDescription(){return description;}
    public void setDescription(String description){this.description=description;}
}
