import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
        this.address = line_splited[3];
        this.city = line_splited[4];
        this.city_code = Parser.safeParseInt(line_splited[5], 0);
        this.surface = Parser.safeParseFloat(line_splited[6], 0.0f);
        this.creation_date = Parser.safeParseInt(line_splited[7], 0);
        this.latest_renovation_date = Parser.safeParseInt(line_splited[8], 0);
        this.nb_locations = Parser.safeParseInt(line_splited[9], 0);
        this.scores = Parser.parseListInt(line_splited[10]);
    }

    public Chambre toChambre(){
        return new Chambre(this.id, this.name, this.residence, this.address, this.city, this.city_code, this.scores, this.latest_renovation_date);
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
                Collections.sort(chambres);
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