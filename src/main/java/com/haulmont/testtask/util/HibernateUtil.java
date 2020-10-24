package com.haulmont.testtask.util;

import com.haulmont.testtask.domain.Doctor;
import com.haulmont.testtask.repository.doctor.DoctorRepository;
import com.haulmont.testtask.repository.doctor.DoctorRepositoryImpl;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    private synchronized static void setUp() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

            DoctorRepository doctorRepository = new DoctorRepositoryImpl();
            doctorRepository.save(new Doctor("Nicolaus", "Fabiulos", "Serbian", "Traumatolog"));
            doctorRepository.save(new Doctor("Galileo", "Galilei", "Shelter", "Stomatolog"));
            doctorRepository.save(new Doctor("Johannes", "Kepler", "Tailer", "Pediator"));
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
