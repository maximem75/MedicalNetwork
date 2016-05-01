package com.esgi.repositories;

import com.esgi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Arnaud on 30/04/2016.
 */
@Repository
public interface CategoryRepository extends JpaRepository <Category, Long>{

    @Query("FROM Category WHERE name_category LIKE %:research%")
    List<Category> getCategoriesByResearch(@Param("research") String research);
}
