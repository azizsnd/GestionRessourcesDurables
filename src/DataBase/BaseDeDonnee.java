package DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDeDonnee {

    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM utilisateur";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Nom: " + resultSet.getString("nom"));                
                System.out.println("motdepasse: " + resultSet.getString("motdepasse"));       
                System.out.println("typeUtilisateur: " + resultSet.getString("typeUtilisateur"));       
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}