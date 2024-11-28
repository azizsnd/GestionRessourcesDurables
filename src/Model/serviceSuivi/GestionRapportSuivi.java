package Model.serviceSuivi;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestionRapportSuivi {
    private int id;
    private List<Rapport> listeRapports;
    private Date dateDernierRapport;
    private int frequenceRapport;
    private String rapportType;
    private ServiceSuivi service;
    
    public GestionRapportSuivi(int frequenceRapport, String rapportType, ServiceSuivi service) {
        this.listeRapports = new ArrayList<>();
        this.frequenceRapport = frequenceRapport;
        this.rapportType = rapportType;
        this.dateDernierRapport = new Date();
        this.service = service;
    }
    public GestionRapportSuivi(int id,int frequenceRapport, String rapportType, ServiceSuivi service) {
        this.id = id; 
        this.listeRapports = new ArrayList<>();
        this.frequenceRapport = frequenceRapport;
        this.rapportType = rapportType;
        this.dateDernierRapport = new Date();
        this.service = service;
    }
    public ServiceSuivi getService() {
        return service;
    }

    public void setService(ServiceSuivi service) {
        this.service = service;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public List<Rapport> getListeRapports() {
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

    public void setListeRapports(List<Rapport> listeRapports) {
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
    public void genererRapport(String contenuRapport) {
        Rapport rapport = new Rapport(this.id, contenuRapport);
        listeRapports.add(rapport);
        dateDernierRapport = rapport.getDateRapport(); // Met à jour la date du dernier rapport

        // Sauvegarder le rapport dans un fichier
        sauvegarderRapportFichier(rapport);
    }
 
    public void genererRapportPourService() {
        if (service != null) {
            String contenuRapport = service.genererRapport();
            genererRapport(contenuRapport);
        } else {
            System.out.println("Aucun service associé pour générer le rapport.");
        }
    }
    // Sauvegarde d'un rapport dans un fichier
    private void sauvegarderRapportFichier(Rapport rapport) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
        String dateFormatted = dateFormat.format(rapport.getDateRapport());
        String filename = "rapport_" + rapportType + "_" + dateFormatted + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write("ID Rapport : " + rapport.getId());
            writer.newLine();
            writer.write("Date : " + rapport.getDateRapport());
            writer.newLine();
            writer.write("Contenu : " + rapport.getContenuRapport());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erreur lors de l'écriture du rapport dans le fichier : " + e.getMessage());
        }
    }
    // Méthode toString pour afficher l'état de GestionRapportSuivi
    @Override
    public String toString() {
        return "GestionRapportSuivi{" +
                "\nlisteRapports=\n" +
                listeRapports+
                ", \ndateDernierRapport=" + dateDernierRapport +
                ", \nfrequenceRapport=" + frequenceRapport +
                ", \nrapportType='" + rapportType + '\'' +
                ", \nservice=" + (service != null ? service.getNom() : "Aucun service associé") +
                '}';
    }
}

