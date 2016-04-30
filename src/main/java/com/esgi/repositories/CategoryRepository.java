package com.esgi.repositories;

import com.esgi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Arnaud on 30/04/2016.
 */
@Repository
public interface CategoryRepository extends JpaRepository <Category, Long>{

}
