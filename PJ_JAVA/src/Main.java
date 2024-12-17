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

        Scanner scanner = new Scanner(System.in);

        GestionnaireAssociation asso = new GestionnaireAssociation("Association", liste_personnes, liste_chambres);

        boolean end = false;

        while (!end){
            System.out.println("\n--- Menu ---");
            System.out.println("1. Ajouter un candidat");
            System.out.println("2. Afficher les candidats");
            System.out.println("3. Ajouter une chambre");
            System.out.println("4. Afficher les chambres");
            System.out.println("5. Attribuer chambres");
            System.out.println("6. Afficher chambres attribué");
            System.out.println("7. Quitter");
            System.out.print("Choisissez une option : ");

            try {
                int action = scanner.nextInt();
                String line;
                String[] splitted_line;
                switch (action) {
                    case 1:    
                        scanner.nextLine();
                        System.out.println("Saisir une ligne de CSV Etudiant à ajouter");
                        line = scanner.nextLine();
                        splitted_line = line.split(";");
                        if (splitted_line.length == 10){
                            Personne p = new ReadCSVEtudiant(splitted_line).toPersonne();
                            if (liste_personnes.stream().anyMatch(e -> e.getID().equals(p.getID()))){
                                System.out.println("Cette personne est déjà dans la liste (id: " + p.getID() + ")");
                                continue;
                            }
                            liste_personnes.add(p);
                            Collections.sort(liste_personnes);
                            System.out.println("Personne (" + p.getID() + ") ajoutée avec succès.");
                        } else {
                            System.out.println("Impossible de lire cette personne.\nFormat attendu : ID;name;surname;age;gender;INE;promo;notes;contrat;working_hours");
                        }
                        
                        break;
                    case 2: 
                        System.out.println("Liste des personnes : ");
                        liste_personnes.stream().forEach(e -> System.out.println(e));
                        break;
                    case 3:    
                        scanner.nextLine();
                        System.out.println("Saisir une ligne de CSV Chambre à ajouter");
                        line = scanner.nextLine();
                        splitted_line = line.split(";");
                        if (splitted_line.length == 11){
                            Chambre c = new ReadCSVChambre(splitted_line).toChambre();
                            if (liste_chambres.stream().anyMatch(e -> e.getID().equals(c.getID()))){
                                System.out.println("Cette chambre est déjà dans la liste (id: " + c.getID() + ")");
                                continue;
                            }
                            liste_chambres.add(c);
                            Collections.sort(liste_chambres);
                            System.out.println("Chambre (" + c.getID() + ") ajoutée avec succès.");
                        } else {
                            System.out.println("Impossible de lire cette chambre.\nFormat attendu : ID;name;residence;address;city;city_code;surface;creation_date;latest_renovation_date;nb_locations;scores");
                        }
                        break;
                    case 4:
                        System.out.println("Liste des chambres : ");
                        liste_chambres.stream().forEach(e -> System.out.println(e));
                        break;
                    case 5:
                        int[] result = asso.attacherChambres();
                        System.out.println("Association des chambres avec succès : ");
                        System.out.println("Candidat(s) sans chambre : " + result[0]);
                        System.out.println("Chambre(s) disponible : " + result[1]);
                        break;
                    case 6:
                        if (!asso.estAssocie()){
                            System.out.println("Vous devez d'abord associé les chambres avec les candidats (option 5).");
                            continue;
                        }
                        asso.afficher();
                        break;    
                    case 7:
                        end = true;
                        break;
                }
            } catch(InputMismatchException e){
                System.out.println("Merci de saisir uniquement une valeur entre 0-7.");
                scanner.nextLine();
            }
        }
        
    }
    
}
