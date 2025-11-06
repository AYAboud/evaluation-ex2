package ma.projet.service;

import ma.projet.beans.Femme;
import ma.projet.beans.Mariage;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class FemmeService implements IDao<Femme> {

    @Override
    public void save(Femme o) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        s.persist(o);
        t.commit();
        s.close();
    }

    @Override
    public void update(Femme o) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        s.merge(o);
        t.commit();
        s.close();
    }

    @Override
    public void delete(Femme o) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        s.remove(o);
        t.commit();
        s.close();
    }

    @Override
    public Femme getById(int id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Femme f = s.get(Femme.class, id);
        s.close();
        return f;
    }

    @Override
    public List<Femme> getAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Femme> femmes = s.createQuery("FROM Femme", Femme.class).list();
        s.close();
        return femmes;
    }

    // 🧮 Méthode native : nombre d’enfants d’une femme entre deux dates
    public int getNombreEnfantsEntreDeuxDates(int femmeId, Date d1, Date d2) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Object result = s.createNativeQuery(
                        "SELECT SUM(nbrEnfant) FROM Mariage WHERE femme_id = :id AND dateDebut BETWEEN :d1 AND :d2")
                .setParameter("id", femmeId)
                .setParameter("d1", d1)
                .setParameter("d2", d2)
                .getSingleResult();
        s.close();
        return result != null ? ((Number) result).intValue() : 0;
    }

    // 👰 Femmes mariées au moins deux fois
    public List<Femme> getFemmesMarieesDeuxFoisOuPlus() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Femme> femmes = s.createQuery(
                "SELECT f FROM Femme f JOIN f.mariages m GROUP BY f HAVING COUNT(m) >= 2",
                Femme.class
        ).list();
        s.close();
        return femmes;
    }
    public Femme getFemmePlusAgee() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Femme f = s.createQuery("FROM Femme ORDER BY dateNaissance ASC", Femme.class)
                .setMaxResults(1)
                .uniqueResult();
        s.close();
        return f;
    }
}
