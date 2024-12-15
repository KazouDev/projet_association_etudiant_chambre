import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadCSVChambre {
    private String id;
    private String name;
    private String residence;
    private String address;
    private String city;
    private int city_code;
    private float surface;
    private int creation_date;
    private int latest_renovation_date;
    private int nb_locations;
    private int[] scores;

    public ReadCSVChambre(String[] line_splited) {
        // just filling variables from the table
        this.id = line_splited[0];
        this.name = line_splited[1];
        this.residence = line_splited[2];
        this.address = line_splited[3]; // had to convert to int
        this.city = line_splited[4];
        this.city_code = Integer.parseInt(line_splited[5]);
        this.surface = Float.parseFloat(line_splited[6]);
        this.creation_date = Integer.parseInt(line_splited[7]);
        this.latest_renovation_date = Integer.parseInt(line_splited[8]);
        this.nb_locations = Integer.parseInt(line_splited[9]);
        this.scores = ReadCSVChambre.parse_list_int(line_splited[10]);
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

    public Chambre toChambre(){
        return new Chambre(this.id, this.name, this.residence, this.address, this.city, this.city_code, this.scores, this.latest_renovation_date);
    }

    public void affiche() {
        System.out.println(this.id);
        System.out.println(this.name);
        System.out.println(this.residence);
        System.out.println(this.address);
        System.out.println(this.city);
        System.out.println(this.city_code);
        System.out.println(this.surface);
        System.out.println(this.creation_date);
        System.out.println(this.latest_renovation_date);
        System.out.println(this.nb_locations);
        System.out.println(this.scores);
    }

    public static List<Chambre> generateChambres(String path){
        File csvFile = new File(path); // to read the CSV file
        ArrayList<Chambre> chambres = new ArrayList<>();
            try(BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String line;
                reader.readLine(); // Dropping first line (CSV header)

                while((line = reader.readLine()) != null) { // reader.readLine() -> to get the line and 
                    chambres.add(new ReadCSVChambre(line.split(";")).toChambre()); // spliting here but it was a choice
                }
                return chambres;
            } catch (IOException e) {
                System.out.println(e);
                System.out.println("An issue occured... (returned empty array)");
            }
            return chambres;
    }


    public static void main(String[] args) {
        String path;
        if (args.length == 0){ 
            System.out.println("This main needs a path!");
        }
        else {
            path = args[0]; // getting the argument
            generateChambres(path).stream().forEach(ch -> System.out.println(ch.toString()));;
        }
    }
}