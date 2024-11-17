package Model.serviceSuivi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestionRapportSuivi {
    private List<String> listeRapports;
    private Date dateDernierRapport;
    private int frequenceRapport;
    private String rapportType;

    // Constructeur
    public GestionRapportSuivi(int frequenceRapport, String rapportType) {
        this.listeRapports = new ArrayList<>();
        this.frequenceRapport = frequenceRapport;
        this.rapportType = rapportType;
        this.dateDernierRapport = new Date();
    }

    // Génère un rapport personnalisé et l'ajoute à la liste
    public void genererRapport(String contenuRapport) {
        String rapport = "Rapport (" + rapportType + ") - " + new Date() + " : " + contenuRapport;
        listeRapports.add(rapport);
        dateDernierRapport = new Date(); // Met à jour la date du dernier rapport
    }

    // Génère automatiquement un rapport pour un service spécifique
    public void genererRapportPourService(ServiceSuivi service) {
        String contenuRapport = service.genererRapport();
        genererRapport(contenuRapport);
    }

    // Accesseurs
    public List<String> getListeRapports() {
        return listeRapports;
    }

    public Date getDateDernierRapport() {
        return dateDernierRapport;
    }

    public int getFrequenceRapport() {
        return frequenceRapport;
    }

    public String getRapportType() {
        return rapportType;
    }

    // Mutateurs
    public void setListeRapports(List<String> listeRapports) {
        this.listeRapports = listeRapports;
    }

    public void setDateDernierRapport(Date dateDernierRapport) {
        this.dateDernierRapport = dateDernierRapport;
    }

    public void setFrequenceRapport(int frequenceRapport) {
        this.frequenceRapport = frequenceRapport;
    }

    public void setRapportType(String rapportType) {
        this.rapportType = rapportType;
    }

    // Méthode toString pour afficher l'état de GestionRapportSuivi
    @Override
    public String toString() {
        return "GestionRapportSuivi{" +
                "listeRapports=" + listeRapports +
                ", dateDernierRapport=" + dateDernierRapport +
                ", frequenceRapport=" + frequenceRapport +
                ", rapportType='" + rapportType + '\'' +
                '}';
    }
}
