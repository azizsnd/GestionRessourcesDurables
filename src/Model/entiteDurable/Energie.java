package Model.entiteDurable;

import java.util.Date;

public final class Energie extends Ressource{
    private boolean estRenouvelable;
    private String type;

    public Energie( int id, String nom, String description, Date dateCreation, ObjectifDurabilite objectif,boolean estRenouvelable, String type, String uniteDeMesure, double utilisationReference, double utilisationActuelle, double coutParUnite) throws ObjectifInvalideException {
        super(id, nom, description, dateCreation, objectif,uniteDeMesure, utilisationReference, utilisationActuelle, coutParUnite);
        this.estRenouvelable = estRenouvelable;
        this.type = type;
    }
    public Energie( String nom, String description, Date dateCreation, ObjectifDurabilite objectif,boolean estRenouvelable, String type, String uniteDeMesure, double utilisationReference, double utilisationActuelle, double coutParUnite) throws ObjectifInvalideException {
        super( nom, description, dateCreation, objectif,uniteDeMesure, utilisationReference, utilisationActuelle, coutParUnite);
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
        return "\nEnergie{" + super.toString()+", estRenouvelable=" + estRenouvelable + ", type=" + type + '}';
    }
    
}
