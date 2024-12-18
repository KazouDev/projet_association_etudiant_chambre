import java.util.List;
import java.util.function.Supplier;

public class MainTest {

    public static <T> void assertTest(String name, Supplier<T> condition, T expected) {
        T result = condition.get();
        if (result.equals(expected)) {
            System.out.println("Test '" + name + "' passé avec succès");
        } else {
            System.out.println("Test '" + name + "' non passé ! Attendu : " + expected + ", Reçu : " + result);
        }
    }

    // Arguments : <csv fichier etudiants> <csv fichier chambres>
    public static void main(String[] args) {
        // Test de chargement des fichiers
        System.out.println("=== Test Chargement des Fichiers CSV ===");
        List<Personne> liste_personnes = ReadCSVEtudiant.generateEtudiants("resources/test/etudiant_test.csv");
        List<Chambre> liste_chambres = ReadCSVChambre.generateChambres("resources/test/chambres_test.csv");

        assertTest("Chargement étudiant", () -> liste_personnes.size(), 1);
        assertTest("Chargement chambres", () -> liste_chambres.size(), 1);

        assertTest("Affichage étudiant et vérifications de la bonne instanciation de la classe", () -> liste_personnes.get(0).toString(), "DOE John Homme de 20 ans - Etudiant : 123345681 (2025), moyenne : 13");
        assertTest("Affichage chambre et vérifications de la bonne instanciation de la classe", () -> liste_chambres.get(0).toString(), "[wgkdc1ehq1] Chambre (A170) dans la résidence CROUS noté : 2 à l'adresse 46 Avenue du Général de Gaulle, 22300 Lannion");


        // Test d'ajout d'un candidat
        System.out.println("\n=== Test Ajout d'un Nouveau Candidat ===");

        String[] newCandidateData = {"123", "John", "Doe", "22", "M", "123456", "2025", "[15,16,17]", "1113789023", "18-20"};
        Personne newPersonne = new ReadCSVEtudiant(newCandidateData).toPersonne();

        liste_personnes.add(newPersonne);        
        assertTest("Ajout d'un étudiant", () -> liste_personnes.stream().anyMatch(p -> p.getID().equals(newPersonne.getID())), true);

        // Test d'ajout d'une chambre
        String[] newChambreData = {"456", "Chambre 12", "Résidence A", "Adresse 1", "Ville", "12345", "20", "1", "2", "1", "15"};
        Chambre newChambre = new ReadCSVChambre(newChambreData).toChambre();
        liste_chambres.add(newChambre);
        assertTest("Ajout d'une chambre", () -> liste_chambres.stream().anyMatch(c -> c.getID().equals(c.getID())), true);


        GestionnaireAssociation asso = new GestionnaireAssociation(liste_personnes, liste_chambres);
        assertTest("Résultat d'une association", () -> {
            int[] result = asso.attacherChambres();
            return result[0] == 0 && result[1] == 0;
        }, true);
        asso.afficher();
    }
}
