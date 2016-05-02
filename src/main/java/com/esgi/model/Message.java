package com.esgi.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Arnaud Flaesch on 02/05/2016.
 */
@Entity
@Table(name = "message")
public class Message {

    private Long idmessage;
    private Date date;
    private String content;

    @Id
    @GeneratedValue
    @Column(name = "idmessage")
    public Long getIdmessage() {
        return(idmessage);
    }

    public void setIdmessage(Long idmessage) {
        this.idmessage = idmessage;
    }

    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private User sender;

    @OneToOne
    @JoinColumn(name = "sender")
    public User getSender() {
        return(sender);
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    private User receiver;

    @OneToOne
    @JoinColumn(name = "receiver")
    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
}
