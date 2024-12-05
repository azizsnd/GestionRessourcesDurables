package Services;

import DataBase.DatabaseConnection;
import Model.serviceSuivi.Rapport;
 
import java.sql.*;

public class RapportDAO {

    public static void saveRapportToDatabase(Rapport rapport) throws SQLException {
        String query = "INSERT INTO Rapport (typeService, contenuRapport, dateRapport) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection(); 
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, rapport.getTypeService());  // Set the type of service
            preparedStatement.setString(2, rapport.getContenuRapport());
            preparedStatement.setTimestamp(3, new java.sql.Timestamp(rapport.getDateRapport().getTime()));

            preparedStatement.executeUpdate();
        }
    }
}