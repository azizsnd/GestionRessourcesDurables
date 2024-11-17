package Model.Reglementation;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class PlanDActionCorrectif {
    private List<Reglementation> reglementationsCibles;
    private List<String> actions;
    private Date date;
    private String responsable;

    public PlanDActionCorrectif(Date date, String responsable) {
        this.reglementationsCibles = new LinkedList<Reglementation>();
        this.actions = new LinkedList<String>();
        this.date = date;
        this.responsable = responsable;
    }

    public List<Reglementation> getReglementationsCibles() {
        return reglementationsCibles;
    }

    public void setReglementationsCibles(List<Reglementation> reglementationsCibles) {
        this.reglementationsCibles = reglementationsCibles;
    }

    public List<String> getActions() {
        return actions;
    }

    public void setActions(List<String> actions) {
        this.actions = actions;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public void afficher() {
        System.out.println("Plan d'Action Correctif :");
        System.out.println("Responsable : " + responsable);
        System.out.println("Date : " + date);
        System.out.println("Actions à prendre : " + actions);
        for (Reglementation reglementation : reglementationsCibles) {
            System.out.println("Règlementation ciblée : " + reglementation.getNom());
        }
    }

    @Override
    public String toString() {
        return "PlanDActionCorrectif{" + "reglementationsCibles=" + reglementationsCibles + ", actions=" + actions + ", date=" + date + ", responsable=" + responsable + '}';
    }
    
}
