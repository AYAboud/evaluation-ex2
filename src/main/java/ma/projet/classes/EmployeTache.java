package ma.projet.classes;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class EmployeTache implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private Date dateDebutReelle;
    @Temporal(TemporalType.DATE)
    private Date dateFinReelle;

    @ManyToOne
    private Employe employe;

    @ManyToOne
    private Tache tache;

    // Getters & Setters
}
