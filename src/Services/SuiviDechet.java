package Services;

import DataBase.DatabaseConnection;
import Model.entiteDurable.Dechet;
import Model.entiteDurable.ObjectifDurabilite;
import Model.entiteDurable.ObjectifInvalideException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SuiviDechet {
    public static List<Dechet> getAllDechets() throws SQLException, ObjectifInvalideException {
        List<Dechet> dechets = new ArrayList<>();
        String query = """
            SELECT d.id, d.type, d.quantiteProduite, d.quantiteRecycle, d.methodeElimination,
                   ed.nom, ed.description, ed.dateCreation, od.id AS objectifId, od.dateCible AS objectifDate,
                   od.reductionCible, od.progresActuel, od.description AS objectifDescription
            FROM Dechet d
            INNER JOIN EntiteDurable ed ON d.id = ed.id
            INNER JOIN ObjectifDurabilite od ON ed.objectifId = od.id;
        """;

        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                ObjectifDurabilite objectif = new ObjectifDurabilite(
                    rs.getDate("objectifDate"),
                    rs.getDouble("reductionCible"),
                    rs.getDouble("progresActuel"),
                    rs.getString("objectifDescription")
                );

                Dechet dechet = new Dechet(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("description"),
                    rs.getDate("dateCreation"),
                    objectif,
                    rs.getString("type"),
                    rs.getDouble("quantiteProduite"),
                    rs.getDouble("quantiteRecycle"),
                    rs.getString("methodeElimination")
                );

                dechets.add(dechet);
            }
        }
        return dechets;
    }

    public static void addDechet(Dechet dechet) throws SQLException {
        String insertObjectif = "INSERT INTO ObjectifDurabilite (dateCible, reductionCible, progresActuel, description) VALUES (?, ?, ?, ?)";
        String insertEntite = "INSERT INTO EntiteDurable (nom, description, dateCreation, objectifId) VALUES ( ?, ?, ?, ?)";
        String insertDechet = "INSERT INTO Dechet (id,type, quantiteProduite, quantiteRecycle, methodeElimination) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement objectifStmt = connection.prepareStatement(insertObjectif, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement entiteStmt = connection.prepareStatement(insertEntite,Statement.RETURN_GENERATED_KEYS);
             PreparedStatement dechetStmt = connection.prepareStatement(insertDechet)) {
            
            connection.setAutoCommit(false);
            // Insert into ObjectifDurabilite
            ObjectifDurabilite objectif = dechet.getObjectif();
            objectifStmt.setDate(1, new java.sql.Date(objectif.getDateCible().getTime()));
            objectifStmt.setDouble(2, objectif.getReductionCible());
            objectifStmt.setDouble(3, objectif.getProgresActuel());
            objectifStmt.setString(4, objectif.getDescription());
            objectifStmt.executeUpdate();

            // Retrieve the generated objectif ID
             ResultSet rs = objectifStmt.getGeneratedKeys();
            if (rs.next()) {
               int objectifId = rs.getInt(1);

               // Insert into EntiteDurable
               entiteStmt.setString(1, dechet.getNom());
               entiteStmt.setString(2, dechet.getDescription());
               entiteStmt.setDate(3, new java.sql.Date(dechet.getDateCreation().getTime()));
               entiteStmt.setInt(4, objectifId);
               entiteStmt.executeUpdate();

               // Retrieve the generated entite ID
               rs = entiteStmt.getGeneratedKeys();
               if (rs.next()) {
                    int entiteId = rs.getInt(1);

                    // Insert into Dechet
                    dechetStmt.setInt(1, entiteId);
                    dechetStmt.setString(2, dechet.getType());
                    dechetStmt.setDouble(3, dechet.getQuantiteProduite());
                    dechetStmt.setDouble(4, dechet.getQuantiteRecycle());
                    dechetStmt.setString(5, dechet.getMethodeElimination());
                    dechetStmt.executeUpdate();

                    connection.commit();
                }
               }
            } catch (SQLException e) {
                throw new SQLException("Error inserting Dechet: " + e.getMessage(), e);
            }
    } }