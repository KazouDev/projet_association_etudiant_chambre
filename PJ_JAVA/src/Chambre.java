public class Chambre {
    private String id;
    private String name;
    private String residence_name;
    private String adress;
    private String city;
    private int code_city;
    private int[] notes;
    private int latest_renovation;

    public Chambre(String id, String name, String residence_name, String adress, String city, int code_city, int[] notes, int latest_renovation){
        this.id = id;
        this.name = name;
        this.residence_name = residence_name;
        this.adress = adress;
        this.city = city;
        this.code_city = code_city;
        this.notes = notes;
        this.latest_renovation = latest_renovation;
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

    public void setLatest_renovation(int latest_renovation) {
        this.latest_renovation = latest_renovation;
    }

    public String toString(){
        return "[" + this.id + "] Chambre (" + this.name + ") dans la résidence " + this.residence_name + " noté : " + this.getMoyenne();
    }
}
