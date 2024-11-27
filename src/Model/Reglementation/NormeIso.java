package Model.Reglementation;

import java.util.LinkedList;
import java.util.List;

public class NormeIso {
    private int numISO;
    private String descriptionNorme;
    private List<Reglementation> exigences;

    public NormeIso(int numISO, String descriptionNorme) {
        this.numISO = numISO;
        this.descriptionNorme = descriptionNorme;
        this.exigences = new LinkedList<Reglementation>();
    }

    public int getNumISO() {
        return numISO;
    }

    public void setNumISO(int numISO) {
        this.numISO = numISO;
    }

    public String getDescriptionNorme() {
        return descriptionNorme;
    }

    public void setDescriptionNorme(String descriptionNorme) {
        this.descriptionNorme = descriptionNorme;
    }

    public List<Reglementation> getExigences() {
        return exigences;
    }

    public void setExigences(List<Reglementation> exigences) {
        this.exigences = exigences;
    }

    public boolean verifierConformite() {
        for (Reglementation reglementation : exigences) {
            if (!reglementation.estActive()) {
                return false;
            }
        }
        return true;
    }

    public void genererRapport() {
        System.out.println("Rapport de conformité pour la norme ISO : " + numISO);
        for (Reglementation reglementation : exigences) {
            System.out.println("Règlementation : " + reglementation.nom()+ " - Conforme : " + reglementation.estActive());
        }
    }

    @Override
    public String toString() {
        return "NormeIso{" + "numISO=" + numISO + ", descriptionNorme=" + descriptionNorme + ", exigences=" + exigences + '}';
    }

}
