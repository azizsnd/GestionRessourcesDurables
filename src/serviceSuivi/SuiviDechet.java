package serviceSuivi;
import entiteDurable.Dechet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SuiviDechet extends ServiceSuivi {
    private List<Dechet> dechetSuivis;
    private double tauxRecyclageMoyenne;
    private double quantiteTotalDechet;

    public SuiviDechet( String nom, int frequenceRapport, Date dernierDateSuivi, String statusService) {
        super(nom, frequenceRapport, dernierDateSuivi, statusService);
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
        double totalRecyclage = 0;
        for (Dechet dechet : dechetSuivis) {
            totalRecyclage += dechet.calculerTauxRecyclage();
        }
        if (!dechetSuivis.isEmpty()) {
            tauxRecyclageMoyenne = totalRecyclage / dechetSuivis.size();
        } else {
            tauxRecyclageMoyenne = 0;
        }
        return tauxRecyclageMoyenne;
    }
    @Override
    public void suivi() {
        calculerTauxRecyclageMoy();
    }

    @Override
    public String toString() {
        return "SuiviDechet{" +super.toString()+" "+ "dechetSuivis=" + dechetSuivis + ", tauxRecyclageMoyenne=" + tauxRecyclageMoyenne + ", quantiteTotalDechet=" + quantiteTotalDechet + '}';
    }
    
}
