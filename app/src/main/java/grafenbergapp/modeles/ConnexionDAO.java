package grafenbergapp.modeles;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;

/**
 * La classe construit des objets Statement, PreparedStatement ou CallableStatement valides.<br>
 * Ils maintiennent une connexion à la base de données Grafenberg pour exécuter des requêtes SQL spécifiques. 
 * @author Nathanaël Goetzmann
 * @version 1.0
 * @since 2026-11-13
 */
public final class ConnexionDAO {
    
    /** Représente l'URL nécessaire pour se connecter à la base de données. */
    private static final String PATH = "jdbc:mysql://192.168.5.120:3307/grafenberg01?noAccessToProcedureBodies=true";
    /** Représente le nom de l'utilisateur défini pour l'application. */
    private static final String USER = "javagraf";
    /** Représente le mot de passe de l'utilisateur défini pour l'application. */
    private static final String PASS = "kO7ù6Ha*Hj43";
    
    /**
     * Etablit une nouvelle connexion vers la base de données, selon les informations contenues
     * dans les constantes de la classe.
     * @return un objet Connection
     * @throws SQLException 
     */
    private static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(PATH, USER, PASS);
    }
    
    /**
     * Instancie un objet Statement, connecté à la base en invoquant la méthode privée « createConnection() ».
     * @return un objet Statement
     * @throws SQLException 
     */
    public static Statement getStatement() throws SQLException {
        return createConnection().createStatement();
    }
    
    /**
     * Instancie un objet PreparedStatement qui précompile une requête SQL.<br>
     * La connexion à la base est effectuée grâce à l'appel de la méthode privée « createConnection() ».
     * @param requete la requête SQL à enregistrer
     * @return un objet PreparedStatement
     * @throws SQLException 
     */
    public static PreparedStatement getPreparedStatement(String requete) throws SQLException {
        return createConnection().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
    }
    
    /**
     * Instancie un objet CallableStatement qui précompile un appel de procédure stockée.<br>
     * La connexion à la base est effectuée en invoquant la méthode privée « createConnection() ».
     * @param requete l'appel de procédure stockée à enregistrer
     * @return un objet CallableStatement
     * @throws SQLException 
     */
    public static CallableStatement getCallableStatement(String requete) throws SQLException {
        return createConnection().prepareCall(requete);
    }
}
