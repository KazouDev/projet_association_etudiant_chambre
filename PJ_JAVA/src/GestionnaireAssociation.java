import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class GestionnaireAssociation {
  private Map<Personne, Chambre> association;
  private List<Personne> candidats;
  private List<Chambre> chambres_disponibles;
  private List<Chambre> chambres;
  private String name;

  public GestionnaireAssociation(String name, List<Personne> candidats, List<Chambre> chambres){
    this.name = name;
    this.association = new TreeMap<Personne, Chambre>();
    this.candidats = candidats;
    this.chambres_disponibles = chambres.stream().collect(Collectors.toList()); // Créé une copie de la liste afin de la modifier sans modifié celle de base.
    this.chambres = chambres;
  }

  public void attacherChambres(){
    this.candidats.stream()
    .limit(chambres_disponibles.size())
    .forEach(e -> {
      Chambre c = this.chambres_disponibles.removeFirst();
      this.association.put(e, c);
    });
    int etudiant_non_attribue = Integer.max(0, this.candidats.size() - (this.chambres.size() - this.chambres_disponibles.size()));
    int chambre_non_attribue = this.chambres_disponibles.size();
    System.out.println("Candidat(s) sans chambre : " + etudiant_non_attribue);
    System.out.println("Chambre(s) disponible : " + chambre_non_attribue);
  }
  

  public void afficher(){
      System.out.println("Contenu de la TreeMap :");
      System.out.println("------------------------");
      this.association.forEach((key, value) -> System.out.println("Clé : " + key + " | Valeur : " + value));
      System.out.println("------------------------");
  }

}
