package Model.entiteDurable;

import java.util.Date;

public sealed class Ressource extends EntiteDurable permits Energie {
    private String uniteDeMesure;
    private double utilisationReference;
    private double utilisationActuelle;
    private double coutParUnite;

    public Ressource( int id, String nom, String description, Date dateCreation, ObjectifDurabilite objectif,String uniteDeMesure, double utilisationReference, double utilisationActuelle, double coutParUnite) {
        super(id, nom, description, dateCreation, objectif);
        this.uniteDeMesure = uniteDeMesure;
        this.utilisationReference = utilisationReference;
        this.utilisationActuelle = utilisationActuelle;
        this.coutParUnite = coutParUnite;
    }

    public String getUniteDeMesure() {
        return uniteDeMesure;
    }

    public void setUniteDeMesure(String uniteDeMesure) {
        this.uniteDeMesure = uniteDeMesure;
    }

    public double getUtilisationReference() {
        return utilisationReference;
    }

    public void setUtilisationReference(double utilisationReference) {
        this.utilisationReference = utilisationReference;
    }

    public double getUtilisationActuelle() {
        return utilisationActuelle;
    }

    public void setUtilisationActuelle(double utilisationActuelle) {
        this.utilisationActuelle = utilisationActuelle;
    }

    public double getCoutParUnite() {
        return coutParUnite;
    }

    public void setCoutParUnite(double coutParUnite) {
        this.coutParUnite = coutParUnite;
    }

    public double calculerTauxReduction() {
        getObjectif().setProgresActuel(((utilisationReference - utilisationActuelle)/utilisationReference)*100);
        return getObjectif().getProgresActuel();

    }

    public double calculerCoutTotal() {
        return utilisationActuelle * coutParUnite;
    }

    public void reinitialiserUtilisateur() {
        this.utilisationActuelle = 0;
    }

    @Override
    public String toString() {
        return "Ressource{" + "uniteDeMesure=" + uniteDeMesure + ", utilisationReference=" + utilisationReference + ", utilisationActuelle=" + utilisationActuelle + ", coutParUnite=" + coutParUnite + '}';
    }

}
