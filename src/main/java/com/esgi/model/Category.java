package com.esgi.model;


import org.springframework.data.repository.query.Param;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Arnaud Flaesch on 28/04/2016.
 */
@Entity
@Table(name = "category")
public class Category {
    private Long idcategory;
    private String nameCategory;

    public Category() {}

    public Category(Long idcategory) {
        this.idcategory = idcategory;
    }

    @Id
    @GeneratedValue
    @Column(name = "idcategory")
    public Long getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(Long idcategory) {
        this.idcategory = idcategory;
    }

    @Column(name = "name_category")
    @NotNull
    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
}