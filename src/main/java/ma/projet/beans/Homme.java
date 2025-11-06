package ma.projet.beans;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Homme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    // 🔹 Constructeur vide obligatoire pour Hibernate
    public Homme() {}

    // 🔹 Constructeur pratique pour créer des objets
    public Homme(String nom, String prenom, Date dateNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
    }

    // 🔹 Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public Date getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(Date dateNaissance) { this.dateNaissance = dateNaissance; }

    @Override
    public String toString() {
        return nom + " " + prenom + " (" + dateNaissance + ")";
    }
}
