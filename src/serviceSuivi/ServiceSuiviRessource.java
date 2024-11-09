package serviceSuivi;

import entiteDurable.Ressource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceSuiviRessource extends ServiceSuivi{
    private List<Ressource> resourcesSuivis;
    private int nbreRessourceSuivi;
    private double coutTotalRessource;

    public ServiceSuiviRessource(String nom, int frequenceRapport, Date dernierDateSuivi, String statusService) {
        super(nom, frequenceRapport, dernierDateSuivi, statusService);
        this.resourcesSuivis =  new ArrayList<>();
        this.nbreRessourceSuivi = 0;
    }

    public List<Ressource> getResourcesSuivis() {
        return resourcesSuivis;
    }

    public void setResourcesSuivis(List<Ressource> resourcesSuivis) {
        this.resourcesSuivis = resourcesSuivis;
    }

    public int getNbreRessourceSuivi() {
        return nbreRessourceSuivi;
    }

    public void setNbreRessourceSuivi(int nbreRessourceSuivi) {
        this.nbreRessourceSuivi = nbreRessourceSuivi;
    }

    public double getCoutTotalRessource() {
        return coutTotalRessource;
    }

    public void setCoutTotalRessource(double coutTotalRessource) {
        this.coutTotalRessource = coutTotalRessource;
    }

    public void addRessource(Ressource ressource) {
        resourcesSuivis.add(ressource);
        nbreRessourceSuivi++;
    }
    
    public String getStatusRessource(Ressource ressource) {
        if (ressource.getObjectif() != null) {
            if (ressource.getObjectif().objectifEstAtteint()) {
                return "L'objectif de durabilité pour la ressource " + ressource.getNom() + " est atteint.";
            } else {
                return "L'objectif de durabilité pour la ressource " + ressource.getNom() + " n'est pas encore atteint. Progrès restant : " + ressource.getObjectif().getProgresRestant();
            }
        } else {
            return "Aucun objectif de durabilité défini pour la ressource " + ressource.getNom() + ".";
        }    }

    @Override
    public void suivi() {
        for (Ressource ressource : resourcesSuivis) {
            coutTotalRessource += ressource.calculerCoutTotal();
        }
    }

    @Override
    public String toString() {
        return "ServiceSuiviRessource{" + super.toString()+" "+ "resourcesSuivis=" + resourcesSuivis + ", nbreRessourceSuivi=" + nbreRessourceSuivi + ", coutTotalRessource=" + coutTotalRessource + '}';
    }
    
}
