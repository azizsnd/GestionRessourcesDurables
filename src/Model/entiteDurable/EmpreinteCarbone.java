package Model.entiteDurable;

import java.util.Date;

public final class EmpreinteCarbone extends EntiteDurable {
    private String sourceEmission;
    private double emissionAnnuelles;
    private double emissionActuelle;

    public EmpreinteCarbone(int id, String nom, String description, Date dateCreation,ObjectifDurabilite objectif, String sourceEmission, double emissionAnnuelles,double emissionActuelle)throws ObjectifInvalideException{
        super(id, nom, description, dateCreation,objectif);
        this.sourceEmission = sourceEmission;
        this.emissionAnnuelles = emissionAnnuelles;
        this.emissionActuelle = emissionActuelle;
    }

    public String getSourceEmission() {
        return sourceEmission;
    }

    public void setSourceEmission(String sourceEmission) {
        this.sourceEmission = sourceEmission;
    }

    public double getEmissionAnnuelles() {
        return emissionAnnuelles;
    }

    public void setEmissionAnnuelles(double emissionAnnuelles) {
        this.emissionAnnuelles = emissionAnnuelles;
    }

    public double getEmissionActuelle() {
        return emissionActuelle;
    }

    public void setEmissionActuelle(double emissionActuelle) {
        this.emissionActuelle = emissionActuelle;
    }

    public double calculerTauxReduction() {
        getObjectif().setProgresActuel(((emissionAnnuelles - emissionActuelle)/emissionAnnuelles)*100);
        return getObjectif().getProgresActuel();
    }
    public void reinitialiseEmission() {
        this.emissionActuelle = 0;
    }

    public void mettreAJourEmission(double emissions) {
        this.emissionActuelle += emissions;
    }
    @Override
    public String toString() {
        return "EmpreinteCarbone{" + super.toString()+ ' '+"sourceEmission=" + sourceEmission + ", emissionAnnuelles=" + emissionAnnuelles +", emissionActuelle=" + emissionActuelle + '}';
    }

}
