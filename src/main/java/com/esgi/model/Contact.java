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
@IdClass(ContactPK.class)
public class Contact {

    private Long iduser;
    private Long idcontact;
    private boolean accepted;
    private String message;

    public Contact() {}

    public Contact(Long idcontact, String message) {
        this.idcontact = idcontact;
        this.message = message;
    }

    @Id
    @Column(name = "iduser")
    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    @Id
    @Column(name = "idcontact")
    public Long getIdcontact() {
        return idcontact;
    }

    public void setIdcontact(Long idcontact) {
        this.idcontact = idcontact;
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
}