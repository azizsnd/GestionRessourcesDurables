package Model.serviceSuivi;

import Model.entiteDurable.Energie;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceSuiviEnergie extends ServiceSuivi{
    private List<Energie> sourcesEnergie;
    private double consommationTotalEnergie;
    private double pourcentageEnergieRenouvelable;

    public ServiceSuiviEnergie(String nom, int frequenceRapport, Date dernierDateSuivi, String statusService) {
        super(nom, frequenceRapport, dernierDateSuivi, statusService);
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
        double renouvelableQte=0.0;
        for (Energie energie : sourcesEnergie) {
            if (energie.estRenouvelable()) {
                renouvelableQte=energie.getUtilisationActuelle();
            }
        }
        pourcentageEnergieRenouvelable=(renouvelableQte/ consommationTotalEnergie) * 100;
        return pourcentageEnergieRenouvelable;
    }

    public String getStatusEnergie() {
        return "Pourcentage d'énergie renouvelable : " + calculerPourcentageEnergieRenouvelable() + "%";
    }

    @Override
    public void suivi() {
        consommationTotalEnergie = 0;
        for (Energie energie : sourcesEnergie) {
            consommationTotalEnergie += energie.getUtilisationActuelle();
        }
    }
    @Override
    public String genererRapport() {
        double consommationTotale = 0;
        int renouvelableCount = 0;

        for (Energie energie : sourcesEnergie) {
            consommationTotale += energie.getUtilisationActuelle();
            if (energie.estRenouvelable()) {
                renouvelableCount++;
            }
        }

        double pourcentageRenouvelable = sourcesEnergie.size() > 0 ? (double) renouvelableCount / sourcesEnergie.size() * 100 : 0;

        return String.format("Rapport de Suivi de l'Énergie:\n" +
                             "Quantité totale d'énergie produite: %.2f unités\n" +
                             "Pourcentage d'énergie renouvelable: %.2f%%\n" +
                             "Nombre de sources d'énergie suivies: %d",
                             consommationTotale, pourcentageRenouvelable, sourcesEnergie.size());
    }
    @Override
    public String toString() {
        return "ServiceSuiviEnergie{" +super.toString()+" "+ "sourcesEnergie=" + sourcesEnergie + ", consommationTotalEnergie=" + consommationTotalEnergie + ", pourcentageEnergieRenouvelable=" + pourcentageEnergieRenouvelable + '}';
    }
    
}
