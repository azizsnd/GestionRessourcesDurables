package Services;

import Model.Utilisateurs.Utilisateur;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author HP
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
        List<Utilisateur> utilisateurs = ServiceUser.getAllUtilisateurs();

        for (Utilisateur utilisateur : utilisateurs) {
            System.out.println("ID: " + utilisateur.getId());
            System.out.println("Name: " + utilisateur.getNom());
            System.out.println("Type: " + utilisateur.getType());
            System.out.println("-------------------------");
        }
    
    }
    
}
