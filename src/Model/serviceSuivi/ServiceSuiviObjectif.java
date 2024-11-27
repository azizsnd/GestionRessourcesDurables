package Model.serviceSuivi;
import Model.entiteDurable.ObjectifDurabilite;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class ServiceSuiviObjectif extends ServiceSuivi {
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
        objectifsSuivis.forEach(objectif -> System.out.println(getStatusObjectif(objectif)));
    }

    @Override
    public void suivi() {
        nbreObjectifAtteints = (int) objectifsSuivis.stream()
                .filter(ObjectifDurabilite::objectifEstAtteint)
                .count();
    }   
    @Override
    public String genererRapport() {
        int objectifsAtteints = 0;
        ObjectifDurabilite objectifPrioritaire = null;
        double progresRestantMin = Double.MAX_VALUE;

        for (ObjectifDurabilite objectif : objectifsSuivis) {
            if (objectif.objectifEstAtteint()) {
                objectifsAtteints++;
            } else if (objectif.getProgresRestant() < progresRestantMin) {
                progresRestantMin = objectif.getProgresRestant();
                objectifPrioritaire = objectif;
            }
        }

        String objectifPrioritaireInfo = (objectifPrioritaire != null) ?
                String.format("Objectif prioritaire : %s, Progrès restant : %.2f",
                        objectifPrioritaire.getDescription(), objectifPrioritaire.getProgresRestant())
                : "Aucun objectif prioritaire non atteint.";

        return String.format("Rapport de Suivi des Objectifs:\n" +
                             "Nombre total d'objectifs : %d\n" +
                             "Nombre d'objectifs atteints : %d\n" +
                             "%s",
                             objectifsSuivis.size(), objectifsAtteints, objectifPrioritaireInfo);
    }

    @Override
    public String toString() {
        return "ServiceSuiviObjectif{" +super.toString()+" "+  "objectifsSuivis=" + objectifsSuivis + ", nbreObjectifAtteints=" + nbreObjectifAtteints + ", objectifPrioritaire=" + objectifPrioritaire + '}';
    }
    
}

