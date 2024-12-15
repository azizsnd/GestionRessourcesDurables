package Services;

import Model.Reglementation.NormeIso;
import java.sql.SQLException;
import java.util.List;

public class main {

    public static void main(String[] args) throws SQLException {
        List<NormeIso> nor=NormeIsoService.getAllNormes();
        System.out.println("nor : "+nor);
    }   
}
