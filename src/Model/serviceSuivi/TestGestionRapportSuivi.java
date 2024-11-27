package Model.serviceSuivi;

/**
 *
 * @author HP
 */
public class TestGestionRapportSuivi {
    public static void main(String[] args) {
        GestionRapportSuivi gestionRapport = new GestionRapportSuivi(7, "Environnemental");

        // Générer un rapport simple
        gestionRapport.genererRapport("Contenu du rapport environnemental pour le mois de novembre.");

        System.out.println("Liste des rapports générés :");
        gestionRapport.getListeRapports().forEach(System.out::println);

    }
}