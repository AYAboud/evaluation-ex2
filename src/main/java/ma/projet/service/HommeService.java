package ma.projet.service;

import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class HommeService implements IDao<Homme> {

    @Override
    public void save(Homme o) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        s.persist(o);
        t.commit();
        s.close();
    }

    @Override
    public void update(Homme o) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        s.merge(o);
        t.commit();
        s.close();
    }

    @Override
    public void delete(Homme o) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        s.remove(o);
        t.commit();
        s.close();
    }

    @Override
    public Homme getById(int id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Homme h = s.get(Homme.class, id);
        s.close();
        return h;
    }

    @Override
    public List<Homme> getAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Homme> hommes = s.createQuery("FROM Homme", Homme.class).list();
        s.close();
        return hommes;
    }


    // ... autres méthodes CRUD

    public List<Mariage> getEpousesEntreDeuxDates(int hommeId, Date d1, Date d2) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Mariage> mariages = s.createQuery(
                        "FROM Mariage m WHERE m.homme.id = :id AND m.dateDebut BETWEEN :d1 AND :d2",
                        Mariage.class)
                .setParameter("id", hommeId)
                .setParameter("d1", d1)
                .setParameter("d2", d2)
                .list();
        s.close();
        return mariages;
    }
}
