package Services;

import DataBase.DatabaseConnection;
import Model.Utilisateurs.Administrateur;
import Model.Utilisateurs.Auditeur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Model.Utilisateurs.Utilisateur;
import java.util.ArrayList;
import java.util.List;

public class ServiceUser {

    public static Utilisateur authenticateUser(String username, String password) {
        String query = "SELECT id, nom, motDePasse, typeUtilisateur FROM Utilisateur WHERE nom = ? AND motDePasse = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String type = rs.getString("typeUtilisateur");
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String motDePasse = rs.getString("motDePasse");

                if ("Administrateur".equals(type)) {
                    return new Administrateur(id, nom, motDePasse, type);
                } else if ("Auditeur".equals(type)) {
                    return new Auditeur(id, nom, motDePasse, type);
                } else {
                    return new Utilisateur(id, nom, motDePasse, type);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Utilisateur> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String query = "SELECT id, nom, motDePasse, typeUtilisateur FROM Utilisateur";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String type = rs.getString("typeUtilisateur");
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String motDePasse = rs.getString("motDePasse");

                if ("Administrateur".equals(type)) {
                    utilisateurs.add(new Administrateur(id, nom, motDePasse, type));
                } else if ("Auditeur".equals(type)) {
                    utilisateurs.add(new Auditeur(id, nom, motDePasse, type));
                } else {
                    utilisateurs.add(new Utilisateur(id, nom, motDePasse, type));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return utilisateurs;
    }

    public static void addUser(String nom, String password, String type) {
        String query = "INSERT INTO Utilisateur (nom, motDePasse, typeUtilisateur) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nom);
            stmt.setString(2, password);
            stmt.setString(3, type);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateUser(Utilisateur user) {
        String query = "UPDATE Utilisateur SET nom = ?, motDePasse = ?, typeUtilisateur = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getNom());
            stmt.setString(2, user.getMotDePasse());
            stmt.setString(3, user.getType());
            stmt.setInt(4, user.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(int userId) {
        String query = "DELETE FROM Utilisateur WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
