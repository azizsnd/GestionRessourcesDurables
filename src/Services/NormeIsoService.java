package Services;

import DataBase.DatabaseConnection;
import Model.Reglementation.NormeIso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NormeIsoService {

    public static List<NormeIso> getAllNormes() throws SQLException {
        List<NormeIso> isoNorms = new ArrayList<>();

        String query = "SELECT * FROM normeiso";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                NormeIso norme = new NormeIso(
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


    public static void addNorme(NormeIso norme) throws SQLException {
        String query = "INSERT INTO normeiso (numISO, descriptionNorme) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, norme.getNumISO());
            stmt.setString(2, norme.getDescriptionNorme());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void updateNorme(NormeIso norme) throws SQLException {
        String query = "UPDATE normeiso SET descriptionNorme = ? WHERE numISO = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, norme.getDescriptionNorme());
            stmt.setInt(2, norme.getNumISO());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void deleteNorme(int numISO) throws SQLException {
        String query = "DELETE FROM normeiso WHERE numISO = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, numISO);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
