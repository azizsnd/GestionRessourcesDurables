/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Services;

import Model.serviceSuivi.Rapport;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author HP
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        List<Rapport> rapports = RapportDAO.loadAllRapports();
        System.out.println("rapport"+rapports);
       
    }
    
}
