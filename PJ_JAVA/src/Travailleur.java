public class Travailleur extends Personne {
    private String contrat;
    private String[] working_hours;

    public Travailleur(String id, String name, String surname, Genre genre, int age, String contrat, String[] working_hours){
        super(id, name, surname, genre, age);
        this.contrat = contrat;
        this.working_hours = working_hours;
    }

    @Override
    public String toString() {
        return super.toString() + " - Sous Contrat de Travail : " + this.contrat + " (" + String.join(",", this.working_hours).replaceAll("\"", "") + ").";
    }

    @Override
    public float getScore(){
        return super.getScore() + 200;
    }
}
