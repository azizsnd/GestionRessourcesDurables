package Model.Reglementation;

import java.util.LinkedList;
import java.util.List;

public class NormeIso {
    private int id;
    private int numISO;
    private String descriptionNorme;
    private List<Reglementation> exigences;

    // Constructors
    public NormeIso(int numISO, String descriptionNorme) {
        this.setNumISO(numISO);
        this.setDescriptionNorme(descriptionNorme);
        this.exigences = new LinkedList<>();
    }

    public NormeIso(int id, int numISO, String descriptionNorme, List<Reglementation> exigences) {
        this.id = id;
        this.setNumISO(numISO);
        this.setDescriptionNorme(descriptionNorme);
        this.exigences = exigences != null ? exigences : new LinkedList<>();
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumISO() {
        return numISO;
    }

    public void setNumISO(int numISO) {
        if (numISO > 0) {
            this.numISO = numISO;
        } else {
            throw new IllegalArgumentException("ISO number must be positive.");
        }
    }

    public String getDescriptionNorme() {
        return descriptionNorme;
    }

    public void setDescriptionNorme(String descriptionNorme) {
        if (descriptionNorme == null || descriptionNorme.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty.");
        }
        this.descriptionNorme = descriptionNorme;
    }

    public List<Reglementation> getExigences() {
        return exigences;
    }

    public void setExigences(List<Reglementation> exigences) {
        this.exigences = exigences != null ? exigences : new LinkedList<>();
    }

    // Functional Methods
    public boolean verifierConformite() {
        for (Reglementation reglementation : exigences) {
            if (!reglementation.estActive()) {
                return false;
            }
        }
        return true;
    }

    public String genererRapport() {
        StringBuilder rapport = new StringBuilder();
        rapport.append("Rapport de conformité pour la norme ISO : ").append(numISO).append("\n");
        for (Reglementation reglementation : exigences) {
            rapport.append("Règlementation : ").append(reglementation.nom())
                  .append(" - Conforme : ").append(reglementation.estActive() ? "Oui" : "Non").append("\n");
        }
        return rapport.toString();
    }

    public void ajouterExigence(Reglementation reglementation) {
        if (reglementation != null && !exigences.contains(reglementation)) {
            exigences.add(reglementation);
        }
    }

    public void supprimerExigence(Reglementation reglementation) {
        exigences.remove(reglementation);
    }

    @Override
    public String toString() {
        return String.format("ISO %d: %s ", numISO, descriptionNorme);
    }
}
