package ma.projet.service;

import ma.projet.classes.*;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeService implements IDao<Employe> {

    @Override
    public void create(Employe o) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(o); // Hibernate 6 préfère persist()
            tx.commit();
        }
    }

    @Override
    public void update(Employe o) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(o);
            tx.commit();
        }
    }

    @Override
    public void delete(Employe o) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.remove(o);
            tx.commit();
        }
    }

    @Override
    public Employe findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Employe.class, id);
        }
    }

    @Override
    public List<Employe> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Employe", Employe.class).list();
        }
    }

    public List<Tache> getTachesRealisees(int employeId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "select et.tache from EmployeTache et where et.employe.id = :id",
                            Tache.class)
                    .setParameter("id", employeId)
                    .list();
        }
    }

    public List<Projet> getProjetsGeres(int employeId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "from Projet where chef.id = :id", Projet.class)
                    .setParameter("id", employeId)
                    .list();
        }
    }
}
