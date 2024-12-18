public enum Genre {
    HOMME, FEMME, INCONNU;

    @Override
    public String toString() {
        if(this == HOMME){
            return "Homme";
        } else if (this == FEMME){
            return "Femme";
        } else {
            return "Inconnu";
        }
    }


    public static Genre parseGenre(String g){
        if (g.equalsIgnoreCase("M")){
            return HOMME;
        } else if (g.equalsIgnoreCase("F")){
            return FEMME;
        } else {
            return INCONNU;
        }
    }
}


