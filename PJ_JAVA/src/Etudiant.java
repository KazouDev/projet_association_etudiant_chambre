import java.util.List;

public class Etudiant extends Personne {
    private long ine;
    private int promo;
    private int[] notes;

    public Etudiant(String id, String name, String surname, int age, long ine, int promo, int[] notes){
        super(id, name, surname, age);
        this.ine = ine;
        this.promo = promo;
        this.notes = notes;
    }

    public long getIne() {
        return ine;
    }

    public void setIne(long ine) {
        this.ine = ine;
    }

    public int getPromo() {
        return promo;
    }

    public void setPromo(int promo) {
        this.promo = promo;
    }

    public int[] getNotes() {
        return notes;
    }

    public void setNotes(int[] notes) {
        this.notes = notes;
    }

    public int getMoyenne(){
        int somme = 0;
        if (this.notes.length > 0){
            for(int i = 0; i < this.notes.length; i++){
                somme += this.notes[i];
            }
            return somme / this.notes.length;
        } else {
            return 0;
        }
    }

    public String toString(){
        return super.toString() + " - Etudiant : " + this.ine + " (" + this.promo + "), moyenne : " + this.getMoyenne();
    }

}
