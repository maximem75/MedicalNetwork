package com.esgi.model;

import javax.persistence.*;

/**
 * Created by Arnaud Flaesch on 28/04/2016.
 */
@Entity
@Table(name = "category")
public class Category {
    private int idcategory;
    private String nameCategory;

    @Id
    @GeneratedValue
    @Column(name = "idcategory")
    public int getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(int idcategory) {
        this.idcategory = idcategory;
    }

    @Column(name = "name_category")
    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
}
