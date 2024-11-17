package Model. serviceSuivi;

import Model.entiteDurable.EmpreinteCarbone;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceSuiviCarbone extends ServiceSuivi {
    private List<EmpreinteCarbone> empreintesSuivis;
    private double emissionTotal;
    private double reductionCibleTotal;
    private String sourcePrincipalEmission;

    public ServiceSuiviCarbone(String nom, int frequenceRapport, Date dernierDateSuivi, String statusService) {
        super(nom, frequenceRapport, dernierDateSuivi, statusService);
        this.empreintesSuivis =  new ArrayList<>();
    }

    public List<EmpreinteCarbone> getEmpreintesSuivis() {
        return empreintesSuivis;
    }

    public void setEmpreintesSuivis(List<EmpreinteCarbone> empreintesSuivis) {
        this.empreintesSuivis = empreintesSuivis;
    }

    public double getEmissionTotal() {
        return emissionTotal;
    }

    public void setEmissionTotal(double emissionTotal) {
        this.emissionTotal = emissionTotal;
    }

    public double getReductionCibleTotal() {
        return reductionCibleTotal;
    }

    public void setReductionCibleTotal(double reductionCibleTotal) {
        this.reductionCibleTotal = reductionCibleTotal;
    }

    public String getSourcePrincipalEmission() {
        return sourcePrincipalEmission;
    }

    public void setSourcePrincipalEmission(String sourcePrincipalEmission) {
        this.sourcePrincipalEmission = sourcePrincipalEmission;
    }

    public String getStatusEmpreinte(EmpreinteCarbone empreinte) {
        if (empreinte.getObjectif() != null) {
            if (empreinte.getObjectif().objectifEstAtteint()) {
                return "L'objectif de réduction pour l'empreinte carbone " + empreinte.getNom() + " est atteint.";
            } else {
                return "L'objectif de réduction pour l'empreinte carbone " + empreinte.getNom() + " n'est pas atteint. Progrès restant : " + empreinte.getObjectif().getProgresRestant();
            }
        } else {
            return "Aucun objectif de réduction défini pour l'empreinte carbone " + empreinte.getNom() + ".";
        }
    }

    public void addEmpreinteCarbone(EmpreinteCarbone empreinteCarbone) {
        empreintesSuivis.add(empreinteCarbone);        
        emissionTotal += empreinteCarbone.getEmissionActuelle();
    }

    @Override
    public void suivi() {
        emissionTotal = 0;
        reductionCibleTotal=0;
        for (EmpreinteCarbone empreinte : empreintesSuivis) {
            emissionTotal += empreinte.getEmissionActuelle();
            emissionTotal += empreinte.getObjectif().getReductionCible();
        }
    }
    @Override    
    public String genererRapport() {
        double emissionsTotales = 0;
        double reductionsTotales = 0;

        for (EmpreinteCarbone empreinte : empreintesSuivis) {
            emissionsTotales += empreinte.getEmissionActuelle();
            reductionsTotales += empreinte.calculerTauxReduction();
        }

        return String.format("Rapport de Suivi Carbone:\n" +
                             "Émissions totales actuelles : %.2f unités\n" +
                             "Réduction nécessaire pour atteindre les objectifs : %.2f unités\n" +
                             "Nombre de sources d'émissions suivies : %d",
                             emissionsTotales, reductionsTotales, empreintesSuivis.size());
    }

    @Override
    public String toString() {
        return "ServiceSuiviCarbone{" +super.toString()+" "+  "empreintesSuivis=" + empreintesSuivis + ", emissionTotal=" + emissionTotal + ", reductionCibleTotal=" + reductionCibleTotal + ", sourcePrincipalEmission=" + sourcePrincipalEmission + '}';
    }

}

