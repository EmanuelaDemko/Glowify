package com.example.project.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="appointments")
public class Appointment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    private Client client;

    @ManyToOne(optional=false)
    private Staff staff;

    @ManyToOne(optional=false)
    private ServiceItem serviceItem;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String notes;

    public enum Status {
        PENDING, CONFIRMED, COMPLETED, CANCELLED
    }

    // getters/setters
    // ...
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public Client getClient(){return client;}
    public void setClient(Client client){this.client=client;}
    public Staff getStaff(){return staff;}
    public void setStaff(Staff staff){this.staff=staff;}
    public ServiceItem getServiceItem(){return serviceItem;}
    public void setServiceItem(ServiceItem serviceItem){this.serviceItem=serviceItem;}
    public LocalDateTime getStartTime(){return startTime;}
    public void setStartTime(LocalDateTime startTime){this.startTime=startTime;}
    public LocalDateTime getEndTime(){return endTime;}
    public void setEndTime(LocalDateTime endTime){this.endTime=endTime;}
    public Status getStatus(){return status;}
    public void setStatus(Status status){this.status=status;}
    public String getNotes(){return notes;}
    public void setNotes(String notes){this.notes=notes;}
}
