package personne;
public class Personne implements Comparable<Personne> {
    private String id;
    private String name;
    private String surname;
    private Genre genre;
    private int age;

    public Personne(String id, String name, String surname, Genre genre, int age){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.genre = genre;
        this.age = age;
    }

    public String getID(){
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString(){
        return this.surname + " " + this.name + " " + this.genre + " de " + this.age + " ans";
    }

    public float getScore(){
        return 1;
    }

    @Override
    public int compareTo(Personne o) {
        int score_comp = Float.compare(o.getScore(), this.getScore());

        if (score_comp == 0){
            return Integer.compare(o.age, this.age);
        }
        return score_comp;
       
        /*// Priorité : Personne > WorkingEtudiant > Etudiant
        // Cas 1 : Comparer WorkingEtudiant
        if (this instanceof WorkingEtudiant) {
            if (o instanceof WorkingEtudiant) {
                // Deux WorkingEtudiants : comparer les moyennes
                return Integer.compare(((Etudiant) o).getMoyenne(), ((Etudiant) this).getMoyenne());
            }
            // WorkingEtudiant prioritaire sur Etudiant, moins prioritaire que PersonneTravailleur
            if (o.getClass() == PersonneTravailleur.class){
                return 1;
            }
            return -1;
        }
    
        // Cas 2 : Comparer Etudiant
        if (this instanceof Etudiant) {
            if (o.getClass() == Etudiant.class) {
                // Deux Étudiants : comparer les moyennes
                 return Integer.compare(((Etudiant) o).getMoyenne(), ((Etudiant) this).getMoyenne());
            }
            // Étudiant moins prioritaire que tout autre type sauf Personne
            if (o.getClass() == Personne.class){
                return -1;
            }

            return 1;
        }
    
        // Cas 3 : Comparer PersonneTravailleur
        if (this instanceof PersonneTravailleur) {
            // PersonneTravailleur est prioritaire sur tout le monde
            if (o instanceof PersonneTravailleur) return Integer.compare(o.age, this.age);
            return -1;
        }

        // Deux Personnes : comparer les âges
        return Integer.compare(o.getAge(), this.getAge());*/
    }
    
    

}