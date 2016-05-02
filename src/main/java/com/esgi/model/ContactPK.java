package com.esgi.model;

import java.io.Serializable;

/**
 * Created by Arnaud Flaesch on 02/05/2016.
 */
public class ContactPK implements Serializable {

    private Long iduser;
    private Long idcontact;

    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    public Long getIdcontact() {
        return idcontact;
    }

    public void setIdcontact(Long idcontact) {
        this.idcontact = idcontact;
    }
}
