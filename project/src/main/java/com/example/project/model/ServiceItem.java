package com.example.project.model;


import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="services")
public class ServiceItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String title;

    @Column(length=1000)
    private String description;

    @Column(nullable=false)
    private BigDecimal price;

    private Integer durationMinutes;


    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public String getTitle(){return title;}
    public void setTitle(String title){this.title=title;}
    public String getDescription(){return description;}
    public void setDescription(String description){this.description=description;}
    public BigDecimal getPrice(){return price;}
    public void setPrice(BigDecimal price){this.price=price;}
    public Integer getDurationMinutes(){return durationMinutes;}
    public void setDurationMinutes(Integer durationMinutes){this.durationMinutes=durationMinutes;}
}
