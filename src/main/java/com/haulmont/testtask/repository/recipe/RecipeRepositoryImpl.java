package com.haulmont.testtask.repository.recipe;

import com.haulmont.testtask.domain.Patient;
import com.haulmont.testtask.domain.Priority;
import com.haulmont.testtask.domain.Recipe;
import com.haulmont.testtask.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class RecipeRepositoryImpl implements RecipeRepository {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void save(Recipe obj) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(obj);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Recipe obj) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(obj);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Recipe obj) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(obj);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Recipe getById(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Recipe Recipe = session.find(Recipe.class, id);
        session.getTransaction().commit();
        session.close();
        return Recipe;
    }

    @Override
    public List<Recipe> getAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        TypedQuery<Recipe> resultList = session.createQuery("SELECT a FROM Recipe a", Recipe.class);

        List<Recipe> recipes = resultList.getResultList();

        session.getTransaction().commit();
        session.close();

        return recipes;
    }

    @Override
    public List<Recipe> getAll(Patient patient, Priority priority, String description) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Recipe> query = builder.createQuery(Recipe.class);
        Root<Recipe> root = query.from(Recipe.class);

        query.select(root);

        List<Predicate> predicateList = new ArrayList<>();
        if(patient != null) predicateList.add(builder.equal(root.get("patient"), patient));
        if(priority != null) predicateList.add(builder.equal(root.get("priority"), priority));
        if(!description.isEmpty()) predicateList.add(builder.like(builder.lower(root.get("description")),
                builder.lower(builder.literal("%"+ description +"%"))));

        query.where(predicateList.toArray(new Predicate[0]));

        List<Recipe> recipes = session.createQuery(query).getResultList();

        session.getTransaction().commit();
        session.close();

        return recipes;
    }
}
