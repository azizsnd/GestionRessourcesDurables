package serviceSuivi;
import entiteDurable.ObjectifDurabilite;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceSuiviObjectif extends ServiceSuivi {
    private List<ObjectifDurabilite> objectifsSuivis;
    private int nbreObjectifAtteints;
    private ObjectifDurabilite objectifPrioritaire;

    public ServiceSuiviObjectif(String nom, int frequenceRapport, Date dernierDateSuivi, String statusService) {
        super(nom, frequenceRapport, dernierDateSuivi, statusService);
        this.objectifsSuivis =  new ArrayList<>();
        this.nbreObjectifAtteints = 0;
    }
    public List<ObjectifDurabilite> getObjectifsSuivis() {
        return objectifsSuivis;
    }

    public void setObjectifsSuivis(List<ObjectifDurabilite> objectifsSuivis) {
        this.objectifsSuivis = objectifsSuivis;
    }

    public int getNbreObjectifAtteints() {
        return nbreObjectifAtteints;
    }

    public void setNbreObjectifAtteints(int nbreObjectifAtteints) {
        this.nbreObjectifAtteints = nbreObjectifAtteints;
    }

    public ObjectifDurabilite getObjectifPrioritaire() {
        return objectifPrioritaire;
    }

    public void setObjectifPrioritaire(ObjectifDurabilite objectifPrioritaire) {
        this.objectifPrioritaire = objectifPrioritaire;
    }

    public void addObjectif(ObjectifDurabilite objectif) {
        objectifsSuivis.add(objectif);
    }

    public void mettreAJourProgres(ObjectifDurabilite objectif, double progres) {
        objectif.suivreProgres(progres);
        if (objectif.objectifEstAtteint()) {
            nbreObjectifAtteints++;
        }
    }

    public String getStatusObjectif(ObjectifDurabilite objectif) {
        return objectif.objectifEstAtteint() ? "Objectif atteint." : "Objectif non atteint. Progrès restant : " + objectif.getProgresRestant();
    }

    public void afficherSuiviObjectif() {
        for (ObjectifDurabilite objectif : objectifsSuivis) {
            System.out.println(getStatusObjectif(objectif));
        }
    }

    @Override
    public void suivi() {
        for (ObjectifDurabilite objectif : objectifsSuivis) {
            if (objectif.objectifEstAtteint()) {
                nbreObjectifAtteints++;
            }
        }
    }

    @Override
    public String toString() {
        return "ServiceSuiviObjectif{" +super.toString()+" "+  "objectifsSuivis=" + objectifsSuivis + ", nbreObjectifAtteints=" + nbreObjectifAtteints + ", objectifPrioritaire=" + objectifPrioritaire + '}';
    }
    
}

