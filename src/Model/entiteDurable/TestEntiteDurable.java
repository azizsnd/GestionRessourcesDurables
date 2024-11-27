import Model.entiteDurable.ObjectifDurabilite;
import Model.entiteDurable.ObjectifInvalideException;
import Model.entiteDurable.Ressource;

import java.util.Date;

public class TestEntiteDurable {
    public static void main(String[] args) {
        try {
            ObjectifDurabilite objectif = new ObjectifDurabilite(
                    new Date(),
                    50.0,
                    20.0,
                    "Réduction de l'utilisation d'énergie"
            );

            Ressource ressource = new Ressource(
                    1,
                    "Ressource Energie",
                    "Une ressource énergétique",
                    new Date(),
                    objectif,
                    "kWh",
                    1000,
                    800,
                    0.1
            );

            System.out.println("Objectif atteint : " + ressource.verifierObjectifAtteint());
            System.out.println("Progrès restant : " + ressource.getProgresRestant());
            System.out.println("Taux de réduction calculé : " + ressource.calculerTauxReduction() + "%");
            System.out.println(ressource.toString());
        }  catch (ObjectifInvalideException e) {
            System.out.println("Erreur : " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erreur inattendue : " + e.getMessage());
        }
    }
}
