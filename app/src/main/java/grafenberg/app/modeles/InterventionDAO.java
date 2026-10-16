package grafenberg.app.modeles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}