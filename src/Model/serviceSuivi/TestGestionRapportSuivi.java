package Model.serviceSuivi;

/**
 *
 * @author HP
 */
import java.util.Date;

public class TestGestionRapportSuivi {

    public static void main(String[] args) {
        // Instanciation d'une classe concrète de ServiceSuivi : ServiceSuiviDechet
        ServiceSuiviDechet serviceDechet = new ServiceSuiviDechet(
                1,
                "Service de Suivi des Déchets",
                7,
                new Date(),
                "Actif"
        );

        // Instanciation de GestionRapportSuivi
        GestionRapportSuivi gestionRapport = new GestionRapportSuivi(
                1,
                7,
                "Déchets",
                serviceDechet
        );

        // Génération d'un rapport simple
        System.out.println("Génération d'un rapport simple...");
        gestionRapport.genererRapport("Rapport initial sur les déchets.");
        System.out.println(gestionRapport);

        // Génération d'un rapport basé sur le service associé
        System.out.println("Génération d'un rapport pour le service de suivi des déchets...");
        gestionRapport.genererRapportPourService();
        System.out.println(gestionRapport);

        // Vérification des méthodes spécifiques au service (exemple : calcul de taux de recyclage moyen)
        System.out.println("Détails du service de suivi des déchets :");
        System.out.println(serviceDechet);
    }

    private static void afficherRapports(GestionRapportSuivi gestionRapport) {
        System.out.println("\nRapports générés :");
        gestionRapport.getListeRapports().forEach(rapport -> {
            System.out.println("ID Rapport : " + rapport.getId());
            System.out.println("Date : " + rapport.getDateRapport());
            System.out.println("Contenu : " + rapport.getContenuRapport());
            System.out.println("--------------------------");
        });
    }
}