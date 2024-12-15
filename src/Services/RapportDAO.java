package Services;

import DataBase.DatabaseConnection;
import Model.serviceSuivi.Rapport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RapportDAO {

    public static void saveRapportToDatabase(Rapport rapport) throws SQLException {
        String query = "INSERT INTO Rapport (typeService, contenuRapport, dateRapport) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, rapport.getTypeService());
            preparedStatement.setString(2, rapport.getContenuRapport());
            preparedStatement.setTimestamp(3, new Timestamp(rapport.getDateRapport().getTime()));

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    rapport.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public static List<Rapport> loadAllRapports() throws SQLException {
        List<Rapport> rapportList = new ArrayList<>();
        String query = "SELECT * FROM Rapport";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String typeService = resultSet.getString("typeService");
                String contenuRapport = resultSet.getString("contenuRapport");
                Date dateRapport = new Date(resultSet.getTimestamp("dateRapport").getTime());

                Rapport rapport = new Rapport(id, typeService, contenuRapport, dateRapport);
                rapportList.add(rapport);
            }
        }
        return rapportList;
    }

    public static Rapport getRapportById(int id) throws SQLException {
        String query = "SELECT * FROM Rapport WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String typeService = resultSet.getString("typeService");
                    String contenuRapport = resultSet.getString("contenuRapport");
                    Date dateRapport = new Date(resultSet.getTimestamp("dateRapport").getTime());

                    return new Rapport(id, typeService, contenuRapport, dateRapport);
                }
            }
        }
        return null;
    }

    public static void updateRapport(Rapport rapport) throws SQLException {
        String query = "UPDATE Rapport SET typeService = ?, contenuRapport = ?, dateRapport = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, rapport.getTypeService());
            preparedStatement.setString(2, rapport.getContenuRapport());
            preparedStatement.setTimestamp(3, new Timestamp(rapport.getDateRapport().getTime()));
            preparedStatement.setInt(4, rapport.getId());

            preparedStatement.executeUpdate();
        }
    }

    public static void deleteRapport(int id) throws SQLException {
        String query = "DELETE FROM Rapport WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
