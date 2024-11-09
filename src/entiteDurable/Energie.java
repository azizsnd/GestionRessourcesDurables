package entiteDurable;

import java.util.Date;

public class Energie extends Ressource{
    private boolean estRenouvelable;
    private String type;

    public Energie(boolean estRenouvelable, String type, String uniteDeMesure, double utilisationReference, double utilisationActuelle, double coutParUnite, int id, String nom, String description, Date dateCreation, ObjectifDurabilite objectif) {
        super(uniteDeMesure, utilisationReference, utilisationActuelle, coutParUnite, id, nom, description, dateCreation, objectif);
        this.estRenouvelable = estRenouvelable;
        this.type = type;
    }

    public void setEstRenouvelable(boolean estRenouvelable) {
        this.estRenouvelable = estRenouvelable;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public boolean estRenouvelable() {
        return estRenouvelable;
    }   

    @Override
    public String toString() {
        return "Energie{" + super.toString()+ ' '+"estRenouvelable=" + estRenouvelable + ", type=" + type + '}';
    }
    
}
