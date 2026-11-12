package grafenbergapp.modeles;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;

public final class ConnexionDAO {
    
    private static final String PATH = "jdbc:mysql://192.168.5.120:3307/grafenberg01";
    private static final String USER = "javagraf";
    private static final String PASS = "kO7ù6Ha*Hj43";
    
    private static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(PATH, USER, PASS);
    }
    
    public static Statement getStatement() throws SQLException {
        return createConnection().createStatement();
    }
    
    public static PreparedStatement getPreparedStatement(String requete) throws SQLException {
        return createConnection().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
    }
    
    public static CallableStatement getCallableStatement(String requete) throws SQLException {
        return createConnection().prepareCall(requete);
    }
}
