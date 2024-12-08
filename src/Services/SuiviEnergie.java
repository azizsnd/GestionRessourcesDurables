package Services;

import DataBase.DatabaseConnection;
import Model.entiteDurable.Energie;
import Model.entiteDurable.ObjectifDurabilite;
import Model.entiteDurable.ObjectifInvalideException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SuiviEnergie {
    public static List<Energie> getAllEnergies() throws SQLException, ObjectifInvalideException {
        List<Energie> energies = new ArrayList<>();
        String query = """
            SELECT e.id, e.estRenouvelable, e.type,
                   ed.nom, ed.description, ed.dateCreation, 
                   od.id AS objectifId, od.dateCible,od.reductionCible, od.progresActuel, od.description AS objectifDescription,
                   r.uniteDeMesure, r.utilisationReference, r.utilisationActuelle, r.coutParUnite
            FROM Energie e
            INNER JOIN Ressource r ON e.id = r.id
            INNER JOIN EntiteDurable ed ON r.id = ed.id
            INNER JOIN ObjectifDurabilite od ON ed.objectifId = od.id;
        """;

        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                ObjectifDurabilite objectif = new ObjectifDurabilite(
                    rs.getDate("dateCible"),
                    rs.getDouble("reductionCible"),
                    rs.getDouble("progresActuel"),
                    rs.getString("objectifDescription")
                );

                Energie energie = new Energie(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("description"),
                    rs.getDate("dateCreation"),
                    objectif,
                    rs.getBoolean("estRenouvelable"),
                    rs.getString("type"),
                    rs.getString("uniteDeMesure"),
                    rs.getDouble("utilisationReference"),
                    rs.getDouble("utilisationActuelle"),
                    rs.getDouble("coutParUnite")
                );
                energies.add(energie);
            }
        }
        return energies;
    }

    public static void addEnergie(Energie energie) throws SQLException {
        String insertObjectif = "INSERT INTO ObjectifDurabilite (dateCible, reductionCible, progresActuel, description) VALUES (?, ?, ?, ?)";
        String insertEntite = "INSERT INTO EntiteDurable (nom, description, dateCreation, objectifId) VALUES ( ?, ?, ?, ?)";
        String insertRessource = "INSERT INTO Ressource (id, uniteDeMesure, utilisationReference, utilisationActuelle, coutParUnite) VALUES (?, ?, ?, ?, ?)";
        String insertEnergie = "INSERT INTO Energie (id, estRenouvelable, type) VALUES (?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement objectifStmt = connection.prepareStatement(insertObjectif, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement entiteStmt = connection.prepareStatement(insertEntite,Statement.RETURN_GENERATED_KEYS);
             PreparedStatement ressourceStmt = connection.prepareStatement(insertRessource,Statement.RETURN_GENERATED_KEYS);
             PreparedStatement energieStmt = connection.prepareStatement(insertEnergie))  {

            connection.setAutoCommit(false);
            // Insert into ObjectifDurabilite
            ObjectifDurabilite objectif = energie.getObjectif();

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
               entiteStmt.setString(1, energie.getNom());
               entiteStmt.setString(2, energie.getDescription());
               entiteStmt.setDate(3, new java.sql.Date(energie.getDateCreation().getTime()));
               entiteStmt.setInt(4, objectifId);
               entiteStmt.executeUpdate();

               // Retrieve the generated entite ID
               rs = entiteStmt.getGeneratedKeys();
               if (rs.next()) {
                    int entiteId = rs.getInt(1);

                    // Insert into Energie
                    ressourceStmt.setInt(1, entiteId);
                    ressourceStmt.setString(2, energie.getUniteDeMesure());
                    ressourceStmt.setDouble(3, energie.getUtilisationReference());
                    ressourceStmt.setDouble(4, energie.getUtilisationActuelle());
                    ressourceStmt.setDouble(5, energie.getCoutParUnite());
                    ressourceStmt.executeUpdate();
               
                    rs = ressourceStmt.getGeneratedKeys();
                    if (rs.next()) {
                         int ressourceId = rs.getInt(1);

                         // Insert into Energie
                         energieStmt.setInt(1, ressourceId);
                         energieStmt.setBoolean(2, energie.estRenouvelable());
                         energieStmt.setString(3, energie.getType());
                         energieStmt.executeUpdate();
                     }
                    connection.commit();
                }
               }
            } catch (SQLException e) {
                throw new SQLException("Error inserting Energie: " + e.getMessage(), e);
            }
    } }