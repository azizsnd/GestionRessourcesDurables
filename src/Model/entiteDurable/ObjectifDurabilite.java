package Model.entiteDurable;

import java.util.Date;

 public class ObjectifDurabilite {
    private int id;
    private Date dateCible;
    private double reductionCible; //taux
    private double progresActuel;  //taux
    private String description;

    public ObjectifDurabilite(Date dateCible, double reductionCible, double progresActuel, String description) {
        this.dateCible = dateCible;
        this.reductionCible = reductionCible;
        this.progresActuel = progresActuel;
        this.description = description;
    }
    public ObjectifDurabilite(int id,Date dateCible, double reductionCible, double progresActuel, String description) {
        this.id = id;
        this.dateCible = dateCible;
        this.reductionCible = reductionCible;
        this.progresActuel = progresActuel;
        this.description = description;
    }
    public Date getDateCible() {
        return dateCible;
    }

    public void setDateCible(Date dateCible) {
        this.dateCible = dateCible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getReductionCible() {
        return reductionCible;
    }

    public void setReductionCible(double reductionCible) {
        this.reductionCible = reductionCible;
    }

    public double getProgresActuel() {
        return progresActuel;
    }

    public void setProgresActuel(double progresActuel) {
        this.progresActuel = progresActuel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean objectifEstAtteint() {
        return progresActuel >= reductionCible;
    }

    public double getProgresRestant() {
        return reductionCible - progresActuel;
    }

    public void suivreProgres(double progres) {
        this.progresActuel += progres;
    }

    @Override
    public String toString() {
        return "\nObjectifDurabilite{" + "id=" + id + ", dateCible=" + dateCible + ", reductionCible=" + reductionCible + ", progresActuel=" + progresActuel + ", description=" + description + "}";
    }



}
