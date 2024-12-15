public class Personne implements Comparable<Personne> {
    private String id;
    private String name;
    private String surname;
    private int age;

    public Personne(String id, String name, String surname, int age){
        this.id = id;
        this.name = name;
        this.surname = surname;
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
        return this.surname + " " + this.name + " " + this.age + " ans";
    }

    @Override
    public int compareTo(Personne o) {
        // Priorité : Personne > WorkingEtudiant > Etudiant
        // Cas 1 : Comparer WorkingEtudiant
        if (this instanceof WorkingEtudiant) {
            if (o instanceof WorkingEtudiant) {
                // Deux WorkingEtudiants : comparer les moyennes
                return Integer.compare(((Etudiant) this).getMoyenne(), ((Etudiant) o).getMoyenne());
            }
            // WorkingEtudiant prioritaire sur Etudiant, moins prioritaire que Personne
            return (o instanceof Etudiant) ? 1 : -1;
        }
    
        // Cas 2 : Comparer Etudiant
        if (this instanceof Etudiant) {
            if (o.getClass() == Etudiant.class) {
                // Deux Étudiants : comparer les moyennes
                 return Integer.compare(((Etudiant) this).getMoyenne(), ((Etudiant) o).getMoyenne());
            }
            // Étudiant moins prioritaire que tout autre type
            return -1;
        }
    
        // Cas 3 : Comparer Personne
        if (o instanceof Etudiant || o instanceof WorkingEtudiant) {
            // Personne est prioritaire sur Étudiant et WorkingEtudiant
            return 1;
        }
    
        // Deux Personnes : comparer les âges
        return Integer.compare(this.getAge(), o.getAge());
    }
    
    

}
