import java.util.ArrayList;
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
                switch (action) {
                    case 1:    
                        scanner.nextLine();
                        System.out.println("Saisir une ligne de CSV Etudiant à ajouter");
                        String line = scanner.nextLine();
                        liste_personnes.add(new ReadCSVEtudiant(line.split(";")).toPersonne());
                        break;
                    
                    case 2: 
                        System.out.println("Liste des personnes : ");
                        liste_personnes.stream().forEach(p -> System.out.println(p));
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
