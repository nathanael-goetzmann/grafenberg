package grafenberg.app.modeles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public boolean insererIntervention(Date date, int nuit, int duree, String topNum) throws SQLException {
        String query = "INSERT INTO intervention (date, nuit, duree, topNum) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement statement = connection.getPreparedStatement(query)) {
            statement.setDate(1, date);
            statement.setInt(2, nuit);
            statement.setInt(3, duree);
            statement.setString(4, topNum);
            
            return statement.executeUpdate() > 0;
        }
    }
}