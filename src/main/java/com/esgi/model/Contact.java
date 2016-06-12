package com.esgi.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Arnaud on 30/04/2016.
 */
@Entity
@Table(name = "contact")
public class Contact {

    private Long idrelation;
    private boolean accepted;
    private String message;
    private User iduser;
    private User idcontact;

    public Contact() {}

    public Contact(User idcontact, String message) {
        this.idcontact = idcontact;
        this.message = message;
    }

    @Id
    @Column(name = "idrelation")
    public Long getIdrelation() {
        return idrelation;
    }

    public void setIdrelation(Long idrelation) {
        this.idrelation = idrelation;
    }

    @Column(name = "accepted")
    @NotNull
    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @OneToOne
    @JoinColumn(name = "iduser")
    @NotNull
    public User getIduser() {
        return iduser;
    }

    public void setIduser(User iduser) {
        this.iduser = iduser;
    }

    @OneToOne
    @JoinColumn(name = "idcontact")
    @NotNull
    public User getIdcontact() {
        return idcontact;
    }

    public void setIdcontact(User idcontact) {
        this.idcontact = idcontact;
    }
}