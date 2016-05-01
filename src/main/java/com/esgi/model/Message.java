package com.esgi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Arnaud on 30/04/2016.
 */
@Entity
@Table(name = "message")
public class Message {
/*
    private Long sender;
    private Long receiver;
    private Date date;
    private String content;

    @Id
    @Column(name = "sender")
    public Long getSender() {
        return(sender);
    }

    public void setSender(Long sender) {
        this.sender = sender;
    }

    @Id
    @Column(name = "receiver")
    public Long getReceiver() {
        return receiver;
    }

    public void setReceiver(Long receiver) {
        this.receiver = receiver;
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
    */
}
