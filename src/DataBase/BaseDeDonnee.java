package DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDeDonnee {

    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Client";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("idClt"));
                System.out.println("Nom: " + resultSet.getString("nomClt"));                
                System.out.println("tel: " + resultSet.getInt("tel"));       
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}