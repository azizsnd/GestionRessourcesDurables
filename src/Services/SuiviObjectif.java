package Services;

import DataBase.DatabaseConnection;
import Model.entiteDurable.ObjectifDurabilite;
import Model.entiteDurable.ObjectifInvalideException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SuiviObjectif {
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

    public static List<ObjectifDurabilite> getAllObjectifs() throws SQLException, ObjectifInvalideException, ParseException {
        List<ObjectifDurabilite> objectifs = new ArrayList<>();
        String query = "SELECT id, dateCible, reductionCible, progresActuel, description FROM ObjectifDurabilite";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Date dateCible = rs.getDate("dateCible"); // Get date from the database
                String formattedDate = dateFormatter.format(dateCible); // Format the date for display
                ObjectifDurabilite objectif = new ObjectifDurabilite(
                    rs.getInt("id"),
                    dateFormatter.parse(formattedDate), // Parse back into Date object if needed
                    rs.getDouble("reductionCible"),
                    rs.getDouble("progresActuel"),
                    rs.getString("description")
                );
                objectifs.add(objectif);
            }
        }
        return objectifs;
    }

    public static void addObjectif(ObjectifDurabilite objectif) throws SQLException {
        String query = "INSERT INTO ObjectifDurabilite (dateCible, reductionCible, progresActuel, description) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setDate(1, new java.sql.Date(objectif.getDateCible().getTime()));
            stmt.setDouble(2, objectif.getReductionCible());
            stmt.setDouble(3, objectif.getProgresActuel());
            stmt.setString(4, objectif.getDescription());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de l'ajout de l'objectif: " + e.getMessage(), e);
        }
    }
}
