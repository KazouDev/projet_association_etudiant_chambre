import java.util.Collections;
import java.util.List;

public class MainTest {

    // Arguments : <csv fichier etudiants> <csv fichier chambres>
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Syntaxe des arguments : <csv fichier etudiants> <csv fichier chambres>");
            return;
        }

        // Test de chargement des fichiers
        System.out.println("=== Test Chargement des Fichiers CSV ===");
        List<Personne> liste_personnes = ReadCSVEtudiant.generateEtudiants(args[0]);
        List<Chambre> liste_chambres = ReadCSVChambre.generateChambres(args[1]);

        System.out.println("Chargement de " + liste_personnes.size() + " candidat(s).");
        System.out.println("Chargement de " + liste_chambres.size() + " chambre(s).");
        
        // Affichage des candidats
        System.out.println("\n=== Liste des Candidats (Personnes) ===");
        liste_personnes.forEach(System.out::println);

        // Affichage des chambres
        System.out.println("\n=== Liste des Chambres ===");
        liste_chambres.forEach(System.out::println);

        // Test d'ajout d'un candidat
        System.out.println("\n=== Test Ajout d'un Nouveau Candidat ===");

        String[] newCandidateData = {"123", "John", "Doe", "22", "M", "123456", "2025", "[15,16,17]", "1113789023", "18-20"};
        Personne newPersonne = new ReadCSVEtudiant(newCandidateData).toPersonne();
        if (liste_personnes.stream().noneMatch(p -> p.getID().equals(newPersonne.getID()))) {
            liste_personnes.add(newPersonne);
            Collections.sort(liste_personnes);
            System.out.println("Candidat ajouté avec succès : " + newPersonne);
        } else {
            System.out.println("Candidat déjà présent : " + newPersonne.getID());
        }

        // Test d'ajout d'une chambre
        System.out.println("\n=== Test Ajout d'une Nouvelle Chambre ===");
        String[] newChambreData = {"456", "Chambre 12", "Résidence A", "Adresse 1", "Ville", "12345", "20", "1", "2", "1", "15"};
        Chambre newChambre = new ReadCSVChambre(newChambreData).toChambre();
        if (liste_chambres.stream().noneMatch(c -> c.getID().equals(newChambre.getID()))) {
            liste_chambres.add(newChambre);
            Collections.sort(liste_chambres);
            System.out.println("Chambre ajoutée avec succès : " + newChambre);
        } else {
            System.out.println("Chambre déjà présente : " + newChambre.getID());
        }

        // Test d'association des chambres et candidats
        System.out.println("\n=== Test Association Chambres et Candidats ===");
        GestionnaireAssociation asso = new GestionnaireAssociation(liste_personnes, liste_chambres);
        int[] result = asso.attacherChambres();
        System.out.println("Association terminée.");
        System.out.println("Candidats sans chambre : " + result[0]);
        System.out.println("Chambres disponibles : " + result[1]);

        // Test affichage de l'association
        System.out.println("\n=== Résultat des Associations ===");
        asso.afficher();
    }
}
