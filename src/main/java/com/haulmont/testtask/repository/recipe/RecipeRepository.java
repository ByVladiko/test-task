package com.haulmont.testtask.repository.recipe;

import com.haulmont.testtask.domain.Patient;
import com.haulmont.testtask.domain.Priority;
import com.haulmont.testtask.domain.Recipe;
import com.haulmont.testtask.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe> {

    List<Recipe> getAll(Patient patient, Priority priority, String description);

}
