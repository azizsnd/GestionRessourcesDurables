package Services;

import DataBase.DatabaseConnection;
import Model.entiteDurable.Ressource;
import Model.entiteDurable.ObjectifDurabilite;
import Model.entiteDurable.ObjectifInvalideException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SuiviRessource {
    public static List<Ressource> getAllRessources() throws SQLException, ObjectifInvalideException {
        List<Ressource> ressources = new ArrayList<>();
        String query = """
            SELECT r.id, r.uniteDeMesure, r.utilisationReference, r.utilisationActuelle, r.coutParUnite,
                   ed.nom, ed.description, ed.dateCreation, od.id AS objectifId, od.dateCible AS objectifDate,
                   od.reductionCible, od.progresActuel, od.description AS objectifDescription
            FROM Ressource r
            INNER JOIN EntiteDurable ed ON r.id = ed.id
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

                Ressource ressource = new Ressource(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("description"),
                    rs.getDate("dateCreation"),
                    objectif,
                    rs.getString("uniteDeMesure"),
                    rs.getDouble("utilisationReference"),
                    rs.getDouble("utilisationActuelle"),
                    rs.getDouble("coutParUnite")
                );
                ressources.add(ressource);

            }
        }
        return ressources;
    }

    public static void addRessource(Ressource ressource) throws SQLException {
        String insertObjectif = "INSERT INTO ObjectifDurabilite (dateCible, reductionCible, progresActuel, description) VALUES (?, ?, ?, ?)";
        String insertEntite = "INSERT INTO EntiteDurable (nom, description, dateCreation, objectifId) VALUES ( ?, ?, ?, ?)";
        String insertRessource = "INSERT INTO Ressource (id,uniteDeMesure, utilisationReference, utilisationActuelle, coutParUnite) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement objectifStmt = connection.prepareStatement(insertObjectif, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement entiteStmt = connection.prepareStatement(insertEntite,Statement.RETURN_GENERATED_KEYS);
             PreparedStatement ressourceStmt = connection.prepareStatement(insertRessource)) {

            connection.setAutoCommit(false);
            // Insert into ObjectifDurabilite
            ObjectifDurabilite objectif = ressource.getObjectif();

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
               entiteStmt.setString(1, ressource.getNom());
               entiteStmt.setString(2, ressource.getDescription());
               entiteStmt.setDate(3, new java.sql.Date(ressource.getDateCreation().getTime()));
               entiteStmt.setInt(4, objectifId);
               entiteStmt.executeUpdate();

               // Retrieve the generated entite ID
               rs = entiteStmt.getGeneratedKeys();
               if (rs.next()) {
                    int entiteId = rs.getInt(1);

                    // Insert into Ressource
                    ressourceStmt.setInt(1, entiteId);
                    ressourceStmt.setString(2, ressource.getUniteDeMesure());
                    ressourceStmt.setDouble(3, ressource.getUtilisationReference());
                    ressourceStmt.setDouble(4, ressource.getUtilisationActuelle());
                    ressourceStmt.setDouble(5, ressource.getCoutParUnite());
                    ressourceStmt.executeUpdate();

                    connection.commit();
                }
               }
            } catch (SQLException e) {
                throw new SQLException("Error inserting Ressource: " + e.getMessage(), e);
            }
    } }