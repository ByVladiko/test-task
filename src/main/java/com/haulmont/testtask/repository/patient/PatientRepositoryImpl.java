package com.haulmont.testtask.repository.patient;

import com.haulmont.testtask.domain.Patient;
import com.haulmont.testtask.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.TypedQuery;
import java.util.List;

public class PatientRepositoryImpl implements PatientRepository {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    
    @Override
    public void save(Patient obj) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(obj);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Patient obj) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(obj);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Patient obj) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(obj);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Patient getById(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Patient Patient = session.find(Patient.class, id);
        session.getTransaction().commit();
        session.close();
        return Patient;
    }

    @Override
    public List<Patient> getAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        TypedQuery<Patient> resultList = session.createQuery("SELECT a FROM Patient a", Patient.class);
        List<Patient> patients = resultList.getResultList();
        session.getTransaction().commit();
        session.close();
        return patients;
    }
}
