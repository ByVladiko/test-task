package com.haulmont.testtask.repository.doctor;

import com.haulmont.testtask.domain.Doctor;
import com.haulmont.testtask.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.*;
import java.util.List;

public class DoctorRepositoryImpl implements DoctorRepository {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void save(Doctor obj) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(obj);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Doctor obj) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(obj);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Doctor obj) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(obj);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Doctor getById(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Doctor doctor = session.find(Doctor.class, id);
        session.getTransaction().commit();
        session.close();
        return doctor;
    }

    @Override
    public List<Doctor> getAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        TypedQuery<Doctor> resultList = session.createQuery("SELECT a FROM Doctor a", Doctor.class);
        List<Doctor> doctors = resultList.getResultList();
        session.getTransaction().commit();
        session.close();
        return doctors;
    }
}
