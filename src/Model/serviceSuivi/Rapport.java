package Model.serviceSuivi;

import java.util.Date;

public class Rapport {
    private int id;
    private int gestionRapportSuiviId; 
    private String contenuRapport;    
    private Date dateRapport;        

    public Rapport(int gestionRapportSuiviId, String contenuRapport) {
        this.gestionRapportSuiviId = gestionRapportSuiviId;
        this.contenuRapport = contenuRapport;
        this.dateRapport = new Date();  
    }

    public Rapport(int id, int gestionRapportSuiviId, String contenuRapport, Date dateRapport) {
        this.id = id;
        this.gestionRapportSuiviId = gestionRapportSuiviId;
        this.contenuRapport = contenuRapport;
        this.dateRapport = dateRapport;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGestionRapportSuiviId() {
        return gestionRapportSuiviId;
    }

    public void setGestionRapportSuiviId(int gestionRapportSuiviId) {
        this.gestionRapportSuiviId = gestionRapportSuiviId;
    }

    public String getContenuRapport() {
        return contenuRapport;
    }

    public void setContenuRapport(String contenuRapport) {
        this.contenuRapport = contenuRapport;
    }

    public Date getDateRapport() {
        return dateRapport;
    }

    public void setDateRapport(Date dateRapport) {
        this.dateRapport = dateRapport;
    }

    @Override
    public String toString() {
        return "Rapport{" +
                "id=" + id +
                ", gestionRapportSuiviId=" + gestionRapportSuiviId +
                ", contenuRapport='" + contenuRapport + '\'' +
                ", dateRapport=" + dateRapport +
                "}\n";
    }
}

