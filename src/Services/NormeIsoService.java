package Services;

import DataBase.DatabaseConnection;
import Model.Reglementation.NormeIso;
import Model.Reglementation.Reglementation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NormeIsoService {

    public static List<NormeIso> getAllNormes() throws SQLException {
        List<NormeIso> isoNorms = new ArrayList<>();
        String query = "SELECT * FROM normeiso";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                NormeIso norme = new NormeIso(
                        resultSet.getInt("id"),
                        resultSet.getInt("numISO"),
                        resultSet.getString("descriptionNorme")
                );
                isoNorms.add(norme);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return isoNorms;
    }

    public static NormeIso getNormeByNumISO(int numISO) throws SQLException {
        String query = "SELECT * FROM NormeIso WHERE numISO = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, numISO);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return new NormeIso(
                        resultSet.getInt("id"),
                        resultSet.getInt("numISO"),
                        resultSet.getString("descriptionNorme"), null
                );
            }
        }
        return null;
    }

    public static void addNorme(NormeIso norme) throws SQLException {
        String query = "INSERT INTO NormeIso (numISO, descriptionNorme) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, norme.getNumISO());
            stmt.setString(2, norme.getDescriptionNorme());
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                norme.setId(generatedKeys.getInt(1));
            }
        }
    }

    public static void updateNorme(NormeIso norme) throws SQLException {
        String query = "UPDATE NormeIso SET descriptionNorme = ? WHERE numISO = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, norme.getDescriptionNorme());
            stmt.setInt(2, norme.getNumISO());
            stmt.executeUpdate();
        }
    }

    public static void addExigence(Reglementation exigence) throws SQLException {
        String query = "INSERT INTO Reglementation (nom, descriptionExigence, dateMiseEnApplication) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, exigence.nom());
            stmt.setString(2, exigence.descriptionExigence());
            stmt.setDate(3, new java.sql.Date(exigence.dateMiseEnApplication().getTime()));
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
            }
        }
    }

public static void deleteNorme(int id) throws SQLException {
    String deleteLinkQuery = "DELETE FROM normeiso_reglementation WHERE norme_id = ?";
    String deleteNormeQuery = "DELETE FROM NormeIso WHERE id = ?";
    
    try (Connection conn = DatabaseConnection.getConnection()) {
        conn.setAutoCommit(false);
        
        try (PreparedStatement stmt1 = conn.prepareStatement(deleteLinkQuery)) {
            stmt1.setInt(1, id);
            stmt1.executeUpdate();
        }
        
        try (PreparedStatement stmt2 = conn.prepareStatement(deleteNormeQuery)) {
            stmt2.setInt(1, id);
            stmt2.executeUpdate();
        }
        
        conn.commit();
    } catch (SQLException e) {
        e.printStackTrace();
        throw e; 
    }
}


    public static void updateExigence(Reglementation exigence) throws SQLException {
        String query = "UPDATE Reglementation SET nom = ?, descriptionExigence = ?, dateMiseEnApplication = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, exigence.nom());
            stmt.setString(2, exigence.descriptionExigence());
            stmt.setDate(3, new java.sql.Date(exigence.dateMiseEnApplication().getTime()));
            stmt.setInt(4, exigence.id());  // assuming `id()` is the primary key of Reglementation

            stmt.executeUpdate();
        }
    }

    public static void linkExigenceToNorme(NormeIso norme, Reglementation exigence) throws SQLException {
        String query = "INSERT INTO PlanDActionCorrectif_Reglementation (plan_id, reglementation_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            int planId = 1;
            stmt.setInt(1, planId);
            stmt.setInt(2, exigence.id());
            stmt.executeUpdate();
        }
    }
}
