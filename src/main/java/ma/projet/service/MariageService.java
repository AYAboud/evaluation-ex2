package ma.projet.service;

import ma.projet.beans.Mariage;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;
import ma.projet.beans.Homme;

public class MariageService implements IDao<Mariage> {

    @Override
    public void save(Mariage o) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        s.persist(o);
        t.commit();
        s.close();
    }

    @Override
    public void update(Mariage o) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        s.merge(o);
        t.commit();
        s.close();
    }

    @Override
    public void delete(Mariage o) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        s.remove(o);
        t.commit();
        s.close();
    }

    @Override
    public Mariage getById(int id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Mariage m = s.get(Mariage.class, id);
        s.close();
        return m;
    }

    @Override
    public List<Mariage> getAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Mariage> mariages = s.createQuery("FROM Mariage", Mariage.class).list();
        s.close();
        return mariages;
    }

    // 🔍 Nombre d’hommes mariés à quatre femmes entre deux dates (API Criteria)
    public List<Mariage> getMariagesEnCours(int hommeId) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Mariage> list = s.createQuery("FROM Mariage m WHERE m.homme.id = :id AND m.dateFin IS NULL", Mariage.class)
                .setParameter("id", hommeId)
                .list();
        s.close();
        return list;
    }

    public List<Mariage> getMariagesEchoues(int hommeId) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Mariage> list = s.createQuery("FROM Mariage m WHERE m.homme.id = :id AND m.dateFin IS NOT NULL", Mariage.class)
                .setParameter("id", hommeId)
                .list();
        s.close();
        return list;
    }
    public List<Mariage> getByHomme(int idHomme) {
        Transaction transaction = null;
        List<Mariage> mariages = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            mariages = session.createQuery(
                            "from Mariage m where m.homme.id = :idHomme", Mariage.class)
                    .setParameter("idHomme", idHomme)
                    .list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }

        return mariages;
    }
}
