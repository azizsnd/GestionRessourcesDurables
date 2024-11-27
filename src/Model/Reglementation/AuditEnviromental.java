package Model.Reglementation;

import java.util.HashMap;
import java.util.Map;

public class AuditEnviromental {
    private String entite;
    private Map<Reglementation, Boolean> resultat;

    public AuditEnviromental(String entite) {
        this.entite = entite;
        this.resultat = new HashMap<Reglementation, Boolean>();
    }

    public String getEntite() {
        return entite;
    }

    public void setEntite(String entite) {
        this.entite = entite;
    }

    public Map<Reglementation, Boolean> getResultat() {
        return resultat;
    }

    public void setResultat(Map<Reglementation, Boolean> resultat) {
        this.resultat = resultat;
    }

    public void executerAudit() {
        for (Reglementation reglementation : resultat.keySet()) {
            boolean conforme = reglementation.estActive();
            resultat.put(reglementation, conforme);
        }
    }

    public void genererRapport() {
        System.out.println("Rapport d'audit environnemental pour l'entité : " + entite);
        for (Map.Entry<Reglementation, Boolean> entry : resultat.entrySet()) {
            System.out.println("Règlementation : " + entry.getKey().nom()+ " - Conforme : " + entry.getValue());
        }
    }

    @Override
    public String toString() {
        return "AuditEnviromental{" + "entite=" + entite + ", resultat=" + resultat + '}';
    }
    
    
}
