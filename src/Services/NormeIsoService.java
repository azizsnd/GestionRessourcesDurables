/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

/**
 *
 * @author Jasser
 */

import Model.Reglementation.NormeIso;
import DataBase.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NormeIsoService {

    // Method to fetch all ISO norms from the database
    public static List<NormeIso> getAllNormes() throws SQLException {
        List<NormeIso> isoNorms = new ArrayList<>();
        String query = "SELECT id, code, descriptionNorme FROM NormeIso"; // Modify query based on your DB schema

        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                // Assuming `NormeIso` has fields id, code, and descriptionNorme
                NormeIso norme = new NormeIso(
                        rs.getInt("code"),
                        rs.getString("descriptionNorme")
                );
                isoNorms.add(norme);
            }
        }
        return isoNorms;
    }
}
