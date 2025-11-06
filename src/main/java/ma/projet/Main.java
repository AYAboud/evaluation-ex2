package ma.projet;

import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.service.ProjetService;
import ma.projet.service.TacheService;

import java.text.SimpleDateFormat;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProjetService projetService = new ProjetService();
        TacheService tacheService = new TacheService();

        // 🟢 ID du projet à afficher
        int projetId = 4;

        // 🔹 Récupération du projet
        Projet projet = projetService.findById(projetId);

        if (projet == null) {
            System.out.println("Aucun projet trouvé avec l'ID " + projetId);
            return;
        }

        SimpleDateFormat sdfAffiche = new SimpleDateFormat("dd MMMM yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Projet : " + projet.getId() +
                "      Nom : " + projet.getNom() +
                "     Date début : " + sdfAffiche.format(projet.getDateDebut()));

        System.out.println("Liste des tâches:");
        System.out.printf("%-5s %-15s %-20s %-20s%n", "Num", "Nom", "Date Début Réelle", "Date Fin Réelle");

        for (Tache t : projet.getTaches()) {
            String debut = (t.getDateDebutReelle() != null) ? sdf.format(t.getDateDebutReelle()) : "--";
            String fin = (t.getDateFinReelle() != null) ? sdf.format(t.getDateFinReelle()) : "--";

            System.out.printf("%-5d %-15s %-20s %-20s%n", t.getId(), t.getNom(), debut, fin);
        }

    }
}
