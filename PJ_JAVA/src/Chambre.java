public class Chambre implements Comparable<Chambre> {
    private String id;
    private String name;
    private Adresse adresse;
    private float surface;
    private String residence_name;
    private int[] notes;
    private int latest_renovation;

    public Chambre(String id, String name, String residence_name, String adress, float surface, String city, int code_city, int[] notes, int latest_renovation){
        this.id = id;
        this.name = name;
        this.residence_name = residence_name;
        this.adresse = new Adresse(city, code_city, adress);
        this.surface = surface;
        this.notes = notes;
        this.latest_renovation = latest_renovation;
    }

    public String getID(){
        return this.id;
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

    public int[] getNotes() {
        return notes;
    }

    public void setNotes(int[] notes) {
        this.notes = notes;
    }

    public int getLatest_renovation() {
        return latest_renovation;
    }

    public String toString(){
        return "[" + this.id + "] Chambre (" + this.name + ") dans la résidence " + this.residence_name + " noté : " + this.getMoyenne() + " à l'adresse " + this.adresse;
    }

    @Override
    public int compareTo(Chambre o) {
        int note_comp = Integer.compare(o.getMoyenne(), this.getMoyenne());
        if (note_comp == 0){
            return -Float.compare(this.surface, o.surface);
        }
        return note_comp;
    }
}
