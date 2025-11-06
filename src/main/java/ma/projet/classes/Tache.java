package ma.projet.classes;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Tache implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    private Date dateFin;

    // 🟢 Ajoute ces deux attributs :
    @Temporal(TemporalType.DATE)
    private Date dateDebutReelle;

    @Temporal(TemporalType.DATE)
    private Date dateFinReelle;

    private double prix;

    @ManyToOne
    @JoinColumn(name = "projet_id")
    private Projet projet;

    @OneToMany(mappedBy = "tache")
    private List<EmployeTache> employeTaches;

    // --- Getters & Setters ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }

    public Date getDateFin() { return dateFin; }
    public void setDateFin(Date dateFin) { this.dateFin = dateFin; }

    public Date getDateDebutReelle() { return dateDebutReelle; }
    public void setDateDebutReelle(Date dateDebutReelle) { this.dateDebutReelle = dateDebutReelle; }

    public Date getDateFinReelle() { return dateFinReelle; }
    public void setDateFinReelle(Date dateFinReelle) { this.dateFinReelle = dateFinReelle; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public Projet getProjet() { return projet; }
    public void setProjet(Projet projet) { this.projet = projet; }

    public List<EmployeTache> getEmployeTaches() { return employeTaches; }
    public void setEmployeTaches(List<EmployeTache> employeTaches) { this.employeTaches = employeTaches; }
}
