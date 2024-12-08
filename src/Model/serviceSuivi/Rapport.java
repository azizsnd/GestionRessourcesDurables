package Model.serviceSuivi;

import java.util.Date;

public class Rapport {
    private int id;
    private String typeService;
    private String contenuRapport;    
    private Date dateRapport;        

    public Rapport(String typeService, String contenuRapport) {
        this.typeService = typeService;
        this.contenuRapport = contenuRapport;
        this.dateRapport = new Date();  
    }

    public Rapport(int id, String typeService, String contenuRapport, Date dateRapport) {
        this.id = id;
        this.typeService = typeService;
        this.contenuRapport = contenuRapport;
        this.dateRapport = dateRapport;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeService() {
        return typeService;
    }

    public void setTypeService(String typeService) {
        this.typeService = typeService;
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
                ", typeService=" + typeService +
                ", contenuRapport='" + contenuRapport + '\'' +
                ", dateRapport=" + dateRapport +
                "}\n";
    }
}

