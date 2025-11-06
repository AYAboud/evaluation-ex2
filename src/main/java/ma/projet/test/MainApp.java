package ma.projet.test;

import ma.projet.beans.Femme;
import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.service.FemmeService;
import ma.projet.service.HommeService;
import ma.projet.service.MariageService;

import java.text.SimpleDateFormat;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws Exception {

        HommeService hommeService = new HommeService();
        FemmeService femmeService = new FemmeService();
        MariageService mariageService = new MariageService();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // 🔹 Création de 5 hommes
        Homme h1 = new Homme("SAFI", "SAID", sdf.parse("03/09/1960"));
        Homme h2 = new Homme("RAHIM", "OMAR", sdf.parse("11/11/1965"));
        Homme h3 = new Homme("KHALID", "BENALI", sdf.parse("04/01/1970"));
        Homme h4 = new Homme("SALAH", "YASSINE", sdf.parse("05/02/1980"));
        Homme h5 = new Homme("ADIL", "HAMDI", sdf.parse("02/03/1985"));

        hommeService.save(h1);
        hommeService.save(h2);
        hommeService.save(h3);
        hommeService.save(h4);
        hommeService.save(h5);

        // 🔹 Création de 10 femmes
        Femme f1 = new Femme("SALIMA", "RAMI", sdf.parse("02/04/1970"));
        Femme f2 = new Femme("AMAL", "ALI", sdf.parse("10/07/1975"));
        Femme f3 = new Femme("WAFA", "ALAOUI", sdf.parse("12/09/1980"));
        Femme f4 = new Femme("KARIMA", "ALAMI", sdf.parse("01/01/1972"));
        Femme f5 = new Femme("FATIMA", "ZAHRA", sdf.parse("05/03/1974"));
        Femme f6 = new Femme("NOURA", "SABIR", sdf.parse("15/04/1976"));
        Femme f7 = new Femme("IMANE", "RAHMA", sdf.parse("18/08/1978"));
        Femme f8 = new Femme("LINA", "SAHRAOUI", sdf.parse("03/03/1982"));
        Femme f9 = new Femme("HANANE", "JABIRI", sdf.parse("21/12/1985"));
        Femme f10 = new Femme("MALIKA", "BOUCHRA", sdf.parse("01/06/1983"));

        femmeService.save(f1);
        femmeService.save(f2);
        femmeService.save(f3);
        femmeService.save(f4);
        femmeService.save(f5);
        femmeService.save(f6);
        femmeService.save(f7);
        femmeService.save(f8);
        femmeService.save(f9);
        femmeService.save(f10);

        // 🔹 Création de mariages
        mariageService.save(new Mariage(h1, f1, sdf.parse("03/09/1990"), null, 4)); // en cours
        mariageService.save(new Mariage(h1, f2, sdf.parse("03/09/1995"), null, 2)); // en cours
        mariageService.save(new Mariage(h1, f3, sdf.parse("04/11/2000"), null, 3)); // en cours
        mariageService.save(new Mariage(h1, f4, sdf.parse("03/09/1989"), sdf.parse("03/09/1990"), 0)); // échoué

        // 🔹 Afficher la liste des femmes
        System.out.println("\n=== Liste des femmes ===");
        for (Femme f : femmeService.getAll()) {
            System.out.println(f.getNom() + " " + f.getPrenom());
        }

        // 🔹 Afficher la femme la plus âgée
        Femme plusAgee = femmeService.getFemmePlusAgee();
        System.out.println("\nFemme la plus âgée : " + plusAgee.getNom() + " " + plusAgee.getPrenom());

        // 🔹 Afficher les épouses d’un homme donné
        System.out.println("\n=== Épouses de " + h1.getNom() + " " + h1.getPrenom() + " ===");
        List<Mariage> mariages = mariageService.getByHomme(h1.getId());
        for (Mariage m : mariages) {
            System.out.println("Femme : " + m.getFemme().getNom() + " " + m.getFemme().getPrenom() +
                    " | Début : " + sdf.format(m.getDateDebut()) +
                    (m.getDateFin() != null ? " | Fin : " + sdf.format(m.getDateFin()) : "") +
                    " | Enfants : " + m.getNbrEnfants());
        }

        // 🔹 Exemple d'affichage demandé
        System.out.println("\n=== Détails des mariages de " + h1.getNom() + " " + h1.getPrenom() + " ===");

        System.out.println("\nMariages En Cours :");
        int i = 1;
        for (Mariage m : mariageService.getMariagesEnCours(h1.getId())) {
            System.out.println(i++ + ". Femme : " + m.getFemme().getNom() + " " + m.getFemme().getPrenom() +
                    "   Date Début : " + sdf.format(m.getDateDebut()) +
                    "   Nbr Enfants : " + m.getNbrEnfants());
        }

        System.out.println("\nMariages échoués :");
        i = 1;
        for (Mariage m : mariageService.getMariagesEchoues(h1.getId())) {
            System.out.println(i++ + ". Femme : " + m.getFemme().getNom() + " " + m.getFemme().getPrenom() +
                    "   Date Début : " + sdf.format(m.getDateDebut()) +
                    "   Date Fin : " + sdf.format(m.getDateFin()) +
                    "   Nbr Enfants : " + m.getNbrEnfants());
        }
    }
}
