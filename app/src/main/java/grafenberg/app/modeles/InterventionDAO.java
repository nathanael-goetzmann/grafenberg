package grafenberg.app.modeles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

public class InterventionDAO {

    private ConnexionDAO connection;

    public InterventionDAO() throws SQLException {
        this.connection = new ConnexionDAO();
    }

    public ResultSet getAllInterventions() throws SQLException {

        String query = "SELECT * FROM intervention";
        
        PreparedStatement statement = connection.getPreparedStatement(query);
        
        return statement.executeQuery();
    }

    public ResultSet getInterventionById(int id) throws SQLException {
        String query = "SELECT * FROM intervention WHERE id = ?";
        PreparedStatement statement = connection.getPreparedStatement(query);
        statement.setInt(1, id);
        return statement.executeQuery();
    }

    public void insererIntervention(Date date, int nuit, int duree, String topNum, ArrayList<Personnel> personnel) throws SQLException { 
    String query = "INSERT INTO intervention (id, date, nuit, duree, topNum) VALUES (default, ?, ?, ?, ?)"; 
    
    try (PreparedStatement statement = connection.getPreparedStatement(query)) { 
        statement.setDate(1, date);
        statement.setInt(2, nuit); 
        statement.setInt(3, duree); 
        statement.setString(4, topNum);

    query = "INSERT INTO affecter (aff_inter_num, aff_per_num) VALUES (default, ?)"; 
    
        for (Personnel p : personnel) { 
                try (PreparedStatement statementp = connection.getPreparedStatement(query)) { 

                    statementp.setInt(1, p.getPer_num()); 
                    statementp.executeUpdate(); 
                } 
            } 
    } 
}
}
