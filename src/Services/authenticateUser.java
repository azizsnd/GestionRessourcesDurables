package Services;
import DataBase.DatabaseConnection;
import Model.Utilisateurs.Administrateur;
import Model.Utilisateurs.Auditeur;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Model.Utilisateurs.Utilisateur;

/**
 *
 * @author HP
 */
public class authenticateUser {
    public static Utilisateur authenticateUser(String username, String password) {
        String query = "SELECT id, nom, motDePasse, typeUtilisateur FROM Utilisateur WHERE nom = ? AND motDePasse = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String type = rs.getString("typeUtilisateur");
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String motDePasse = rs.getString("motDePasse");

                if ("Administrateur".equals(type)) {
                    return new Administrateur(id, nom, motDePasse);
                } else if ("Auditeur".equals(type)) {
                    return new Auditeur(id, nom, motDePasse);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
    

