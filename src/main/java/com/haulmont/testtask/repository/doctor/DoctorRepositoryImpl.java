package com.haulmont.testtask.repository.doctor;

import com.haulmont.testtask.domain.Doctor;
import com.haulmont.testtask.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public Map<Doctor, Long> getStatistic() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String hqlText =
                "SELECT doc, count(rec) " +
                        "from Doctor doc " +
                        "left join Recipe rec " +
                        "on rec.doctor.id = doc.id " +
                        "GROUP BY(doc)";

        Map<Doctor, Long> result = session
                .createQuery(hqlText, Tuple.class)
                .getResultStream()
                .collect(Collectors.toMap(row -> (Doctor) row.get(0), row -> (Long) row.get(1)));

        session.getTransaction().commit();
        session.close();

        return result;
    }
}
