package Model.serviceSuivi;
import Model.entiteDurable.Dechet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class ServiceSuiviDechet extends ServiceSuivi {
    private List<Dechet> dechetSuivis;
    private double tauxRecyclageMoyenne;
    private double quantiteTotalDechet;

    public ServiceSuiviDechet( String nom, int frequenceRapport, Date dernierDateSuivi, String statusService) {
        super(nom, frequenceRapport, dernierDateSuivi, statusService);
        this.dechetSuivis = new ArrayList<>();
        this.tauxRecyclageMoyenne = 0.0;
        this.quantiteTotalDechet = 0.0;
    }
    public ServiceSuiviDechet(int id,String nom, int frequenceRapport, Date dernierDateSuivi, String statusService) {
        super(id,nom, frequenceRapport, dernierDateSuivi, statusService);
        this.dechetSuivis = new ArrayList<>();
        this.tauxRecyclageMoyenne = 0.0;
        this.quantiteTotalDechet = 0.0;
    }
    public List<Dechet> getDechetSuivis() {
        return dechetSuivis;
    }

    public void setDechetSuivis(List<Dechet> dechetSuivis) {
        this.dechetSuivis = dechetSuivis;
    }

    public double getTauxRecyclageMoyenne() {
        return tauxRecyclageMoyenne;
    }

    public void setTauxRecyclageMoyenne(double tauxRecyclageMoyenne) {
        this.tauxRecyclageMoyenne = tauxRecyclageMoyenne;
    }

    public double getQuantiteTotalDechet() {
        return quantiteTotalDechet;
    }

    public void setQuantiteTotalDechet(double quantiteTotalDechet) {
        this.quantiteTotalDechet = quantiteTotalDechet;
    }

    public void mettreAJourDechet(Dechet dechet, double quantite) {
        dechet.setQuantiteProduite(dechet.getQuantiteProduite()+ quantite);
        quantiteTotalDechet += quantite;
    }

    public double calculerTauxRecyclageMoy() {
        tauxRecyclageMoyenne = dechetSuivis.stream()
            .mapToDouble(Dechet::calculerTauxRecyclage) 
            .average() // Calcul de la moyenne
            .orElse(0); // Si la liste est vide, retourner 0
        return tauxRecyclageMoyenne;
    }
    @Override
    public void suivi() {
        calculerTauxRecyclageMoy();
    }
    @Override    
    public String genererRapport() {
        calculerTauxRecyclageMoy(); // Mettre à jour le taux de recyclage moyen
        return String.format("Rapport de Suivi des Déchets:\n" +
                             "Quantité totale de déchets: %.2f\n" +
                             "Taux de recyclage moyen: %.2f%%\n" +
                             "Nombre de types de déchets suivis: %d",
                             quantiteTotalDechet, tauxRecyclageMoyenne, dechetSuivis.size());
    }
    @Override
    public String toString() {
        return "SuiviDechet{" +super.toString()+" "+ "dechetSuivis=" + dechetSuivis 
                + ", tauxRecyclageMoyenne=" + tauxRecyclageMoyenne
                + ", quantiteTotalDechet=" + quantiteTotalDechet + '}';
    }
    
}
