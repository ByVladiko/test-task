package com.haulmont.testtask.repository.patient;

import com.haulmont.testtask.domain.Patient;
import com.haulmont.testtask.domain.Patient;
import com.haulmont.testtask.util.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class PatientRepositoryImpl implements PatientRepository {

    @Override
    public void save(Patient obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(obj);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Patient obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.remove(obj);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Patient obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(obj);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Patient getById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Patient Patient = session.find(Patient.class, id);
        session.getTransaction().commit();
        session.close();
        return Patient;
    }

    @Override
    public List<Patient> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        TypedQuery<Patient> resultList = session.createQuery("SELECT a FROM Patient a", Patient.class);
        session.getTransaction().commit();
        session.close();
        return resultList.getResultList();
    }
}
