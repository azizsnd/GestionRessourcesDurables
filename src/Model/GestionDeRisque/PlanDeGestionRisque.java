package Model.GestionDeRisque;


import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class PlanDeGestionRisque {
    private List<RisqueEnviromental> risques;
    private List<String> strategiesMitigation;
    private String responsableGestion;
    private Date dateEvaluation;

    public PlanDeGestionRisque( String responsableGestion, Date dateEvaluation) {
        this.risques = new LinkedList<RisqueEnviromental>();
        this.strategiesMitigation = new LinkedList<String>();
        this.responsableGestion = responsableGestion;
        this.dateEvaluation = dateEvaluation;
    }

    public List<RisqueEnviromental> getRisques() {
        return risques;
    }

    public void setRisques(List<RisqueEnviromental> risques) {
        this.risques = risques;
    }

    public List<String> getStrategiesMitigation() {
        return strategiesMitigation;
    }

    public void setStrategiesMitigation(List<String> strategiesMitigation) {
        this.strategiesMitigation = strategiesMitigation;
    }

    public String getResponsableGestion() {
        return responsableGestion;
    }

    public void setResponsableGestion(String responsableGestion) {
        this.responsableGestion = responsableGestion;
    }

    public Date getDateEvaluation() {
        return dateEvaluation;
    }

    public void setDateEvaluation(Date dateEvaluation) {
        this.dateEvaluation = dateEvaluation;
    }

    public void evaluerEfficacite() {
        System.out.println("Évaluation des risques : ");
        for (RisqueEnviromental risque : risques) {
            System.out.println("Risque : " + risque.getNom() + ", Gravité : " + risque.evaluerGravite());
        }
    }

    @Override
    public String toString() {
        return "PlanDeGestionRisque{" + "risques=" + risques + ", strategiesMitigation=" + strategiesMitigation + ", responsableGestion=" + responsableGestion + ", dateEvaluation=" + dateEvaluation + '}';
    }
    
}

