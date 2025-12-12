package com.glowify.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="notifications")
public class Notification {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String channel; // EMAIL | SMS | INTERNAL

    private String recipient; // email or phone or userId

    private String message;

    private LocalDateTime sendAt;

    private boolean sent;

    // getters/setters
    // ...
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public String getChannel(){return channel;}
    public void setChannel(String channel){this.channel=channel;}
    public String getRecipient(){return recipient;}
    public void setRecipient(String recipient){this.recipient=recipient;}
    public String getMessage(){return message;}
    public void setMessage(String message){this.message=message;}
    public LocalDateTime getSendAt(){return sendAt;}
    public void setSendAt(LocalDateTime sendAt){this.sendAt=sendAt;}
    public boolean isSent(){return sent;}
    public void setSent(boolean sent){this.sent=sent;}
}
