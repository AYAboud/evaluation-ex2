package ma.projet.beans;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Mariage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    private Date dateFin;

    private int nbrEnfants;

    @ManyToOne
    private Homme homme;

    @ManyToOne
    private Femme femme;

    // 🔹 Constructeur vide obligatoire pour Hibernate
    public Mariage() {}

    // 🔹 Constructeur pratique avec tous les paramètres
    public Mariage(Homme homme, Femme femme, Date dateDebut, Date dateFin, int nbrEnfants) {
        this.homme = homme;
        this.femme = femme;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbrEnfants = nbrEnfants;
    }

    // 🔹 Getters & Setters
    public int getId() { return id; }

    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }

    public Date getDateFin() { return dateFin; }
    public void setDateFin(Date dateFin) { this.dateFin = dateFin; }

    public int getNbrEnfants() { return nbrEnfants; }
    public void setNbrEnfants(int nbrEnfants) { this.nbrEnfants = nbrEnfants; }

    public Homme getHomme() { return homme; }
    public void setHomme(Homme homme) { this.homme = homme; }

    public Femme getFemme() { return femme; }
    public void setFemme(Femme femme) { this.femme = femme; }

    @Override
    public String toString() {
        return "Mariage{" +
                "homme=" + homme.getNom() +
                ", femme=" + femme.getNom() +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", nbrEnfants=" + nbrEnfants +
                '}';
    }
}
