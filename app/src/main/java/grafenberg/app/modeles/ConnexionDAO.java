package grafenberg.app.modeles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

/**
 * @author Nathanaël Goetzmann
 */

public final class ConnexionDAO {
    
    /**
     * Cette classe propose des méthodes publiques qui retournent
     * des objets de type Statement ou PreparedStatement.
     * Lors de l'instanciation, le constructeur établit automatiquement 
     * la connexion vers la base de données de la clinique Grafenberg.
     */
    
    private final Connection connexion;
    
    /**
     * Lorem ipsum
     * @throws SQLException 
     */
    public ConnexionDAO() throws SQLException {
        connexion = DriverManager.getConnection(
                "jdbc:mysql://localhost:3307/grafenberg",
                "javagraf",
                "kO7ù6Ha*Hj43"
        );
    }
    
    /**
     * Lorem ipsum
     * @return
     * @throws SQLException 
     */
    public Statement getSimpleStatement() throws SQLException {
        return connexion.createStatement();
    }
    
    /**
     * Lorem ipsum
     * @param requete
     * @return
     * @throws SQLException 
     */
    public PreparedStatement getPreparedStatement(String requete) throws SQLException {
        return connexion.prepareStatement(requete);
    }
}