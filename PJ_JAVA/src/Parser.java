public class Parser {
  // Méthode sécurisée pour parser un int
  public static int safeParseInt(String value, int defaultValue) {
    try {
        return Integer.parseInt(value);
    } catch (NumberFormatException e) {
        System.err.println("Impossible de parser en entier : " + value + " -> Utilisation de " + defaultValue);
        return defaultValue;
    }
}

// Méthode sécurisée pour parser un long
public static long safeParseLong(String value, long defaultValue) {
    try {
        return Long.parseLong(value);
    } catch (NumberFormatException e) {
        System.err.println("Impossible de parser en long : " + value + " -> Utilisation de " + defaultValue);
        return defaultValue;
    }
}

 // Méthode sécurisée pour parser un float
 public static float safeParseFloat(String value, float defaultValue) {
  try {
      return Float.parseFloat(value);
  } catch (NumberFormatException e) {
      System.err.println("Impossible de parser en float : " + value + " -> Valeur par défaut utilisée : " + defaultValue);
      return defaultValue;
  }
}

// Conversion de la chaîne de notes en tableau d'entiers
public static int[] parseListInt(String notes) {
    try {
        String[] n = notes.replace("[", "").replace("]", "").split(",");
        int[] res = new int[n.length];
        for (int i = 0; i < n.length; i++) {
            res[i] = safeParseInt(n[i].trim(), 0); // Trim pour nettoyer les espaces
        }
        return res;
    } catch (Exception e) {
        System.err.println("Erreur de parsing des notes : " + notes);
        return new int[0];
    }
}
}
