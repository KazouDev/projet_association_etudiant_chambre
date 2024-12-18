
public class Etudiant extends Personne {
    private long ine;
    private int promo;
    private int[] notes;

    public Etudiant(String id, String name, String surname, Genre genre, int age, long ine, int promo, int[] notes){
        super(id, name, surname, genre, age);
        this.ine = ine;
        this.promo = promo;
        this.notes = notes;
    }

    public long getIne() {
        return ine;
    }

    public int getPromo() {
        return promo;
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

    @Override
    public float getScore(){
        return super.getScore() + 50 + this.getMoyenne() + (this.promo / 1000);
    }

}
