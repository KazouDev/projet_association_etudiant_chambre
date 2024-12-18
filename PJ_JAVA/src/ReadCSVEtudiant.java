
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ReadCSVEtudiant {
    private String id;
    private String name;
    private String surname;
    private int age;
    private Genre gender;
    private long INE;
    private int promo;
    private int[] notes;
    private String contrat;
    private String[] working_hours;

    public ReadCSVEtudiant(String[] line_splited) {
        this.id = line_splited[0];
        this.name = line_splited[1];
        this.surname = line_splited[2];
        this.age = Parser.safeParseInt(line_splited[3], 0); // valeur par défaut 0
        this.gender = Genre.parseGenre(line_splited[4]);
        this.INE = Parser.safeParseLong(line_splited[5], 0L); // valeur par défaut 0
        this.promo = Parser.safeParseInt(line_splited[6], 0);
        this.notes = line_splited[7].equals("null") ? new int[0] : Parser.parseListInt(line_splited[7]);
        this.contrat = line_splited[8].equals("null") ? "null" : line_splited[8];
        this.working_hours = line_splited[9].equals("null") ? new String[0] : line_splited[9].split(",");
    }

    public Personne toPersonne(){
        Personne p;
        if (this.INE == 0){
            // Pas un étudiant
            if (this.contrat == "null"){
                p = new Personne(this.id, this.name, this.surname, this.gender, this.age);
            } else {
                p = new Travailleur(this.id, this.name, this.surname, this.gender, this.age, this.contrat, this.working_hours);
            }
        } else if (this.contrat == "null"){
            // Etudiant qui ne travaille pas
            p = new Etudiant(this.id, this.name, this.surname, this.gender, this.age, this.INE, this.promo, this.notes);
        } else {
            // Etudiant qui travaille
            p = new EtudiantTravailleur(this.id, this.name, this.surname, this.gender, this.age, this.INE, this.promo, this.notes, this.contrat, this.working_hours);
        }

        return p;
    }
    
    public static ArrayList<Personne> generateEtudiants(String path){
        File csvFile = new File(path); // to read the CSV file
        ArrayList<Personne> candidats = new ArrayList<>();
            try(BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String line;
                reader.readLine(); // Dropping first line (CSV header)
                while((line = reader.readLine()) != null) { // reader.readLine() -> to get the line and 
                    candidats.add(new ReadCSVEtudiant(line.split(";")).toPersonne()); // spliting here but it was a choice
                }
                Collections.sort(candidats);
                return candidats;
            } catch (IOException e) {
                System.err.println(e);
                System.err.println("An issue occured... (returned empty array)");
            }

            return candidats;
    }
}