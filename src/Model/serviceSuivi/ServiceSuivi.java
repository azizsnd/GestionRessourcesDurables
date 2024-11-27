package Model.serviceSuivi;

import java.util.Date;

public sealed abstract class ServiceSuivi permits ServiceSuiviCarbone,ServiceSuiviDechet,ServiceSuiviEnergie,ServiceSuiviObjectif,ServiceSuiviRessource {
    private String nom;
    private int frequenceRapport;
    private Date dernierDateSuivi;
    private String statusService;

    public ServiceSuivi(String nom, int frequenceRapport, Date dernierDateSuivi, String statusService) {
        this.nom = nom;
        this.frequenceRapport = frequenceRapport;
        this.dernierDateSuivi = dernierDateSuivi;
        this.statusService = statusService;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getFrequenceRapport() {
        return frequenceRapport;
    }

    public void setFrequenceRapport(int frequenceRapport) {
        this.frequenceRapport = frequenceRapport;
    }

    public Date getDernierDateSuivi() {
        return dernierDateSuivi;
    }

    public void setDernierDateSuivi(Date dernierDateSuivi) {
        this.dernierDateSuivi = dernierDateSuivi;
    }

    public String getStatusService() {
        return statusService;
    }

    public void setStatusService(String statusService) {
        this.statusService = statusService;
    }

    public abstract void suivi();
    public abstract String genererRapport();

    @Override
    public String toString() {
        return "ServiceSuivi{" + "nom=" + nom + ", frequenceRapport=" + frequenceRapport + ", dernierDateSuivi=" + dernierDateSuivi + ", statusService=" + statusService + '}';
    }

}
