package Model.serviceSuivi;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
        String rapport = "Rapport (" + rapportType + ") - " + new Date().toString() + " : " + contenuRapport;
        listeRapports.add(rapport);
        dateDernierRapport = new Date(); // Met à jour la date du dernier rapport
        
        // Utilisation du try-with-resources pour écrire dans un fichier
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
        String dateFormatted = dateFormat.format(new Date());

        // Utilisation du try-with-resources pour écrire dans un fichier
        String filename = "rapport_" + rapportType + "_" + dateFormatted + ".txt"; 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(rapport);
            writer.newLine();  // Ajoute une nouvelle ligne après chaque rapport
        } catch (IOException e) {
            System.out.println("Erreur lors de l'écriture du rapport dans le fichier : " + e.getMessage());
        }
    }

    public void genererRapportPourService(ServiceSuivi service) {
        String contenuRapport = service.genererRapport();
        genererRapport(contenuRapport);
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

