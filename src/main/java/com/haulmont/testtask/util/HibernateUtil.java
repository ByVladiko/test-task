package com.haulmont.testtask.util;

import com.haulmont.testtask.domain.Doctor;
import com.haulmont.testtask.domain.Patient;
import com.haulmont.testtask.domain.Priority;
import com.haulmont.testtask.domain.Recipe;
import com.haulmont.testtask.repository.doctor.DoctorRepository;
import com.haulmont.testtask.repository.doctor.DoctorRepositoryImpl;
import com.haulmont.testtask.repository.patient.PatientRepository;
import com.haulmont.testtask.repository.patient.PatientRepositoryImpl;
import com.haulmont.testtask.repository.recipe.RecipeRepository;
import com.haulmont.testtask.repository.recipe.RecipeRepositoryImpl;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDate;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    private synchronized static void setUp() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

//            DoctorRepository doctorRepository = new DoctorRepositoryImpl();
//            Doctor doctor = new Doctor("Nicolaus", "Fabiulos", "Serbian", "Traumatolog");
//            doctorRepository.save(doctor);
//            doctorRepository.save(new Doctor("Galileo", "Galilei", "Shelter", "Stomatolog"));
//            doctorRepository.save(new Doctor("Johannes", "Kepler", "Tailer", "Pediator"));
//
//            PatientRepository patientRepository = new PatientRepositoryImpl();
//            Patient patient1 = new Patient("Krambi", "Kreker", "Sugarovich", "+79277209637");
//            patientRepository.save(patient1);
//            Patient patient2 = new Patient("Selvestor", "Sharm", "Fisical", "+7934555555");
//            patientRepository.save(patient2);
//
//            RecipeRepository recipeRepository = new RecipeRepositoryImpl();
//            Recipe recipe = new Recipe("Dont forget about your soul", patient1, doctor, LocalDate.of(2020, 3, 25), Priority.Default);
//            recipeRepository.save(recipe);
//            Recipe recipe1 = new Recipe("Come out", patient1, doctor, LocalDate.of(2020, 3, 25), Priority.Default);
//            recipeRepository.save(recipe1);
//            Recipe recipe2 = new Recipe("Wake up early", patient1, doctor, LocalDate.of(2020, 3, 25), Priority.Default);
//            recipeRepository.save(recipe2);
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public synchronized static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            setUp();
        }
        return sessionFactory;
    }
}
