package grafenberg.app.modeles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;

/**
 * Ce fichier assure la bonne connexion à la base de données
 * de la clinique Grafenberg.
 * @author Nathanaël Goetzmann
 * @version v1.0
 * @since 2026-10-15
 */

public final class ConnexionDAO {
    
    /**
     * Cette classe propose des méthodes publiques qui retournent
     * des objets de type « Statement », « PreparedStatement » ou
     * « CallableStatement ». Lors de l'instanciation, le constructeur
     * établit automatiquement la connexion vers la base de données
     * de la clinique Grafenberg.
     */
    
    private final Connection connexion;
    
    /**
     * Crée une instance dont la propriété « connexion » comporte
     * d'office une connexion à la base « grafenberg01 ».
     * @throws SQLException 
     */
    public ConnexionDAO() throws SQLException {
        connexion = DriverManager.getConnection(
                "jdbc:mysql://192.168.5.120:3307/grafenberg01",
                "javagraf",
                "kO7ù6Ha*Hj43"
        );
    }
    
    /**
     * Fournit un objet de type « Statement ».
     * @return Statement
     * @throws SQLException 
     */
    public Statement getSimpleStatement() throws SQLException {
        return connexion.createStatement();
    }
    
    /**
     * Fournit un objet de type « PreparedStatement ».
     * @param requete
     * @return PreparedStatement
     * @throws SQLException 
     */
    public PreparedStatement getPreparedStatement(String requete) throws SQLException {
        return connexion.prepareStatement(requete);
    }
    
    /**
     * Fournit un objet de type « CallableStatement ».
     * @param requete
     * @return CallableStatement
     * @throws SQLException 
     */
    public CallableStatement getCallableStatement(String requete) throws SQLException {
        return connexion.prepareCall(requete);
    }
}