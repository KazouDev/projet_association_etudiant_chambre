import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReadCSVEtudiant {
    private String id;
    private String name;
    private String surname;
    private int age;
    private String gender;
    private long INE;
    private int promo;
    private int[] notes;
    private String contrat;
    private String[] working_hours;

    public ReadCSVEtudiant(String[] line_splited) {
        // just filling variables from the table
        this.id = line_splited[0];
        this.name = line_splited[1];
        this.surname = line_splited[2];
        this.age = Integer.parseInt(line_splited[3]); // had to convert to int
        this.gender = line_splited[4];
        // 'if' one line statements, just to verify if the value is "null"
        this.INE = ((line_splited[5].equals("null")) ? 0 : Long.parseLong(line_splited[5])); 
        this.promo = ((line_splited[6].equals("null")) ? 0 : Integer.parseInt(line_splited[6]));
        this.notes = ((line_splited[7].equals("null")) ? new int[0] : parse_list_int(line_splited[7]));
        this.contrat = ((line_splited[8].equals("null")) ? "null" : line_splited[8]);
        this.working_hours = ((line_splited[9].equals("null")) ? new String[0] : line_splited[9].split(","));
    }

    private static int[] parse_list_int(String notes) {
        // function to convert the string of notes to a list of int
        String[] n = notes.replace("[", "").replace("]", "").split(",");
        int[] res = new int[n.length];
        for (int i = 0; i<n.length; i++){
            // System.out.println(n[i]);
            res[i] = Integer.parseInt(n[i]);
        }
        return res;
    }

    public void affiche() {
        System.out.println(this.id);
        System.out.println(this.name);
        System.out.println(this.surname);
        System.out.println(this.age);
        System.out.println(this.gender);
        System.out.println(this.INE);
        System.out.println(this.promo);
        System.out.println(this.notes);
        System.out.println(this.contrat);
        System.out.println(this.working_hours);
    }

    public Personne toPersonne(){
        Personne p;
        if (this.INE == 0){
            // Pas un étudiant
            p = new Personne(this.id, this.name, this.surname, this.age);
        } else if (this.contrat == "null"){
            // Etudiant qui ne travaille pas
            p = new Etudiant(this.id, this.name, this.surname, this.age, this.INE, this.promo, this.notes);
        } else {
            // Etudiant qui travaille
            p = new WorkingEtudiant(this.id, this.name, this.surname, this.age, this.INE, this.promo, this.notes, this.contrat, this.working_hours);
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

    public static void main(String[] args) {
        String path;
        if (args.length == 0){ 
            System.out.println("This main needs a path!");
        }
        else {
            path = args[0]; // getting the argument
            generateEtudiants(path).stream().forEach(e -> System.out.println(e));
        }
    }
}