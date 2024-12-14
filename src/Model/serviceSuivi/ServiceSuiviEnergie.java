package Model.serviceSuivi;

import Model.entiteDurable.Energie;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class ServiceSuiviEnergie extends ServiceSuivi{
    private List<Energie> sourcesEnergie;
    private double consommationTotalEnergie;
    private double pourcentageEnergieRenouvelable;

    public ServiceSuiviEnergie(String nom, int frequenceRapport, Date dernierDateSuivi, String statusService) {
        super(nom, frequenceRapport, dernierDateSuivi, statusService);
        this.sourcesEnergie =  new ArrayList<>();
        this.consommationTotalEnergie = 0.0;
        this.pourcentageEnergieRenouvelable = 0.0;
    }
    public ServiceSuiviEnergie(int id,String nom, int frequenceRapport, Date dernierDateSuivi, String statusService) {
        super(id,nom, frequenceRapport, dernierDateSuivi, statusService);
        this.sourcesEnergie =  new ArrayList<>();
        this.consommationTotalEnergie = 0.0;
        this.pourcentageEnergieRenouvelable = 0.0;
    }
    public List<Energie> getSourcesEnergie() {
        return sourcesEnergie;
    }

    public void setSourcesEnergie(List<Energie> sourcesEnergie) {
        this.sourcesEnergie = sourcesEnergie;
    }

    public double getConsommationTotalEnergie() {
        return consommationTotalEnergie;
    }

    public void setConsommationTotalEnergie(double consommationTotalEnergie) {
        this.consommationTotalEnergie = consommationTotalEnergie;
    }
    public double getPourcentageEnergieRenouvelable() {
        return pourcentageEnergieRenouvelable;
    }

    public void setPourcentageEnergieRenouvelable(double pourcentageEnergieRenouvelable) {
        this.pourcentageEnergieRenouvelable = pourcentageEnergieRenouvelable;
    }

    public void addEnergie(Energie energie) {
        sourcesEnergie.add(energie);
        consommationTotalEnergie += energie.getUtilisationActuelle();
    }

    public double calculerPourcentageEnergieRenouvelable() {
        double renouvelableQte = sourcesEnergie.stream()
            .filter(Energie::estRenouvelable) // Filtrer les énergies renouvelables
            .mapToDouble(Energie::getUtilisationActuelle) // Récupérer la quantité d'énergie renouvelable
            .sum();
        
        pourcentageEnergieRenouvelable=(renouvelableQte/ consommationTotalEnergie) * 100;
        return pourcentageEnergieRenouvelable;
    }

    public String getStatusEnergie() {
        return "Pourcentage d'énergie renouvelable : " + calculerPourcentageEnergieRenouvelable() + "%";
    }

    @Override
    public void suivi() {
        consommationTotalEnergie = sourcesEnergie.stream()
            .mapToDouble(Energie::getUtilisationActuelle) 
            .sum();
    }
    @Override
    public String genererRapport() {
        double consommationTotale = sourcesEnergie.stream()
            .mapToDouble(Energie::getUtilisationActuelle) 
            .sum();

        long renouvelableCount = sourcesEnergie.stream()
            .filter(Energie::estRenouvelable)
            .count();

        double pourcentageRenouvelable = sourcesEnergie.size() > 0 ?
                (double) renouvelableCount / sourcesEnergie.size() * 100 : 0;

        return String.format("Rapport de Suivi de l'Énergie:\n" +
                             "Quantité totale d'énergie produite: %.2f unités\n" +
                             "Pourcentage d'énergie renouvelable: %.2f%%\n" +
                             "Nombre de sources d'énergie suivies: %d\n" +  
                             "Les sources d'énergie suivies :\n%s", 
                             consommationTotale, pourcentageRenouvelable, sourcesEnergie.size(), sourcesEnergie);
    }

    @Override
    public String toString() {
        return "ServiceSuiviEnergie{" +super.toString()+" "+ "sourcesEnergie=" 
                + sourcesEnergie + ", consommationTotalEnergie=" + consommationTotalEnergie
                + ", pourcentageEnergieRenouvelable=" + pourcentageEnergieRenouvelable + '}';
    }
    
}
