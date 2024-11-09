package serviceSuivi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestionRapportSuivi {
    private List<String> listeRapports;
    private Date dateDernierRapport;
    private int frequenceRapport;
    private String rapportType;

    public GestionRapportSuivi(int frequenceRapport, String rapportType) {
        this.listeRapports = new ArrayList<>();
        this.frequenceRapport = frequenceRapport;
        this.rapportType = rapportType;
        this.dateDernierRapport = new Date();
    }

    public void genererRapport(String contenuRapport) {
        String rapport = "Rapport (" + rapportType + ") - " + new Date() + " : " + contenuRapport;
        listeRapports.add(rapport);
        dateDernierRapport = new Date(); 
    }

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
    
    @Override
    public String toString() {
        return "GestionRapportSuivi{" + "listeRapports=" + listeRapports + ", dateDernierRapport=" + dateDernierRapport + ", frequenceRapport=" + frequenceRapport + ", rapportType=" + rapportType + '}';
    }

}

