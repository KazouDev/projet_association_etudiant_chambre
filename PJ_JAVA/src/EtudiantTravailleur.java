
public class EtudiantTravailleur extends Etudiant {
    private String contrat;
    private String[] working_hours;

    public EtudiantTravailleur(String id, String name, String surname, Genre genre, int age, long ine, int promo, int[] notes, String contrat, String[] working_hours){
        super(id, name, surname, genre, age, ine, promo, notes);
        this.contrat = contrat;
        this.working_hours = working_hours;
    }

    @Override
    public String toString() {
        return super.toString() + " - Sous Contrat de Travail : " + this.contrat + " (" + String.join(",", this.working_hours).replaceAll("\"", "") + ").";
    }

    @Override
    public float getScore(){
        return super.getScore() + 100 + this.getMoyenne();
    }
    
}
