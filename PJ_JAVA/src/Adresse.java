public class Adresse {
  private String adress;
  private String city;
  private int code_city;

  public Adresse(String city, int code_city, String adresse){
    this.adress = adresse;
    this.city = city;
    this.code_city = code_city;
  }

  public String getAdress() {
    return adress;
  }

  public String getCity() {
    return city;
  }

  public int getCodeCity() {
    return code_city;
  }

  public void setAdress(String adress) {
    this.adress = adress;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setCodeCity(int code_city) {
    this.code_city = code_city;
  }

  @Override
  public String toString() {
    return this.adress + ", " + this.code_city + " " + this.city;
  }
}
