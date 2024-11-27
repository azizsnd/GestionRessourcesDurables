package Model.serviceSuivi;
import Model.entiteDurable.ObjectifDurabilite;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        long objectifsAtteints = objectifsSuivis.stream()
                .filter(ObjectifDurabilite::objectifEstAtteint)
                .count();
        //L'utilisation de Optional permet d'indiquer explicitement qu'une valeur pourrait être présente ou absente
        // Éviter les erreurs NullPointerException
        Optional<ObjectifDurabilite> objectifPrioritaireOpt = objectifsSuivis.stream()
                .filter(objectif -> !objectif.objectifEstAtteint())
                .min((o1, o2) -> Double.compare(o1.getProgresRestant(), o2.getProgresRestant()));

        String objectifPrioritaireInfo = objectifPrioritaireOpt.map(objectif -> 
            String.format("Objectif prioritaire : %s, Progrès restant : %.2f", 
                objectif.getDescription(), objectif.getProgresRestant()))
            .orElse("Aucun objectif prioritaire non atteint.");

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

