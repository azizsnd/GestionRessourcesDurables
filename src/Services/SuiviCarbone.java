package Services;

import DataBase.DatabaseConnection;
import Model.entiteDurable.EmpreinteCarbone;
import Model.entiteDurable.ObjectifDurabilite;
import Model.entiteDurable.ObjectifInvalideException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SuiviCarbone {
    public static List<EmpreinteCarbone> getAllEmpreintes() throws SQLException, ObjectifInvalideException {
        List<EmpreinteCarbone> empreintes = new ArrayList<>();
        String query = """
            SELECT ec.id, ec.sourceEmission, ec.emissionAnnuelles, ec.emissionActuelle,
                   ed.nom, ed.description, ed.dateCreation, 
                   od.id AS objectifId, od.dateCible AS objectifDate, 
                   od.reductionCible, od.progresActuel, od.description AS objectifDescription
            FROM EmpreinteCarbone ec
            INNER JOIN EntiteDurable ed ON ec.id = ed.id
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

                EmpreinteCarbone empreinte = new EmpreinteCarbone(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("description"),
                    rs.getDate("dateCreation"),
                    objectif,
                    rs.getString("sourceEmission"),
                    rs.getDouble("emissionAnnuelles"),
                    rs.getDouble("emissionActuelle")
                );
                empreintes.add(empreinte);
            }
        }
        return empreintes;
    }

    public static void addEmpreinteCarbone(EmpreinteCarbone empreinte) throws SQLException {
        String insertObjectif = "INSERT INTO ObjectifDurabilite (dateCible, reductionCible, progresActuel, description) VALUES (?, ?, ?, ?)";
        String insertEntite = "INSERT INTO EntiteDurable (nom, description, dateCreation, objectifId) VALUES (?, ?, ?, ?)";
        String insertEmpreinte = "INSERT INTO EmpreinteCarbone (id, sourceEmission, emissionAnnuelles, emissionActuelle) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement objectifStmt = connection.prepareStatement(insertObjectif, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement entiteStmt = connection.prepareStatement(insertEntite, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement empreinteStmt = connection.prepareStatement(insertEmpreinte)) {

            connection.setAutoCommit(false);

            // Insert into ObjectifDurabilite
            ObjectifDurabilite objectif = empreinte.getObjectif();

            objectifStmt.setDate(1, new java.sql.Date(objectif.getDateCible().getTime()));
            objectifStmt.setDouble(2, objectif.getReductionCible());
            objectifStmt.setDouble(3, objectif.getProgresActuel());
            objectifStmt.setString(4, objectif.getDescription());
            objectifStmt.executeUpdate();

            // Retrieve generated objectif ID
            ResultSet rs = objectifStmt.getGeneratedKeys();
            if (rs.next()) {
                int objectifId = rs.getInt(1);

                // Insert into EntiteDurable
                entiteStmt.setString(1, empreinte.getNom());
                entiteStmt.setString(2, empreinte.getDescription());
                entiteStmt.setDate(3, new java.sql.Date(empreinte.getDateCreation().getTime()));
                entiteStmt.setInt(4, objectifId);
                entiteStmt.executeUpdate();

                // Retrieve generated entite ID
                rs = entiteStmt.getGeneratedKeys();
                if (rs.next()) {
                    int entiteId = rs.getInt(1);

                    // Insert into EmpreinteCarbone
                    empreinteStmt.setInt(1, entiteId);
                    empreinteStmt.setString(2, empreinte.getSourceEmission());
                    empreinteStmt.setDouble(3, empreinte.getEmissionAnnuelles());
                    empreinteStmt.setDouble(4, empreinte.getEmissionActuelle());
                    empreinteStmt.executeUpdate();

                    connection.commit();
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error inserting EmpreinteCarbone: " + e.getMessage(), e);
        }
    }
}
