package ma.projet.test;

import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class TestHibernate {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();




        System.out.println("✅ Connexion Hibernate réussie !");
        session.getTransaction().commit();
        session.close();
    }
}
