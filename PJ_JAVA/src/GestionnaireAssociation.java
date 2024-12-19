import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import personne.Personne;

public class GestionnaireAssociation {
  private Map<Personne, Chambre> association;
  private List<Personne> candidats;
  private List<Chambre> chambres_disponibles;
  private List<Chambre> chambres;

  public GestionnaireAssociation(List<Personne> candidats, List<Chambre> chambres){
    this.association = new TreeMap<Personne, Chambre>();
    this.candidats = candidats;
    this.chambres_disponibles = chambres.stream().collect(Collectors.toList()); // Créé une copie de la liste afin de la modifier sans modifié celle de base.
    this.chambres = chambres;
  }

  /*
  Faire 8. Afficher stats de l'association.
  
  public int nbAssociation(){

  }

  public int nbCandidat(){

  }

  public int nbChambres(){

  }

  public int nbChambresDispo(){

  }*/
  public boolean estAssocie(){
    return !this.association.isEmpty();
  }

  public int[] attacherChambres(){
    this.candidats.stream()
    .limit(chambres_disponibles.size())
    .forEach(e -> {
      Chambre c = this.chambres_disponibles.removeFirst();
      this.association.put(e, c);
    });
    int etudiant_non_attribue = Integer.max(0, this.candidats.size() - (this.chambres.size() - this.chambres_disponibles.size()));
    int chambre_non_attribue = this.chambres_disponibles.size();
    
    return new int[]{etudiant_non_attribue, chambre_non_attribue};
  }

  public void libererChambre(String id){
    this.association.entrySet().removeIf(entry -> {
      if (entry.getValue().getID().equals(id)){
        this.chambres_disponibles.add(entry.getValue());
        return true;
      }
      return false;
    });
  }
  

  public void afficher(){
      System.out.println("Association des candidats :");
      System.out.println("------------------------");
      this.association.forEach((key, value) -> System.out.println(key + " => " + value));
      System.out.println("------------------------");
  }

}
