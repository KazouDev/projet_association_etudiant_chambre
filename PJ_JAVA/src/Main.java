import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    // Arguments : <csv etudiants> <csv chambres> 
    public static void main(String[] args) {
        if (args.length < 2){
            System.out.println("Syntaxe des arguments : <csv fichier etudiants> <csv fichier chambres>");
            return;
        }
        List<Personne> liste_personnes = ReadCSVEtudiant.generateEtudiants(args[0]);
        List<Chambre> liste_chambres = ReadCSVChambre.generateChambres(args[1]);
        
        System.out.println("camlbre size : " + liste_chambres.size());
        Scanner scanner = new Scanner(System.in);

        boolean end = false;

        while (!end){
            System.out.println("\n--- Menu ---");
            System.out.println("1. Ajouter un étudiant");
            System.out.println("2. Afficher les étudiants");
            System.out.println("3. Ajouter une chambre");
            System.out.println("4. Afficher les chambres");
            System.out.println("5. Quitter");
            System.out.print("Choisissez une option : ");

            try {
                int action = scanner.nextInt();
                String line;
                switch (action) {
                    case 1:    
                        scanner.nextLine();
                        System.out.println("Saisir une ligne de CSV Etudiant à ajouter");
                        line = scanner.nextLine();
                        Personne p = new ReadCSVEtudiant(line.split(";")).toPersonne();
                        if (liste_personnes.stream().anyMatch(e -> e.getID().equals(p.getID()))){
                            System.out.println("Cette personne est déjà dans la liste (id: " + p.getID() + ")");
                            continue;
                        }
                        liste_personnes.add(p);
                        break;
                    case 2: 
                        System.out.println("Liste des personnes : ");
                        liste_personnes.stream().forEach(e -> System.out.println(e));
                        break;
                    case 3:    
                        scanner.nextLine();
                        System.out.println("Saisir une ligne de CSV Chambre à ajouter");
                        line = scanner.nextLine();
                        Chambre c = new ReadCSVChambre(line.split(";")).toChambre();
                        if (liste_chambres.stream().anyMatch(e -> e.getID().equals(c.getID()))){
                            System.out.println("Cette chambre est déjà dans la liste (id: " + c.getID() + ")");
                            continue;
                        }
                        liste_chambres.add(c);
                        break;
                    case 4:
                        System.out.println("Liste des chambres : ");
                        liste_chambres.stream().forEach(e -> System.out.println(e));
                        break;
                    case 5:
                        System.out.println("Personne trié ");
                        Collections.sort(liste_personnes);

                        System.out.println("Chambre trié ");
                        Collections.sort(liste_chambres);
                        break;
                    case 6:
                        GestionnaireAssociation asso = new GestionnaireAssociation("Association", liste_personnes, liste_chambres);
                        asso.attacherChambres();
                        asso.afficher();
                        break;
                    default:
                        break;
                }
            } catch(InputMismatchException e){
                System.out.println("Merci de saisir uniquement une valeur entre 0-5.");
                scanner.nextLine();
            }
        }
        
    }
    
}
