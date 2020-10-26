package com.haulmont.testtask.repository.recipe;

import com.haulmont.testtask.domain.Recipe;
import com.haulmont.testtask.domain.Recipe;
import com.haulmont.testtask.util.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class RecipeRepositoryImpl implements RecipeRepository {

    @Override
    public void save(Recipe obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(obj);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Recipe obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.remove(obj);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Recipe obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(obj);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Recipe getById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Recipe Recipe = session.find(Recipe.class, id);
        session.getTransaction().commit();
        session.close();
        return Recipe;
    }

    @Override
    public List<Recipe> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        TypedQuery<Recipe> resultList = session.createQuery("SELECT a FROM Recipe a", Recipe.class);
        List<Recipe> recipes = resultList.getResultList();
        session.getTransaction().commit();
        session.close();
        return recipes;
    }
}
