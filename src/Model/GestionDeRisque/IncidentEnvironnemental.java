package Model.GestionDeRisque;

import java.util.Date;

public final class IncidentEnvironnemental extends RisqueEnviromental {
    private String typeIncident;
    private Date dateIncident;
    private String consequence;

    public IncidentEnvironnemental(String typeIncident, Date dateIncident, String consequence, String nom, String description, double probabilite, double impact, int priorite) {
        super(nom, description, probabilite, impact, priorite);
        this.typeIncident = typeIncident;
        this.dateIncident = dateIncident;
        this.consequence = consequence;
    }

    public String getTypeIncident() {
        return typeIncident;
    }

    public void setTypeIncident(String typeIncident) {
        this.typeIncident = typeIncident;
    }

    public Date getDateIncident() {
        return dateIncident;
    }

    public void setDateIncident(Date dateIncident) {
        this.dateIncident = dateIncident;
    }

    public String getConsequence() {
        return consequence;
    }

    public void setConsequence(String consequence) {
        this.consequence = consequence;
    }

    public void rapporterIncident() {
        System.out.println("Incident rapporté : " + typeIncident);
        System.out.println("Date : " + dateIncident);
        System.out.println("Conséquences : " + consequence);
    }

    @Override
    public String toString() {
        return "IncidentEnvironnemental{" + "typeIncident=" + typeIncident + ", dateIncident=" + dateIncident + ", consequence=" + consequence + '}';
    }
    
}

