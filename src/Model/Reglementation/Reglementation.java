package Model.Reglementation;

import java.util.Date;

public record Reglementation(int id,String nom,String descriptionExigence,Date dateMiseEnApplication) {
    
    public boolean estActive() {
        return new Date().after(dateMiseEnApplication);
    }
    public boolean isEmpty() {
        return (nom == null || nom.isEmpty()) || 
               (descriptionExigence == null || descriptionExigence.isEmpty()) || 
               (dateMiseEnApplication == null);
    }
    public void afficher() {
        System.out.println("Règlementation : " + nom +
                "\nExigences : " + descriptionExigence +
                "\nDate d'application : " + dateMiseEnApplication);
    }

}
