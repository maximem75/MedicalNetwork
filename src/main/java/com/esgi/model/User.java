package com.esgi.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Arnaud Flaesch on 28/04/2016.
 */
@Entity
@Table(name = "user")
public class User implements Serializable {
    private Long iduser;
    private String login;
    private String password;
    private String name;
    private String firstname;
    private Date birthday;
    private String phone;
    private String email;
    private String token;
    private Date tokenExpirationDate;

    private Category category;

    public User() {}

    public User(Long iduser) {
        this.iduser = iduser;
    }

    @Id
    @GeneratedValue
    @Column(name = "iduser")
    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    @Column(name = "login")
    @NotNull
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "password")
    @NotNull
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "name")
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "firstname")
    @NotNull
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Column(name = "birthday")
    @NotNull
    public Date getBirthday() {
        return birthday;
    }

    @JsonSerialize(using=DateSerializer.class)
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Column(name = "phone")
    @NotNull
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "email")
    @NotNull
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Column(name = "token_expiration_date")
    public Date getTokenExpirationDate() {
        return tokenExpirationDate;
    }

    public void setTokenExpirationDate(Date tokenExpirationDate) {
        this.tokenExpirationDate = tokenExpirationDate;
    }

    @ManyToOne
    @JoinColumn(name = "idcategory")
    @NotNull
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
