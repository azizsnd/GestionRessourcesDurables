package  Model.Reglementation;

import java.util.Date;

public class Reglementation {
    private int id;
    private String nom;
    private String descriptionExigence;
    private Date dateMiseEnApplication;

    public Reglementation(int id, String nom, String descriptionExigence, Date dateMiseEnApplication) {
        this.id = id;
        this.nom = nom;
        this.descriptionExigence = descriptionExigence;
        this.dateMiseEnApplication = dateMiseEnApplication;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescriptionExigence() {
        return descriptionExigence;
    }

    public void setDescriptionExigence(String descriptionExigence) {
        this.descriptionExigence = descriptionExigence;
    }

    public Date getDateMiseEnApplication() {
        return dateMiseEnApplication;
    }

    public void setDateMiseEnApplication(Date dateMiseEnApplication) {
        this.dateMiseEnApplication = dateMiseEnApplication;
    }

    public boolean estConforme() {
        // Exemple : vérifier si la date actuelle dépasse la date de mise en application
        return new Date().after(dateMiseEnApplication);
    }

    public void afficher() {
        System.out.println("Règlementation : " + nom + "\nExigences : " + descriptionExigence + "\nDate d'application : " + dateMiseEnApplication);
    }

    @Override
    public String toString() {
        return "Reglementation{" + "id=" + id + ", nom=" + nom + ", descriptionExigence=" + descriptionExigence + ", dateMiseEnApplication=" + dateMiseEnApplication + '}';
    }
    
}
