package Model.Reglementation;

import java.util.Date;

public record Reglementation(int id,String nom,String descriptionExigence,Date dateMiseEnApplication) {
    
    public boolean estActive() {
        // Vérifie si la date actuelle dépasse la date de mise en application
        return new Date().after(dateMiseEnApplication);
    }

    public void afficher() {
        System.out.println("Règlementation : " + nom +
                "\nExigences : " + descriptionExigence +
                "\nDate d'application : " + dateMiseEnApplication);
    }

}
