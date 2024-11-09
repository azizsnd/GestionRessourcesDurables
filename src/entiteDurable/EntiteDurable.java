package entiteDurable;

import java.util.Date;

public abstract class EntiteDurable {
    private int id;
    private String nom;
    private String description;
    private Date dateCreation;
    private ObjectifDurabilite objectif; 


    public EntiteDurable(int id, String nom, String description, Date dateCreation,ObjectifDurabilite objectif) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.dateCreation = dateCreation;
        this.objectif = objectif;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public ObjectifDurabilite getObjectif() {
        return objectif;
    }

    public void setObjectif(ObjectifDurabilite objectif) {
        this.objectif = objectif;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
    public boolean verifierObjectifAtteint() {
        return objectif != null && objectif.objectifEstAtteint();
    }

    public double getProgresRestant() {
        return objectif != null ? objectif.getProgresRestant() : 0;
    }

    @Override
    public String toString() {
        return "EntiteDurable{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", dateCreation=" + dateCreation + ", objectif=" + objectif + '}';
    }
        
}
