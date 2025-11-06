package ma.projet.service;

import ma.projet.classes.Projet;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProjetService implements IDao<Projet> {

    @Override
    public void create(Projet o) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(o);
            tx.commit();
        }
    }

    @Override
    public void update(Projet o) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(o);
            tx.commit();
        }
    }

    @Override
    public void delete(Projet o) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.remove(o);
            tx.commit();
        }
    }

    @Override
    public Projet findById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Projet projet = session.get(Projet.class, id);

        if (projet != null) {
            // 🔹 Charger explicitement la liste des tâches avant de fermer la session
            projet.getTaches().size();
        }

        session.close();
        return projet;
    }


    @Override
    public List<Projet> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Projet", Projet.class).list();
        }
    }
}
