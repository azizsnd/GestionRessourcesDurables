package entiteDurable;

import java.util.Date;

public class Dechet extends EntiteDurable {
    private String type;
    private double quantiteProduite;
    private double quantiteRecycle;
    private String methodeElimination;

    public Dechet(String type, double quantiteProduite, double quantiteRecycle, String methodeElimination, ObjectifDurabilite objectif, int id, String nom, String description, Date dateCreation) {
        super(id, nom, description, dateCreation,objectif);
        this.type = type;
        this.quantiteProduite = quantiteProduite;
        this.quantiteRecycle = quantiteRecycle;
        this.methodeElimination = methodeElimination;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getQuantiteProduite() {
        return quantiteProduite;
    }

    public void setQuantiteProduite(double quantiteProduite) {
        this.quantiteProduite = quantiteProduite;
    }

    public double getQuantiteRecycle() {
        return quantiteRecycle;
    }

    public void setQuantiteRecycle(double quantiteRecycle) {
        this.quantiteRecycle = quantiteRecycle;
    }

    public String getMethodeElimination() {
        return methodeElimination;
    }

    public void setMethodeElimination(String methodeElimination) {
        this.methodeElimination = methodeElimination;
    }

    public double calculerTauxRecyclage() {
        getObjectif().setProgresActuel((this.quantiteRecycle/this.quantiteProduite)*100);
        return getObjectif().getProgresActuel();
    }

    @Override
    public String toString() {
        return "Dechet{" + super.toString()+ ' '+"type=" + type + ", quantiteProduite=" + quantiteProduite + ", quantiteRecycle=" + quantiteRecycle + ", methodeElimination=" + methodeElimination + '}';
    }

}
