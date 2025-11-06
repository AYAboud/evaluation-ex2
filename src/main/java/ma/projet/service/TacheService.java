package ma.projet.service;

import ma.projet.classes.Tache;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class TacheService implements IDao<Tache> {

    @Override
    public void create(Tache o) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(o);
            tx.commit();
        }
    }

    @Override
    public void update(Tache o) { /* idem */ }

    @Override
    public void delete(Tache o) { /* idem */ }

    @Override
    public Tache findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Tache.class, id);
        }
    }

    @Override
    public List<Tache> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Tache", Tache.class).list();
        }
    }
}
